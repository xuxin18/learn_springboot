package early_bird.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.runner
 * @date 2019/5/24 11:03
 * @description
 */
@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner...run...运行" + Arrays.asList(args));
    }
}
