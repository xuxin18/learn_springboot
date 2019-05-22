package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*

配置嵌入式Servlet容器：
	springboot默认使用 tomcat 作为嵌入式的 servlet容器（具体可见 spring-boot-starter-web 启动器依赖的jar包中包含有 spring-boot-starter-tomcat）

如何定制和修改servlet容器的相关配置？
	1.在配置文件中修改和server有关的配置（其实本质也是 实现了EmbeddedServletContainerCustomizer 接口的 customize 方法）
	2.自定义一个 EmbeddedServletContainerCustomizer(嵌入式Servlet容器的定制器)来修改 Servlet容器的配置

注册Servlet三大组件（Servlet、Filter、Listener）：
	由于SpringBoot默认是以jar包的方式启动嵌入式的Servlet容器来启动SpringBoot的Web应用，没有web.xml文件。注册三大组件的方式为：
		ServletRegistrationBean
		FilterRegistrationBean
		ServletListenerRegistrationBean
			原理：具体查看 EmbeddedWebApplicationContext 的 onRefresh -> createEmbeddedServletContainer ->selfInitialize 该方法可以初始化 ServletContextInitializer 类型的类

注册Servlet的典型例子：
	SpringBoot启动SpringMVC的时候，自动注册了 SpringMVC的前端控制器（DisPatcherServlet）
		具体可以查看的 DispatcherServletAutoConfiguration 类的 dispatcherServletRegistration 和 dispatcherServlet 方法
			通过 ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet, this.serverProperties.getServletMapping())
			中的 this.serverProperties.getServletMapping() 知：默认拦截 /  所有请求（包括静态资源，但是不拦截jsp请求）
			/* 会拦截jsp, 但是 / 不会拦截jsp
			可以通过 server.servletPath来修改SpringMVC前端控制器默认拦截的请求路径

如何将嵌入式的Servlet容器 由 tomcat 改为 其他Servlet容器？
	通过 AbstractEmbeddedServletContainerFactory 的实现类知：
		SpringBoot内嵌3中Servlet容器：
			Tomcat、Undertow、Jetty
			简介：
				Undertow 和 jetty 都是基于 NIO 实现的高并发轻量级服务器（使用于长连接场景（更满足公有云的分布式环境） 例如：聊天系统）
				tomcat 适用于 短连接（一般是企业级环境）
	具体如何修改为其他容器，详见 pom 文件

（ xxxBeanPostProcessor 在spring中称为： xxx后置处理器，它的作用是 在 xxx 初始化之前 和 初始化之后 为 xxx 赋值）
为什么 EmbeddedServletContainerCustomizer（嵌入式Servlet容器定制器）可以帮我们修改Servlet容器的配置？
	EmbeddedServletContainerAutoConfiguration 配置类 上存在注解 @Import(BeanPostProcessorsRegistrar.class)
	BeanPostProcessorsRegistrar 类 通过registerSyntheticBeanIfMissing(registry,"embeddedServletContainerCustomizerBeanPostProcessor",EmbeddedServletContainerCustomizerBeanPostProcessor.class);
		方法为 容器中注入了 embeddedServletContainerCustomizerBeanPostProcessor（嵌入式Servlet容器定制器的后置处理器）
	EmbeddedServletContainerCustomizerBeanPostProcessor 类中的
	 	postProcessBeforeInitialization 方法: 如果当前初始化的是一个 ConfigurableEmbeddedServletContainer 类型的组件（即如果当前初始化的是Servlet 容器），
	 											则在初始化完成之前 获取所有的 EmbeddedServletContainerCustomizer 类型的定制器，然后调用每一个定制器的 customizer
	 											方法来给 Servlet容器工厂的属性赋值（这些属性会在工厂创建 servlet容器时赋值给servlet容器）
	 	postProcessAfterInitialization  方法：Servlet容器初始化完成后，将servlet 容器返回
	总结大致流程：
		1.SpringBoot启动时根据 pom 文件中导入的 jar包的情况，给容器添加相应的 EmbeddedServletContainerFactory
		2.当 Servlet容器工厂中需要创建对象时，就会触发 BeanPostProcessorsRegistrar 的 registerBeanDefinitions 方法
		3.registerBeanDefinitions 方法 会触发 EmbeddedServletContainerCustomizerBeanPostProcessor 后置处理器
		4.EmbeddedServletContainerCustomizerBeanPostProcessor 处理器就会调用 postProcessBeforeInitialization 方法来 遍历
			EmbeddedServletContainerCustomizer 来给 Servlet 容器的一些属性赋值
		5.当从 EmbeddedServletContainerFactory 工厂 中获取 Servlet 容器时 会调用 getEmbeddedServletContainer 方法
			该方法会根据之前给 Servler容器指定的一些属性 来 自动创建 Servler容器。
			这样 SpringBoot 创建的 Servlet 容器就具备了我们指定的配置

	在 TomcatEmbeddedServletContainerFactory 的 getEmbeddedServletContainer 方法中的Tomcat tomcat = new Tomcat() 打断点
	通过堆栈分析嵌入式tomcat容器启动原理：
		1.SpringBoot应用启动运行run方法
		2.refreshContext(context) ：SpringBoot刷新ioc容器【创建ioc容器对象，并初始化ioc容器，创建容器中的每一个组件
			如果是web应用，则创建 AnnotationConfigEmbeddedWebApplicationContext 】
		3.refresh(context)：刷新刚才创建好的ioc容器
		4.onRefresh()：web的ioc容器重写了onRefresh 方法
		5.web的ioc容器会创建嵌入式的Servlet容器 createEmbeddedServletContainer()
		6.获取嵌入式的Servlet容器工厂
			EmbeddedServletContainerFactory containerFactory = getEmbeddedServletContainerFactory()
			创建 EmbeddedServletContainerFactory 工厂，后置处理器 EmbeddedServletContainerCustomizerBeanPostProcessor 的
			postProcessBeforeInitialization 方法检测到当前创建工厂对象属于 ConfigurableEmbeddedServletContainer类型 就先定制
			Servlet容器相关的配置（这些配置放在工厂对象中）
		7.使用容器工厂获取嵌入式的Servlet容器
			this.embeddedServletContainer = containerFactory.getEmbeddedServletContainer(getSelfInitializer());
		8.嵌入式Servlet容器创建对象（tomcat）创建 并 启动
			创建 Tomcat tomcat = new Tomcat() 并给 tomcat容器设置一些属性（这些属性从工厂中获取）
			启动 getTomcatEmbeddedServletContainer(tomcat)
		todo AbstractApplicationContext 类的 refresh 方法。该方法是SpringBoot启动的核心 https://www.jianshu.com/p/5d75c9bdf0c6


*/
@SpringBootApplication
public class Springboot09ServletcontainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot09ServletcontainerApplication.class, args);
	}

}
