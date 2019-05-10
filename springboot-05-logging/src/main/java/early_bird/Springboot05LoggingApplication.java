package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
	日志门面：
		JCL （spring使用）
		SLF4J（springboot使用）：Simple Logging Facade for Java，最为常用
		jboss-logging（hibernate使用）
	日志实现有：
		Log4j 	（落伍的技术）
		Log4j2  （据说功能最强大）
		Logback （最常用）

	Spingboot选用的是：SLF4J 和 Logback

	SLF4J 官网 https://www.slf4j.org

	不同的框架用的不同的日志门面，如何让系统中所有的日志都统一到SLF4J呢？
		1.将系统中其他的日志框架先排除出去
			例如：springboot中引用了 spring框架，而spring框架使用的是 commons-logging 日志实现
				 这时候在引用 spring 框架的时候，应当先在 spring-core 中把该日志实现排除：
				 	<dependency>
						<groupId>org.springframework</groupId>
						<artifactId>spring-core</artifactId>
						<exclusions>
							<exclusion>
								<groupId>commons-logging</groupId>
								<artifactId>commons-logging</artifactId>
							</exclusion>
						</exclusions>
					</dependency>
		2.使用中间包来替换原有的日志框架（例如：使用中间包 jcl-over-slf4j 替换 commons logging api（即jcl日志门面））
					在pom文件中添加starter：
					<dependency>
						<groupId>org.springframework.boot</groupId>
						<artifactId>spring-boot-starter-logging</artifactId>
					</dependency>
					该依赖中 包含了替换包：
						<dependency>
							<groupId>org.slf4j</groupId>
							<artifactId>jcl-over-slf4j</artifactId>
						</dependency>
		3.导入SLF4J的其他实现（例如：使用 Log4J日志实现，则导入 slf4j-log4j12 来适配）

	日志的输出格式：
		%d 表示日期时间
		%thread 表示线程名
		%-5level 级别从左显示5个字符宽度
		%logger{50} 表示logger名字最长50个字符，否则按照句点分割
		%msg 日志信息
		%n 是换行符

	如果使用springboot默认的配置，则日志相关配置所在的位置为
		org.springframework.boot:1.5.9.RELEASE 包的 org.springframework.boot.logging 的目录下的xml或者properties文件中

	如果我们需要自己指定日志配置，那我们给 classpath（resource目录下）位置添加我们自己的配置文件即可，springboot就不会使用默认的配置了
		日志实现 与 日志配置名称 的关系固定如下：
			logback    logback.xml 或者 logback-spring.xml
							logback.xml会直接被日志框架识别
							logback-spring.xml 日志框架不直接加载日志的配置项，而是由springboot解析日志配置，可以使用springboot的高级Profile功能。
							例如：指定在某种环境下 开启某种 日志输出格式的配置
								<layout class="ch.qos.logback.classic.PatternLayout">
									<springProfile name="dev">
										<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ----> [%thread] ---> %-5level %logger{50} - %msg%n</pattern>
									</springProfile>
									<springProfile name="!dev">
										<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ==== [%thread] ==== %-5level %logger{50} - %msg%n</pattern>
									</springProfile>
								</layout>
			log4j2     log4j2.xml  或者 log4j2-spring.xml
			JDK        logging.properties


			level：要记录的日志级别，包括 TRACE < DEBUG < INFO < WARN < ERROR,级别越低信息越详细

*/
@SpringBootApplication
public class Springboot05LoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot05LoggingApplication.class, args);
	}

}
