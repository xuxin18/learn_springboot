package early_bird;

import early_bird.bean.Book;
import early_bird.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/*
	springboot 的测试单元
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot02HelloworldQuickApplicationTests {

	@Autowired
	Person person;

	@Autowired
	Book book;

	//spring容器
	@Autowired
	ApplicationContext ac;

	@Test
	public void testHelloService(){
		System.out.println(ac.containsBean("helloService"));
	}

	@Test
	public void contextLoads() {
		System.out.println(person);
		System.out.println(book);

	}

}
