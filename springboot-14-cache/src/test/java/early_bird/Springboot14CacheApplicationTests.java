package early_bird;

import early_bird.bean.Employee;
import early_bird.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot14CacheApplicationTests {

	@Autowired
	EmployeeMapper employeeMapper;

	/*
		这两个组件，详细见 Redis的自动配置类： RedisAutoConfiguration
	*/
	@Autowired
	StringRedisTemplate stringRedisTemplate; //专门用来保存 5种基本数据类型
	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	RedisTemplate<Object, Employee> empRedisTemplate;

	/*
		redis 常见的5种数据类型：
			String（字符串）
			List(列表)
			Set(集合)
			Hash（散列）
			ZSet（有序集合）
	*/
	@Test
	public void test1(){
		/*// opsForValue 方法用来操作 String 类型数据
		stringRedisTemplate.opsForValue().append("msg", "hello");*/
		//System.out.println("-----------" + stringRedisTemplate.opsForValue().get("msg"));
		//opsForList 方法用来操作 List 类型数据，其他类型的类似
		stringRedisTemplate.opsForList().leftPush("myList", "1");
		stringRedisTemplate.opsForList().leftPush("myList", "2");
	}
	/*
		保存对象
	*/
	@Test
	public void test2(){
		Employee emp = employeeMapper.getEmpById(1);
		/*//如果保存对象，redis默认使用 jdk序列化机制，将序列化的数据保存到redis中，使用RedisDeskManager查看会爆错或者出现乱码，但是不影响反序列化
		redisTemplate.opsForValue().set("emp-1", emp);
		System.out.println(redisTemplate.opsForValue().get("emp-1"));*/

		/*
			通常我们将数据以json的方式保存，有两种实现方式：
				1.自己使用插件，将对象转为 json （例如： ObjectMapper）
				2.使用redisTemplate提供的序列化规则 （默认使用jdk序列化，但是我们可以修改默认规则）。
					具体实现见： MyRedisConfig
		*/
		empRedisTemplate.opsForValue().set("emp-2", emp);
		System.out.println(empRedisTemplate.opsForValue().get("emp-2"));
	}

	@Test
	public void contextLoads() {
		Employee emp = employeeMapper.getEmpById(1);
		System.out.println(emp);
	}

	@Test
	public void test4(){
		System.out.println(rup(2));
		System.out.println(rup(3));
	}

	private static int rup(int number){
		return number >= (1 << 30)
				? (1 << 30)
				: (number > 1)? Integer.highestOneBit((number-1)<<1):1;
	}

}
