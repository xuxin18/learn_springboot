package early_bird.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.component
 * @date 13 11:${MIMUTE}
 * @modified
 * 扩展 springmvc中的国际化区域信息 组件
 * 可以在连接上携带区域信息
 */
public class MyLocaleResolver implements LocaleResolver{

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
