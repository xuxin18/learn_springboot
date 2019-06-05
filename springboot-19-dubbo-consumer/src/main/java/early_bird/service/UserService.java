package early_bird.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.service
 * @date 2019/6/5 14:13
 * @description
 */
@Service
public class UserService {

    @Reference //以当前 ticketService 的全类名 去 注册中心中 查找并获取服务
    TicketService ticketService;

    public void hello(){
        String ticket = ticketService.getTicket();
        System.out.println("买到了电影票：" + ticket);
    }
}
