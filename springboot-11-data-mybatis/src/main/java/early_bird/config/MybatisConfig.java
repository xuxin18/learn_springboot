package early_bird.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.config
 * @date 2019/5/22 16:21
 * @description 扩展 mybatis 的一些配置
 */
public class MybatisConfig {
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer(){
            @Override
            public void customize(Configuration configuration) {
                //开启 下划线 与 驼峰命名法 的转换
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
