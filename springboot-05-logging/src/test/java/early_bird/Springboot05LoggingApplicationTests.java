package early_bird;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot05LoggingApplicationTests {

	//日志记录器
	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void contextLoads() {
		/*
			日志级别：由低到高
				可以调整日志的输出级别
				级别越高输出的信息越少（即，error 级别信息最少，trace 级别信息最多）
		    可以在配置文件中指定日志级别
		*/
		logger.trace("这是trace");
		logger.debug("这是debug");
		//springboot默认使用的级别是 info 级 也被称为 root 级
		logger.info("这是info（root）");
		logger.warn("这是warn");
		logger.error("这是error");

	}

}
