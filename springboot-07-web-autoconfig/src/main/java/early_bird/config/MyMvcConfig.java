package early_bird.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.config
 * @date 09 18:${MIMUTE}
 * @modified
 *
 * 如何扩展 SpringMvc的功能？
 *      编写一个配置类（@Configuration），是 WebMvcConfigurerAdapter 类型，不能使用 @EnableWebMvc 注解。
 *      这种配置方式，即保留了所有的自动配置，也能用我们自己的扩展配置
 *          原理：
 *              1. WebMvcAutoConfiguration 是 SpringMvc 的自动配置类 中的静态内部类 WebMvcAutoConfigurationAdapter
 *                  在做自动配置时会导入 @Import(EnableWebMvcConfiguration.class)
 *              2. EnableWebMvcConfiguration 类继承了 DelegatingWebMvcConfiguration，该类的 setConfigurers 会将所有
 *                  的 WebMvcConfigurer 相关配置 全部存放到 list 集合中，然后将这些配置遍历调用
 *                  这样 SpringMvc 的自动配置和我们的扩展配置都会起作用
 *
 *  如果想要全面接管 SpringMVC，那么在配置类中添加 @EnableWebMvc 即可，因为 @EnableWebMvc 注解 使用了 @Import(DelegatingWebMvcConfiguration.class)
 *      当使用 DelegatingWebMvcConfiguration 类时，会自动创建 WebMvcConfigurationSupport 组件
 *      而 WebMvcAutoConfiguration 只有在 容器中没有 WebMvcConfigurationSupport 组件时才生效
 *
 *
 *
 *
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // http://localhost:8080/myselfpath 会访问 classpath:templates/success.html
        registry.addViewController("/myselfpath").setViewName("success");
    }
}
