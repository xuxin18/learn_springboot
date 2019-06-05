package early_bird.controller;


import early_bird.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.controller
 * @date 2019/6/5 16:07
 * @description
 *
 * 由于 springcloud 在 整合 微服务 时，是通过轻量级的 http协议进行通讯
 */
@RestController
public class TickerController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/ticket")
    public String getTicket(){
        return ticketService.getTicket();
    }
}
