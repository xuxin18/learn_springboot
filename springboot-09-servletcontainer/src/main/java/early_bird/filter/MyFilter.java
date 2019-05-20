package early_bird.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.filter
 * @date 2019/5/16 14:57
 * @description 自定义 Filter
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter 启用...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
