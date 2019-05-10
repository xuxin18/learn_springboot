package early_bird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.controller
 * @date 08 18:${MIMUTE}
 * @modified
 *
 * thymeleaf的使用：
 *      1.导入名称空间 xmlns:th="http://www.thymeleaf.org"
 *      2.使用thymeleaf语法
 *      3.语法规则
 *          th:任意html属性         替换原生属性的值
 *              例如：success.html 中 经过模板引擎处理后 “你好” 会替换 “显示欢迎信息”
 *              更多 th 相关用法：
 *                  Fragment inclusion      片段包含（相当于jsp的 include标签）
 *                      th:insert
 *                      th:replace
 *                  Fragement iteration     遍历（相当于 jsp中的 c:forEach）
 *                      th:each
 *                  Conditional evaluation   条件判断  （相当于 jsp中的 c:if）
 *                      th:if
 *                      th:unless
 *                      th:switch
 *                      th:case
 *                  General attribute modification 任意属性修改
 *                      th:attr
 *                      th:attrprepend
 *                      th:attrappend
 *                  Specific attribute modification 修改指定属性默认值
 *                      th:value
 *                      th:href
 *                      th:src  等
 *                  Text    修改标签体内容
 *                      th:text 转义特殊字符
 *                      th：utext 不转义特殊字符
 *                  Fragment specification 声明片段
 *                      th:fragment
 *                  Fragment specification 移除片段
 *                      th:remove
 *             表达式：
 *                  ${...}：获取变量值（支持ognl）
 *                      1.获取对象的属性、调用方法
 *                      2.使用内置的基本对象
 *                          #ctx
 *                          #vars
 *                          #locale
 *                          #request
 *                          #response
 *                          #session
 *                          #ServletContext
 *                      3.使用内置的工具对象
 *                  *{...}：选择表达式（和 ${...}在功能是是一样的）
 *                      例如：
 *                          <div th:object="${session.user}">
 *                               <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
 *                           </div>
 *                           相当于：
 *                           <div>
 *                               <p>Name: <span th:text="${session.userfirstName}">Sebastian</span>.</p>
 *                           </div>
 *                  #{...}：获取国际化内容
 *                  @{...}：定义url
 *                  ~{...}：片段引用表达式
 *                      例如：<div th:insert="~{commons :: main}">...</div>
 */
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
