package early_bird.learn_autoconfig;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.HttpEncodingProperties;
import org.springframework.boot.autoconfigure.web.HttpEncodingProperties.Type;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for configuring the encoding to use
 * in web applications.
 *
 * @author Stephane Nicoll
 * @author Brian Clozel
 * @since 1.2.0
 * Spring底层的 @Conditional注解：如果满足指定的条件，那么该配置类里的配置才会生效
 *
 * 自动配置的总结：
 * 		SpringBoot启动会加载大量的自动配置类
 * 		看我们需要的功能有没有SpringBoot默认写好的自动配置类
 * 		再来看这个自动配置类中到底配置了哪些组件；（只要我们要用的组件有，我们就不需要再来配置了）
 * 		给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们就可以在配置文件中指定这些属性的值；
 * 			***AutoConfiguration 会从 ***Properties 中获取相关属性
 * 			例如：	HttpEncodingAutoConfiguration 配置类会从 HttpEncodingProperties 配置文件中获取属性值
 */
@Configuration //表示这是一个配置类
@EnableConfigurationProperties(HttpEncodingProperties.class) //启用 HttpEncodingProperties.class 类的 ConfigurationProperties的功能（
												// 从配置文件中获取指定的值和bean的属性进行绑定），并将 HttpEncodingProperties 加入到ioc容器中
@ConditionalOnWebApplication //判断当前应用是否为 web应用，如果是，则当前配置生效
@ConditionalOnClass(CharacterEncodingFilter.class) //判断当前项目中有没有 CharacterEncodingFilter类（springmvc中解决乱码的过滤器），如果有，则配置生效
@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true) //判断配置文件中是否存在spring.http.encoding.enabled 配置项
																			// （如果不存在spring.http.encoding.enabled=true，则默认spring.http.encoding.enabled=true生效），让配置生效
public class HttpEncodingAutoConfiguration {

	// 通过 @EnableConfigurationProperties 注解，将变量 properties 与 配置文件进行了映射
	private final HttpEncodingProperties properties;

	public HttpEncodingAutoConfiguration(HttpEncodingProperties properties) {
		this.properties = properties;
	}

	@Bean  //给容器中添加一个bean（组件），这个bean（组件）的一些属性，需要从 properties中获取
	@ConditionalOnMissingBean(CharacterEncodingFilter.class)
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
		filter.setEncoding(this.properties.getCharset().name());
		filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
		filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
		return filter;
	}

	@Bean
	public LocaleCharsetMappingsCustomizer localeCharsetMappingsCustomizer() {
		return new LocaleCharsetMappingsCustomizer(this.properties);
	}

	private static class LocaleCharsetMappingsCustomizer
			implements EmbeddedServletContainerCustomizer, Ordered {

		private final HttpEncodingProperties properties;

		LocaleCharsetMappingsCustomizer(HttpEncodingProperties properties) {
			this.properties = properties;
		}

		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			if (this.properties.getMapping() != null) {
				container.setLocaleCharsetMappings(this.properties.getMapping());
			}
		}

		@Override
		public int getOrder() {
			return 0;
		}

	}

}
