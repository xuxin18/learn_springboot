package early_bird.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.controller
 * @date 2019/6/5 17:49
 * @description
 */
@RestController
public class HelloController {

    /*
        引入了 devtools 后，当修改一些文件后，只需要 ctrl + F9 就能实现热部署功能
    */
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
