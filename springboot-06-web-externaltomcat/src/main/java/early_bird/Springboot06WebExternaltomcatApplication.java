package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	使用外部的tomcat容器，将当前项目打包为 war 包（打包时将 pom文件中的 tomcat 的scope 指定为  provided）
		必须编写一个 SpringBootServletInitializer的子类，并调用 configure 方法

	将这个项目结构与springboot-06-web-jsp 对比

	如何让idea中的一个springboot项目使用外部的tomcat容器运行？
		1.对需要运行的项目配置 Edit Configurations
		2.选中需要使用外部tomcat的项目，点 + （或者 快捷键 alter+insert，即 add new configuration）
		3.找到tomcat 并选中 locale
			Server中的设置
				设置 name （自定义一个name）
				配置 Application server （点击 Configure，配置tomct home，即外部的tomcat所在的位置）
				在 Open Browser 栏设置 tomcat 的浏览器访问地址 例如：http://localhost:8080/
			Deployment中的设置
				点击 + 选择 artifact
				然后选择需要部署到外部tomcat的项目（选择以 exploded 结尾的项目）
				最后 点击 apply 即可
		4.运行
			运行时需要将 Select Run/Debug Configuration (即 运行按钮左边的选择框)选为 步骤3中的name
			即以 外部tomcat的方式运行项目

	ps：servlet3.0规范的 8.2.4章节 Shared libraries/runtimes pluggability 中这样描述：
			1.服务器启动web应用时会创建当前web应用里面每个jar包里面的 ServletContainerInitializer 的实例
			2.ServletContainerInitializer 的实现放在 META-INF/services 文件夹下，有一个名为
				javax.servlet.ServletContainerInitializer 的文件，文件的内容就是 ServletContainerInitializer
				的实现类的 全类名（例如：org.springframework.web.SpringServletContainerInitializer）
			3.还可以使用 @HandlesTypes 注解，用来在应用启动的时候加载一些自定义（需要在应用启动的时候加载）的类

	使用外置tomcat的项目的启动流程（war包启动流程）：
		启动tomcat服务器，tomcat服务器启动Springboot应用（SpringBootServletInitializer），启动ioc容器
		（内置tomcat的启动流程则是：先执行SpringBoot的主程序类，启动ioc容器，创建嵌入式Servlet容器（tomcat））
		具体流程如下：
			1.启动tomcat
			2.扫描web应用程序，扫描到项目依赖的jar包 spring-web 中的 META-INF/services 目录下的
				javax.servlet.ServletContainerInitializer 文件，该文件中存入的是
				org.springframework.web.SpringServletContainerInitializer
			3.SpringServletContainerInitializer 使用了 @HandlesTypes(WebApplicationInitializer.class) 注解
				这个注解会将 所有的 WebApplicationInitializer 类型的类 都传入到 onStartup 方法的 Set<Class<?>>
				如果 WebApplicationInitializer 类型的类不是接口或抽象类，onStartup方法会为它们创建实例（其实这个地方创
				建的就是 SpringBootServletInitializer 的子类，即 ServletInitializer），
				这些实例创建成功后，都会调用自己的 onStartup 方法（即调用 ServletInitializer 的 onStartup 方法）
			4.ServletInitializer 的 onStartup 方法 主要就是 调用了 createRootApplicationContext 方法 来创建容器
			5.createRootApplicationContext 方法中的
				 builder = configure(builder);
				 SpringApplication application = builder.build();
				这两个方法 会将 spingboot应用的主程序类传入进来，然后使用builder 创建一个 Spring 应用
				run(application); 方法用来启动spring应用（其实就是调用了 SpringApplication 的 ConfigurableApplicationContext 方法）
*/
@SpringBootApplication
public class Springboot06WebExternaltomcatApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot06WebExternaltomcatApplication.class, args);
	}

}
