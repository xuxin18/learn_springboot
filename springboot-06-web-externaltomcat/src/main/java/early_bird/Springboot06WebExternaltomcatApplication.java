package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	使用外部的tomcat容器，将当前项目打包为 jar 包
		将这个项目结构与springboot-06-web-jsp 对比
*/
@SpringBootApplication
public class Springboot06WebExternaltomcatApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot06WebExternaltomcatApplication.class, args);
	}

}
