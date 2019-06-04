package early_bird;

import early_bird.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot15RabbitmqApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	// declare* 方法： 创建。。。
	@Test
	public void createExchange(){
		/*amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
		amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
		amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqpadmin.#", null));
		System.out.println("创建完成");*/

		//删除 Exchange 和 Queue时，传入 name 即可
		amqpAdmin.deleteExchange("amqpadmin.exchange");
		amqpAdmin.deleteQueue("amqpadmin.queue");
	}

	//点对点
	@Test
	public void send1() {
		/*
		这种消息发送方式，Message需要自己构造，可以自定义消息头（MessageProperties）和消息体（body,需要序列化为字节流）内容
		rabbitTemplate.send(exchange,routeKey,message);
		常用的方式如下：
		只需要传入发送的对象objec，objectt默认当成消息体，自动序列化发送给mq
		rabbitTemplate.convertAndSend(exchange,routeKey,object);
		*/
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "第一个发送到RabbitMQ的消息");
		map.put("data", Arrays.asList("hello world", 123, false));
		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("雪中悍刀行","大内总管陈政华"));
	}

	//广播
	@Test
	public void send2(){
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("剑来", "烽火戏诸侯"));
	}



	@Test
	public void receive(){
		Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
		System.out.println(o.getClass());
		System.out.println(o);
	}

}
