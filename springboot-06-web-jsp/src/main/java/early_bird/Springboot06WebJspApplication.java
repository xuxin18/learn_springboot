package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
通过 WebMvcAutoConfiguration 类的
	addResourceHandlers 方法知道：
		springboot 以webjars（一种用来引入静态资源的 jar 包） 的方式引入静态资源。
			所有访问路径 为 /webjars/** 都会去 classpath：/META-INF/resource/webjars/** 路径下找资源
			例如：http://localhost:8080/webjars/jquery/3.4.1/jquery.js 会访问
				jquery-3.4.1.jar/META-INF/resources/webjars/jquery/3.4.1/jquery.js 资源
		在 ResourceProperties 中可以设置和静态资源相关的参数（如：缓存时间等）
		访问当前项目的任何静态资源（/**）,都去这几个文件夹中找映射（classpath（类路径）：main包下的java和resources所在的路径）：
			classpath：/META-INF/resources/
			classpath:/resources/
			classpath:/static/
			classpath:/public/
			以及 / (当前项目路径)
				例如：访问 http://localhost:8080/asserts/js/chart.min.js

	welcomePageHandlerMapping 方法可以进行欢迎页配置，默认 /** 与静态资源文件夹下的 index.html 进行关联
	静态内部类 FaviconConfiguration 中可以设置 喜欢的图标， 所有路径下的/favicon.ico 都会在静态资源文件下找

	jsp（java server page ）：专门给java server 用的（即在容器里运行）
	springboot是内嵌web容器，推荐打成jar包而不是war包（如果打成war包，这就相当于失去了一些springboot的特性）
	为了能在jar包中使用类似jsp的强大功能（动态获取信息），需要使用模板引擎。模板引擎的原理：
		模板 + 数据 通过 模板引擎 的处理，转化为我们需要的信息。

	//todo 通过 ThymeleafAutoConfiguration 配置类可以知道，只要我们把HTML页面放在classpath:/templates/，thymeleaf就能自动渲染；


*/
@SpringBootApplication
public class Springboot06WebJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot06WebJspApplication.class, args);
	}

}
