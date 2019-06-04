package early_bird.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.config
 * @date 2019/6/4 9:05
 * @description 当没有指定 MessageConverter 时，RabbitTemplate 默认使用的序列化方式是 jdk序列化方式，当我们指定了时
 *                  则会使用我们指定的 MessageConverter 将默认的给覆盖掉，详见 RabbitAutoConfiguration 的 rabbitTemplate 方法
 */

@Configuration
public class MyAMQPConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
