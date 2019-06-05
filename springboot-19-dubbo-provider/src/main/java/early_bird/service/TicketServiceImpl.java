package early_bird.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.service
 * @date 2019/6/5 11:50
 * @description
 */
@Component // 将服务注册到ioc容器中
@Service // 使用 dubbo 的 @Service 注解，将服务发布出去（注册到 zookeeper）.注意 发布时 没有指定的name的话，就按 ticketService的 全类名发布出去的
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《大人物》";
    }
}
