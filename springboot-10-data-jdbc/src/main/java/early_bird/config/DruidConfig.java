package early_bird.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.config
 * @date 2019/5/21 17:26
 * @description 使用 Druid 连接池的，如果不自定义配置类，系统只会加载
 * 配置文件中数据源的基本配置，为了让我们数据源的一些扩展配置（例如：连接池的配置、监控的配置）生效，所以要自定义一个数据源的配置类
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /*
        配置Druid的监控
            1.管理后台的Servlet： StatViewServlet
            2.web监控的filter： WebStatFilter

    */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //访问路径：http://localhost:8080/druid/
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        initParams.put("allow", "");//默认就是允许所有访问
        initParams.put("deny", "10.168.0.52");//拒绝某个ip访问
        bean.setInitParameters(initParams);

        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");//排除拦截静态资源和druid的后台管理（/druid/*）相关请求
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));//排除上面的静态资源和druid后台管理相关的请求外，拦截所有请求

        return bean;
    }

}
