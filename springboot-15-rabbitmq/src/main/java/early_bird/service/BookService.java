package early_bird.service;

import early_bird.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.service
 * @date 2019/6/4 10:04
 * @description
 */
@Service
public class BookService {
    @Autowired
    MessageConverter messageConverter;

    //直接获取消息体并反序列化返回
    @RabbitListener(queues = "atguigu.news") //指定监听 atguigu.news 队列
    public void receive(Book book){
        System.out.println("收到的消息是：" + book);
    }

    //获取 Message 对象
    @RabbitListener(queues = "atguigu")
    public void receive(Message message){
        System.out.println("消息头：" + message.getMessageProperties());
        System.out.println("消息体：" + message.getBody());
    }
}
