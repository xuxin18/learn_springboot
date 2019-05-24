package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	SpringData是一套规范，整合对各种数据库的访问（redis、MongoDB、springdata jpa）
		springdata jpa：整合了使用 orm 原理的框架（例如：Hibernate、toplink、open JPA）

	SpringData 提供了统一的操作数据库的接口： Repository 接口
		Repository：
			RevisionRepository：基于乐观锁机制的接口
			CrudRepository：含有基本CRUD功能的接口
			PagingAndSortingRepository：基本CRUD 及分页
*/
@SpringBootApplication
public class Springboot12DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot12DataJpaApplication.class, args);
	}

}
