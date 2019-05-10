package early_bird.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.config
 * @date 10 10:${MIMUTE}
 * @modified
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }
}
