package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
与数据库相关几个比较重要的类：
	DataSourceConfiguration：根据DataSourceProperties 和 配置文件中的配置 创建数据源，默认使用 tomcat 连接池
			也可以使用 spring.dataSource.type 指定自定义的数据源类型
	DataSourceAutoConfiguration：数据源的自动配置类（主要是创建了 DataSourceInitializer 实例）
	DataSourceInitializer：
			运行建表语句 runSchemaScripts  （默认建表语句保存在类路径下的 schema-*.sql 或 schema.sql，也可以通过 spring.datasource.schema 来指定建表语句的位置和名字）
			运行插入语句 runSchemaScripts  （默认插入语句保存在类路径下的 data-*.sql 或 data.sql，也可以通过 spring.datasource.data 来指定建表语句的位置和名字）
	JdbcTemplateAutoConfiguration: 负责创建 JdbcTemplate 实例 和 NamedParameterJdbcTemplate 实例
			NamedPrameterJdbcTemplate 是 JdbcTemplate 的加强版
				在经典的 JDBC 用法中, SQL 参数是用占位符 ? 表示,并且受到位置的限制. 定位参数的问题在于, 一旦参数的顺序发生变化, 就必须改变参数绑定.
				在 Spring JDBC 框架中, 绑定 SQL 参数的另一种选择是使用具名参数(named parameter).
				那么什么是具名参数？
					具名参数: SQL 按名称(以冒号开头)而不是按位置进行指定. 具名参数更易于维护, 也提升了可读性.
					具名参数 只在 NamedPrameterJdbcTemplate 中支持

*/
@SpringBootApplication
public class Springboot10DataJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot10DataJdbcApplication.class, args);
	}

}
