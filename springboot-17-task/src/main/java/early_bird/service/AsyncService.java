package early_bird.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.service
 * @date 2019/6/5 9:42
 * @description
 */
@Service
public class AsyncService {

    @Async //表明该方法是个异步方法
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据处理中");
    }

}
