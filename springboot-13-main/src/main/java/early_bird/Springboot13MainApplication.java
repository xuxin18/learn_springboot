package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
	启动原理：
		SpringApplication.run(主程序类,args)-> new SpringApplication(主程序类).run(args);

		new SpringApplication(主程序类)-> initialize(主程序类)

		initiallize(主程序类)：
			setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
				getSpringFactoriesInstances(ApplicationContextInitializer.class)
					SpringFactoriesLoader.loadFactoryNames(type, classLoader): 将程序中的所有jar包（spring-bean的jar包、spring-boot的jia包、spring-boot的 autoconfigure包）
						的类路径下的 /META-INF/Spring.factories 配置的 所有的 ApplicationContextInitializer 保存到Set集合中
					createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names)：根据上面存的 类名 通过反射的方式，创建这些类的实例，存放到 List集合 instance 中
			setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class)):与上面的 setInitializers 的流程一样，只是 传入的参数改为了 ApplicationListener

		initialize（主程序类）详解：
			private void initialize(Object[] sources) {
				1.将主程序类放入 Set集合 sources 中
				if (sources != null && sources.length > 0) {
					this.sources.addAll(Arrays.asList(sources));
				}
				2.判断当前程序是否为web应用
				this.webEnvironment = deduceWebEnvironment();
				3.从类路径下找到的 META-INF/spring.factories 中配置的所有 ApplicationContextInitializer,并创建，保存到 SpringApplication 中
				setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
				4.从类路径下找到的 META-INF/spring.factories 中配置的所有 ApplicationListener,并创建，保存到 SpringApplication 中
				setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
				5.遍历当前栈帧，从中找到 运行了 main 方法的类（即主程序类），通过反射创建主程序类的实例，并保存到 SpringApplication 中
				this.mainApplicationClass = deduceMainApplicationClass();
			}
		run(args)详解：
			public ConfigurableApplicationContext run(String... args) {
					定义一个计时器，并开启计时器 （作用：在控制台输出springboot应用的启动耗时）
				StopWatch stopWatch = new StopWatch();
				stopWatch.start();
					声明一个ConfigurableApplicationContext 类型的变量 context
				ConfigurableApplicationContext context = null;
					声明一个 FailureAnalyzers 类型的变量 analyzers
				FailureAnalyzers analyzers = null;
					通过 System.setProperty() 来设置当前系统环境的属性为 java.awt.headless，即指定程序的运行模式为 HeadLess mode(不要指望硬件帮忙了，你得自力更生，依靠系统的计算能力模拟出这些特性来。)
						ps:System.setProperty()：将某个属性设置为系统中的全局变量，在项目的任何一个地方可以通过System.getProperty()来获得。相当于声明了一个静态变量在内存中
				configureHeadlessProperty();
				1.获取ApplicationContext中的监听器，并启动监听器
				SpringApplicationRunListeners listeners = getRunListeners(args);
				listeners.starting();
				try {
					2.封装命令行参数
					ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
					3.准备环境（标准Servlet环境 或 标准环境）；环境准备完成后 回调SpringApplicationRunListener.environmentPrepared() 用来加载 我们写的配置文件（例如：application.yml）
					ConfigurableEnvironment environment = prepareEnvironment(listeners,applicationArguments);
						在控制台输出图标
					Banner printedBanner = printBanner(environment);
					4.通过反射创建ApplicationContext（也被称为 应用上下文 或 ioc容器）；如果是 web 环境则创建 web的ioc容器，不是则创建普通的ioc容器
					context = createApplicationContext();
					5.创建 FailureAnalyzers ，用于在启动 ioc 容器出错时，输出分析错误报告
					analyzers = new FailureAnalyzers(context);
					6.为 ioc容器 设置环境，其中 applyInitializers(context) 会调用容器中的 所有的 ApplicationContextInitializer 的 initialize 方法
						listeners.contextPrepared(context) 会调用 ioc 容器中所有 ApplicationListener 的 contextPrepared 方法
						最后  listeners.contextLoaded(context) 会调用 ApplicationListener 的 contextLoaded 方法
					prepareContext(context, environment, listeners, applicationArguments,printedBanner);
					7.初始化ioc容器（如果是web应用，还会创建嵌入式的 tomcat）（扫描、创建、加载所有的配置类、组件、自动配置）todo 非常重要
					refreshContext(context);
					8.从ioc容器中通过 callRunner方法先调用所有的 ApplicationRunner，再调用所有的 CommmandLineRunner
					afterRefresh(context, applicationArguments);
					9.所有的 SpringApplicationRunListener 回调 finish 方法
					listeners.finished(context, null);
					计时器停止
					stopWatch.stop();
					if (this.logStartupInfo) {
						new StartupInfoLogger(this.mainApplicationClass)
								.logStarted(getApplicationLog(), stopWatch);
					}
					10.整个springboot应用启动后返回 启动了的 ioc容器
					return context;
				}
				catch (Throwable ex) {
					handleRunFailure(context, listeners, analyzers, ex);
					throw new IllegalStateException(ex);
				}
		}

启动过程中比较重要的类：
	配置在 META-INF/spring.factories中
		ApplicationContextInitializer
		ApplicationListener

	只需要放在ioc容器中
		ApplicationRunner
		CommandLineRunner
			这些 Runner 的作用：在ioc容器完成初始化之前，让我们可以（在容器启动的时候）执行一些操作

refreshContext(contexnt)->refresh(context)->((AbstractApplicationContext) applicationContext).refresh()->super.refresh()
refresh() 方法详解：
	public void refresh() throws BeansException, IllegalStateException {
		//refresh过程只能一个线程处理,不允许并发执行官
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
			清除换存，并读取环境中的配置
			prepareRefresh();

			// Tell the subclass to refresh the internal bean factory.
			此处的beanFactory是通过父类 GenericApplicationContext 来 获取 DefaultListableBeanFactory 类型的 BeanFactory
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// Prepare the bean factory for use in this context.
			给 beanFactory 配置标准的 上下文环境。例如设置 classloader、BeanPostProcessor(具体有两个： ApplicationContextAwareProcessor、ApplicationListenerDetector，给 AbstractApplicationContext使用)
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				继续向 beanFactory中添加 BeanPostProcessor （具体有：WebApplicationContextServletAwareProcessor，给 ServletWebServerApplicationContext 使用）
				postProcessBeanFactory(beanFactory);

				// Invoke factory processors registered as beans in the context.
				执行在 context 中注册的 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法
					BeanFactoryPostProcessor 是 bean 属性处理容器，即管理 BeanFactory 中的 BeanDefinition
					BeanDefinition 在 springmvc 中就是 xml 文件中对应的 bean 标签（注意，是标签，而不是真实的 java bean）
				invokeBeanFactoryPostProcessors(beanFactory);

				// Register bean processors that intercept bean creation.
				为实际使用的 context（即 AnnotationConfigEmbeddedWebApplicationContext） 注册 BeanPostProcessor （这是第三次注册BeanPostProcessor了）
				registerBeanPostProcessors(beanFactory);

				// Initialize message source for this context.
				实例化并注册 MessageSource（国际化相关的接口） 类
				initMessageSource();

				// Initialize event multicaster for this context.
				初始化ApplicationEventMulticaster（spring事件广播器），ApplicationContext 会通过广播器发送事件，负责监听广播器的 listener 会负责处理事件
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses. 最重要的方法
				在一些 特殊的 ApplicationContext 子类中实例化一些 特殊的 bean.(例如：实例化tomcat容器并启动、DispacherServlet、
					CharacterEncodingFilter、一些用户自定义的Servlet、Filter)
				onRefresh();

				// Check for listener beans and register them.
				为 ApplicationEventMulticaster 注册 监听器Listener（ApplicationListener）
					会有一些默认的 listener 被注册。例如这是一个 dubbo provider 的工程，就会注册DubboBannerApplicationListener
					也会有自定义的 listener 被注册
				registerListeners();

				// Instantiate all remaining (non-lazy-init) singletons.
				初始化 单例的、默认非懒加载的bean。注意器依赖自动注入的 bean 却不再此时注入，而是等到用的时候再去获取（所以这里不会出现循环依赖的问题）
				一个 spring bean 的初始化过程如下：
					1.执行构造器
					2.BeanPostProcessor 的 postProcessBeforeInitialization方法
					3.InitializingBean 的 afterPropertiesSet 方法
					4.@Bean 注解的 initMethod 方法
					5.BeanPostProcessor的postProcessAfterInitialization方法
					6.DisposableBean的destroy方法
					7.@Bean注解的destroyMethod方法
				finishBeanFactoryInitialization(beanFactory);

				// Last step: publish corresponding event.
				初始化生命周期处理器，并设置到Spring容器中(LifecycleProcessor)
				调用生命周期处理器的onRefresh方法，这个方法会找出Spring容器中实现了SmartLifecycle接口的类并进行start方法的调用
				发布ContextRefreshedEvent事件告知对应的ApplicationListener进行响应的操作
				调用LiveBeansView的registerApplicationContext方法：如果设置了JMX相关的属性，则就调用该方法
				发布EmbeddedServletContainerInitializedEvent事件告知对应的ApplicationListener进行响应的操作
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}



*/
@SpringBootApplication
public class Springboot13MainApplication {

	public static void main(String[] args) {
		System.out.println("调用 run 方法");
		SpringApplication.run(Springboot13MainApplication.class, args);
		System.out.println("执行完毕");
	}

}
