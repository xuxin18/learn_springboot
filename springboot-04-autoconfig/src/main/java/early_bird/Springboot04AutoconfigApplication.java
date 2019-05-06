package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
	springboot 官方文档：https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/htmlsingle/
	主配置文件可以写什么 可以参考官方文档的 Common application properties 章节

 自动配置原理：
 	SpringBoot启动的时候加载主配置类，开启了自动配置功能（ @SpringBootApplication 里包含了 @EnableAutoConfiguration）

 	@EnableAutoConfiguration 的作用：
 		使用了 @Import(EnableAutoConfigurationImportSelector.class) 注解(即导入了 自动配置选择器组件)
 			可以查看该类父类中 selectImports() 方法的内容
				获取候选配置
					List<String> configurations = getCandidateConfigurations(annotationMetadata,attributes);
				扫描jar包类路径下 META-INF/spring.factories,把扫描到的这些文件的内容包装成 properties对象，
				并从 properties中获取到 EnableAutoConfiguration.class 对应的值，然后把他们添加到容器中。
				（每一个 ***AutoConfiguration类都是容器中的一个组件，都加入到容器中，用它来做自动配置）
					List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader());
 				每个自动配置类都会进行自动配置功能（根据当前项目中不同的条件，决定配置类是否生效，一旦生效，就会通过 @Bean注解，往容器中添加组件），
					以 HttpEncodingAutoConfiguration （Http编码自动配置）为例，解释自动配置的原理
 				***Properties类中 封装着配置文件中相关的属性（配置文件能配置什么就可以参照这个自动配置功能对应的properties类）

 如何知道哪些自动配置类生效了？
 	在配置文件中，配置 debug=true 来启用在控制台打印自动配置类相关信息。
 		Positive matches：启用了的 自动配置类
		Negative matches：未启用的 自动配置类

*/
@SpringBootApplication
public class Springboot04AutoconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot04AutoconfigApplication.class, args);
	}

}
