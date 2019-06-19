package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
	todo 待验证 mybatis 的一些知识：
		SqlSession： 作为 mybatis 工作的顶层 api 接口，作为会话访问，完成 crud 功能
		Executor  :  mybatis 的执行器，是 mybatis 的核心。负责 sql 动态语句的生成和查询缓存的维护
		StatementHandler：	负责处理 jdbc 与 statement 的交互，包括对 statement设置参数、以及
			jdbc返回的 resultSet 结果集转为 list
		TypeHandler：负责数据类型和jdbc数据类型之间的转换

*/
//@MapperScan(value = "early_bird.mapper") // 使用 @MapperScan 指定扫描mapper所在的包，这样就可以不用在 Dao层的 接口上加@Mapper注解
@SpringBootApplication
public class Springboot11DataMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot11DataMybatisApplication.class, args);
	}

}
