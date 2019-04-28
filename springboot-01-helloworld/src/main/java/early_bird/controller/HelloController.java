package early_bird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.controller
 * @date 25 15:${MIMUTE}
 * @modified
 *
 *
 */

@Controller
@ResponseBody
public class HelloController {


    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
