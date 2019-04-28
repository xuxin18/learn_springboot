package early_bird.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.controller
 * @date 25 20:${MIMUTE}
 * @modified
 *
 * @RestController = @Controller + @ResponseBody
 *      @ResponseBody ： 将返回值（如果返回值为 对象 则换为 json 格式）写入到 response 的 请求体中
 */

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World Quick";
    }
}
