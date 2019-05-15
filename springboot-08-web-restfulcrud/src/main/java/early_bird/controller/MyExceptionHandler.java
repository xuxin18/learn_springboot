package early_bird.controller;

import early_bird.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.controller
 * @date 2019/5/15 16:23
 * @description 自定义 异常处理器
 * 控制器增强： @ControllerAdvice ，它是一个 @Component 注解，一般常用于配合 @ExceptionHandler 使用
 *              如果我们只使用 @ExceptionHandler 则只能在当前类中处理异常，但是配合 @ControllerAdvice后
 *              可以在所有的类中使用
 */
@ControllerAdvice
public class MyExceptionHandler {

    /*
        这种写法 浏览器 和 客户端 返回的都是 json格式数据，没有自适应效果
        对 同一个异常只能做一种处理
    */
    /*@ResponseBody
    @ExceptionHandler(UserNotExistException.class) // 对 UserNotExistException 异常进行自定义处理
    public Map<String, Object> handleException(Exception e){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        return map;
    }*/

    @ExceptionHandler(UserNotExistException.class)
    public String handleException2(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        map.put("code", "user.notexist");
        map.put("message", "自定义异常处理的附带信息");
        /*
            传入我们自己的错误状态码，4xx 5xx，否则就不会进入定制的错误页面解析流程（具体解析，可以查看 BasicErrorController 的
                errorHtml 或 error 方法中 getStatus(request) 方法）
        */
        request.setAttribute("javax.servlet.error.status_code", 500);

        /* 将 定制的信息 放入 request域中，然后让 MyErrorAttributes 获取后，视图解析器*/
        request.setAttribute("ext", map);


        /*
            为什么 转发到 /erroe 就能有自适应效果呢？
                因为 转发到 /error 时，会去调用 /error 对应 的 BasicErrorController 类的方法
        */
        return "forward:/error";
    }
}
