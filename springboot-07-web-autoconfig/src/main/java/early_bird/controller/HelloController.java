package early_bird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello", "你好");
        map.put("templates", "<h1>模板引擎</h1>");
        map.put("users", Arrays.asList("张三", "李四"));
        //打开的页面的位置为： classpath:/templates/success.html
        return "success";
    }
}
