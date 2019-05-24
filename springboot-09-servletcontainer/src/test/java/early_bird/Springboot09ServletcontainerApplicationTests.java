package early_bird;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot09ServletcontainerApplicationTests {

	@Test
	public void contextLoads() {
	}

}

class Car{


	public Car() {
		System.out.println("Car's Constructor..");
	}

	public void init(){
		System.out.println("Car's Init...");
	}

	public void destory(){
		System.out.println("Car's Destroy...");
	}

}
