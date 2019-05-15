package early_bird.controller;

import early_bird.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.controller
 * @date 10 11:${MIMUTE}
 * @modified
 */
@Controller
public class HelloController {

    /*
        这样配置后，结果视图 会去 classpath: templates 下查找 html 页面，找不到就报错
        还有一种实现方式：
            自己扩展 WebMvcAutoConfiguration 的功能，具体实现方式见 MyMvcConfig
    */
    @RequestMapping({"/xxx","/yyy/zzz"})
    public String index(){
        return "login";
    }

    /**
      * @author xuxin
      * @date   2019/5/13 11:47
      * @Description
      * @param  
      * @return  
      */
    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello", "你好");
        map.put("templates", "<h1>模板引擎</h1>");
        map.put("users", Arrays.asList("张三", "李四"));
        //打开的页面的位置为： classpath:/templates/success.html
        return "success";
    }

    /* 模拟抛出用户不存在的异常 在没自定义异常处理器的时候，输出的异常信息是默认的
       http://localhost:8081/crud/hello?user=aaa */
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        if (user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "Hello World";
    }
}
