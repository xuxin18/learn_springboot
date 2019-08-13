package early_bird;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
1.大多数应用中，可以通过消息服务中间间来提升：
 	系统异步通信
 		（例如：注册流程中，用户信息写入到数据后，还需要向用户发送短信和邮件后，返回注册成功的消息
 			利用消息队列,可以在用户信息写入到数据后，将调用发送短信和发送邮件的信息写入到消息队列
 			并在写入时，向用户返回注册成功的消息；然后，发送短信和发送邮件模块使用异步读取消息的方式
 			来发送邮件和短信，这样做极大的提高了用户体验
 		）
 	扩展解耦能力
 		（不同的模块之间通过 消息队列 来进行调用（而不是直接调用），实现了系统之间的解耦）
 	流量削峰
 		（例如：秒杀场景中，有效商品数量是100件，此时有10w 请求进入了系统，使用了消息队列后，可以控制
 			在前100条请求进入系统后，后面的其他请求直接返回秒杀失败，这样系统可以很轻松的处理这100条请求
 			极大的减轻了系统的压力）
2.消息服务中两个重要的概念：
	消息代理（message broker）
	目的地（destination）
		当消息发送者发送消息以后，将由消息代理接管，消息代理保证消息传递到指定的目的地
3.消息队列主要有两种形式的目的地：
	队列（queue）：主要用于 点对点通信
		消息发送者发送消息，消息代理将其放入一个队列中，消息接收者从队列中获取消息内容，消息读取后被移出队列
		消息只有唯一的发送者和接受者（可以有多个接收者监听消息队列，但是1条消息只能有一个接收者）
	主题（topic）：主要用于 发布（publish）/订阅（subscribe）消息通信
		发送者（发布者）发送消息主题，多个接收者（订阅者）监听（订阅）这个主题，那么就会在消息到达时同时收到消息
4.JMS 与 AMQP
	JMS（Java Message Service）：java消息服务，基于jvm消息代理的规范。（Active MQ）
	AMQP（Advanced Message Queuing Protocol）：高级消息队列协议（网络层协议），兼容jms，可以实现跨平台、跨语言系统之间的消息发送
		（RabbitMQ）
		在实际应用时，如果发送的是复杂的消息，可以将消息序列化后发送（即发送的数据类型是 byte[]）
5.Springboot对JMS 和 AMQP 的支持：
	spring-jms 提供了 对 jms 的支持；spring-rabbit提供了对 AMQP 的支持
	需要 ConnectionFactory 的实现来连接 消息代理
	提供 JmsTemplate 、RabbitTemplate 来发送信息
	@JmsListener、 @RabbitListener注解在方法上监听消息代理发布的消息
	@EnableJms、 @EnableRabbit 开启支持
	JmsAutoConfiguration、 RabbitAutoConfiguration 提供了消息组件的自动配置

RabbitMQ：
	核心概念：
		Message：消息，消息是不具名的，它由消息头和消息体组成。
			消息头由一系列的可选属性组成，这些属性包括routing-key（路由键）、priority（相对于其他消息的优先权）
				delivery-mode（指出该消息可能需要持久性存储）等
			消息体是不透明的，携带具体消息内容
		Publishser：消息的生产者，也是一个向交换器发布消息的客户端应用程序
		Exchange：交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列
			Exchange有4种类型：
				direct（点对点，默认模式）
				fanout
				topic
				headers
		Queue：消息队列，用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列
			消息一直在队列里面，等待消费者连接到这个队列将其取走
		Binding：绑定，用于消息队列和交换器之间的关联。一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则
			Exchange 和 Queue 绑定可以是多对多的关系
		Connection：网络连接，比如一个 tcp 连接
		Channel：信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的tcp连接内的虚拟连接，AMQP命令都是
			通过信道发送出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成的。因为对于操作系统来说
			建立和销毁tcp都是非常昂贵的开销，所以引入了信道的概念，以复用一条tcp连接
		Consumer：消息的消费者，表示一个从消息队列中取得消息的客户端应用程序
		Virtual Host：虚拟主机，表示一批交换器、消息队列和相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。
			每个vhost本质上就是一个mini版的RabbitMQ服务器，拥有自己的队列、交换器、绑定和权限机制。vhost是AMQP概念的基础
			必须在连接时指定vhost.(rabbitmq默认的vhost是 / )
		Broken:表示消息队列服务器实体（即它包含vhost）

	运行机制：
		生产者将消息发布到 Exchange上，Binding决定交换器的消息发送到那个队列，最终消息被消费者从队列中接收。
	Exchange 的分发策略：
		direct：单播，点对点，消息中的路由键（routing key） 如果和Binding中的binding key 完全一致，则交换器
			将消息发送到对应的队列中
		headers：匹配的是AMQP消息的 header,而不是路由键，与direct基本相同，但是性能差很多，目前基本不用
		fanout：fanout交换器不处理路由键，只是简单的将队列绑定到交换器上，每个发送到交换器的消息都会被转发到与该
			交换器绑定的所有队列上，很像子网广播，每台子网内的主机都获得了一份复制的消息，fanout类型转发消息时最快的
		topic：topic 交换器通过模式匹配分配消息的路由键属性，将路由键和某个模式进行匹配，此时队列需要绑定到一个模式上。
			它将路由键和绑定键的字符串切分成单词，这些单词之间用点隔开，它同样也会识别两个通配符：
				#：匹配0个或多个单词
				*：匹配一个单词
	docker 中运行 RabbitMQ: docker run -d -p 5672:5672 -p 15672:15672 --name myrabbitmq 8e69b73e98c9
		访问： docker主机所在的地址:15672 看下能不能进入rabbit的管理页面

RabbitMQ的自动配置：
	自动配置类 RabbitAutoConfiguration
		自动配置类中配置了连接工厂 ConnectionFactory
		RabbitProperties 封装了 RabbitMQ 的配置
		RestTemplate：从RabbitMQ接收消息或发送消息到RabbitMQ
		AmqpAdmin：RabbitMQ系统管理功能组件（创建和删除 Queue、Exchange、Binding）
	@EnableRabbit 开启基于注解的RabbitMQ模式
	@RabbitListener 监听消息队列的内容
*/
@EnableRabbit
@SpringBootApplication
public class Springboot15RabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot15RabbitmqApplication.class, args);
	}

}
