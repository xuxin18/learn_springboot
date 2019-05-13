package early_bird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
