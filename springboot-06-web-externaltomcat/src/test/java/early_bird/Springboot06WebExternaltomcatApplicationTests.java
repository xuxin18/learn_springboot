package early_bird;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot06WebExternaltomcatApplicationTests {

	@Test
	public void contextLoads() {

		new Thread(new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return null;
			}
		})).start();
	}

}
