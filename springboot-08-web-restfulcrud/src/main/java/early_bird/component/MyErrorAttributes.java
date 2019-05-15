package early_bird.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.component
 * @date 2019/5/15 18:00
 * @description 在容器中加入 自定义的ErrorAttributes
 * 出现错误后，如果容器中
 *      没有AbstractErrorController类型的 bean，那么就会由 BasicErrorController 处理请求，
 *          通过对BasicErrorController中的 errorHtml 和 error 方法分析，其返回的数据都是由 getErrorAttributes 方法构造
 *              如果容器中没有 ErrorAttributes 类型的 bean 则，创建 DefaultErrorAttributes 的 bean实体，由该实体的
 *              getErrorAttributes 方法 将数据构造返回给 ModelAndView（视图解析器）
 *
 * 通过上面这个流程，我们可以看出如果想要将我们自定义的数据返回给ModelAndView，有两种方式可以实现我们的需求：
 *      1.写一个 AbstractErrorController 类型的 bean
 *      2.写一个 ErrorAttributes 类型的 bean
 *      这两种方式第二种更简单
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes{

    /*
        返回值map 就是页面和json所能获取的所有字段
    */
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("MyErrorAttributes","自定义 errorAttributes");
        // 0: 代表从 request域获取信息
        Map<String, Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
        map.put("ext", ext);
        return map;
    }
}
