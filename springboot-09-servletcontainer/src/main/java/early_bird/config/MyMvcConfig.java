package early_bird.config;

import early_bird.filter.MyFilter;
import early_bird.listener.MyListener;
import early_bird.servlet.MyServlet;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.config
 * @date 2019/5/16 11:49
 * @description
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    /*
        注册三大组件
            关于三大组件的一些设置都可以用 registrationBean 进行设置
    */
    @Bean // http://localhost:8082/crud/myservlet
    public ServletRegistrationBean myServletaffadfa(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(), "/myservlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myservlet"));
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }


    /*
        启动容器发现  Tomcat started on port(s): 8082 (http) 即定制器生效（优先于配置文件生效），这是为什么呢？
            在 EmbeddedServletContainerCustomizerBeanPostProcessor类的 postProcessBeforeInitialization 方法中发现在
            调用 getCustomizers() 方法时会对 我们定制的 XXXCustomizer 进行排序，而 ServerProperties 定制器 排在 MyMvcConfig 中的 定制器前面
            所以 在进行遍历 赋值时，MyMvcConfig 中的 定制的 EmbeddedServletContainerCustomizer 相关的设置，会覆盖之前的相关设置
    */
    @Bean //将自定义的定制器加入到容器中
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {

            //定制嵌入式Servlet容器相关的规则
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(8082);
            }
        };
    }
}
