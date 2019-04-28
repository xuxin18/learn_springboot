package early_bird.config;

import early_bird.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.config
 * @date 28 17:${MIMUTE}
 * @modified
 *
 * @Configuration： 指明当前类是配置类（就是用来替代之前的spring配置文件）
 *
 */

@Configuration
public class MyAppConfig {

    //将方法的返回值天骄到容器中（容器中这个组件的默认id就是方法名）
    @Bean
    public HelloService helloService(){
        System.out.println("配置类 @Bean 给容器中添加了组件");
        return new HelloService();
    }
}
