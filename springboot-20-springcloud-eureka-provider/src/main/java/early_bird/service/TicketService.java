package early_bird.service;

import org.springframework.stereotype.Service;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.service
 * @date 2019/6/5 16:05
 * @description
 */
@Service
public class TicketService {

    public String getTicket(){
        System.out.println("端口号为8082");
        return "《复仇者联盟4》";
    }
}
