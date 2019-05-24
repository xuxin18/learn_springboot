package early_bird.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.initializer
 * @date 2019/5/24 11:07
 * @description
 */
public class HelloApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer...initialize...初始化" + applicationContext);
    }
}
