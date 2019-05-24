package early_bird.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.runner
 * @date 2019/5/24 11:03
 * @description
 */

@Component
public class HelloApplicationRunner implements ApplicationRunner{
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner...run...运行");
    }
}
