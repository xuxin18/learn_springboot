package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
在编写主配置文件的时候，文件名可以是： application-{profile}.properties/yml
							默认为 application.properties/yml
激活指定的 profile（以application-prod.yml）：
	1.在配置文件application.yml 中指定
		 spring:
		   profiles:
		     active: prod
 	2.命令行方式：
 		java -jar springboot-03-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
 		(也可以直接配置program arguments --spring.profiles.active=prod)
 	3.虚拟机参数配置
 		-Dspring.profiles.active=prod
 		ps 命令行配置 和 虚拟机配置 的地方是：Edit Configurations 界面的 program arguments 和 vm options
	这三种方式的优先级为：
 		program arguments > 虚拟机参数配置 > 配置文件中指定

 配置文件的加载位置：
 	-file: ./config/          即： application-1.yml 所在的位置
 	-file: ./                 即： application-2.yml 所在的位置
 	-classpath: /config/      即： application-3.yml 所在的位置
 	-classpath: /             即： application.yml   所在的位置（springboot主配置文件的默认位置）
		这4个位置的主配置文件的 加载优先级 是 由高到低，高优先级的配置会覆盖低优先级的配置；
 		值得注意的是：springboot会从这四个位置 全部 加载主配置文件；这四个文件会进行 互补配置

 项目打好包后，我们还可以使用命令行参数的形式，启动项目的时候指定配置文件的新位置，这个指定的配置文件和默认
 	加载的配置文件会 共同起作用，形成互补配置。例如：
 	java -jar springboot-03-config-0.0.1-SNAPSHOT.jar --spring.config.location=G:/application.properties

 配置文件加载规则：
 	1.优先级从高到低；高优先级的配置覆盖低优先级的配置，所有的配置会形成互补配置
	2.由jar包外向jar包内进行寻找
 	3.优先加载带profile
 	4.再加载不带profile
 		加载顺序：
 			最优先加载 命令行参数，然后加载其他配置
			1.jar包 外 部的application-{profile}.properties或application.yml(带spring.profile)配置文件
 			2.jar包 内 部的application-{profile}.properties或application.yml(带spring.profile)配置文件
			3.jar包外部的application.properties或application.yml(不带spring.profile)配置文件
 			4.jar包内部的application.properties或application.yml(不带spring.profile)配置文件




 */
@SpringBootApplication
public class Springboot03ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot03ConfigApplication.class, args);
	}

}
