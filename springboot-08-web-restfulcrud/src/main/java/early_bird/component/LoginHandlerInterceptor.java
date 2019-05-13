package early_bird.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.component
 * @date 2019/5/13 14:37
 * @description 通过拦截器来进行登录检查：未登录，不允许访问主页
 */
public class LoginHandlerInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null){
            request.setAttribute("msg", "没有权限请先登录");
            //转发到登录页面
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
        //已登录，放行请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
