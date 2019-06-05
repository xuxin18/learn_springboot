package early_bird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.controller
 * @date 2019/6/5 16:48
 * @description
 */
@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/buy")
    public String buyTicket(){
        // url 组成： 服务提供者的name + 注册的服务
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return "购买了" + s;
    }

}
