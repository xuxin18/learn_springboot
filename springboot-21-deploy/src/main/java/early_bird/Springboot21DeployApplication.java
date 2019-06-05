package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
热部署：
	在系统中有 html 页面时，禁用模板引擎的 cache ，ctrl + F9 可以让 html 热部署
	对于 java代码而言：
		使用：jrebel 或者 springboot devtools 插件，
			修改文件后 ctrl + F9 即可

*/
@SpringBootApplication
public class Springboot21DeployApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot21DeployApplication.class, args);
	}

}
