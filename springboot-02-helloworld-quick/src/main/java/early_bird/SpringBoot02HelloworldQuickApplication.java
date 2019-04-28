package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	resources文件夹中的目录结构：
		static：保存1所有的静态资源（js、css、images）
		template：保存所有的模板页面（springboot默认jar包使用嵌入式的tomcat，默认不支持 jsp页面，可以使用模板引擎（freemark、thymeleaf））
		application.properties : springboot的应用配置文件（也可以修改一些默认的配置）

	springboot使用一个默认全局的配置文件，配置文件是固定的：application.properties 或 application.yml
		推荐使用 yml文件作为配置文件

    YAML 是 json 的超集合，即 YAML支持json格式
	YAML（yml：A markup language）语法：
	    1.大小写敏感
	    2.使用缩进表示层级关系
	    3.缩进时不允许使用tab键，只允许使用空格
	    4.缩进的空格数目不重要，只要相同层级的元素左对齐即可。

*/

//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class SpringBoot02HelloworldQuickApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot02HelloworldQuickApplication.class, args);
	}

}
