package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Arrays;
import java.util.Locale;

/*
	springboot 自动配置好了 springmvc（具体参见 WebMvcAutoConfiguration 类）：
		自动配置了 ContentNegotiatingViewResolver 和 BeanNameViewResolver，即自动配置了视图解析器(根据方法的返回值得到视图对象 View，视图对象决定如何渲染)
			ContentNegotiatingViewResolver：组合所有的视图解析器。
				我们可以自己给容器中添加一个视图解析器，如果ContentNegotiatingViewResolver 就会自动将其组合进来
		对 静态资源文件夹路径、静态首页访问、以及图标等的支持
		自动注册了 Converter、GenericConverter、Formatter 等
			Converter：转换器，将前端传过来的数据转换成我们需要的格式、数据类型
				例如: HttpMessageConverter：SpringMVC用来转换Http请求和响应的；json---User---Json
			Formatter：日期格式化器，将日期转换为我们需要的格式
				自己添加的格式化器、转换器等，只需要放在容器中即可（@Bean、@Component 注解都能达到效果）
		定义了错误代码的生成规则 MessageCodesResolver
		我们也可以配置一个 ConfigurableWebBindingInitializer 到容器中，来替换容器中默认的 web数据绑定器（WebDataBinder：将web数据转换为 javabean）

	为什么我们自己写的 这些组件会自动生效？
		这个方法很重要：
			getBeansOfType(Class<T> type)： 根据传入的Class文件的 类型，返回容器中所有的属于该类型的 bean。
			然后 springboot 遍历所有的组件，自动加载，所以它们都能自动生效

	ps：如果自动配置类中，只有一个构造函数，那么构造函数中的每一个参数都是从容器中获取

	如何修改SpringBoot的默认配置？
		SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（@Bean、@Component）如果有就用用户配置的，如果没有，才自动配置；如果某些组件可以有多
			个（ViewResolver）则将用户配置的和自己默认的组合起来；
		在SpringBoot中会有非常多的 ***Configurer 帮助我们进行扩展配置
		在SpringBoot中会有很多的xxxCustomizer帮助我们进行定制配置
*/
@SpringBootApplication
public class Springboot07WebAutoconfigApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Springboot07WebAutoconfigApplication.class, args);
		//System.out.println(ctx.getBean("delegatingWebMvcConfiguration"));
		String[] ss = ctx.getBeanDefinitionNames();
		//System.out.println(ctx.getBean("webMvcConfigurationSupport"));
		System.out.println(Arrays.asList(ss));
	}


	@Bean
	public ViewResolver myViewResolver(){
		return new MyViewResolver();
	}

	//自定义视图解析器 需要实现 ViewResolver接口。可以在 DispatcherServlet 类中 的 doDispatch方法查看我们自己定义的 解析器是否加载进了容器中
	private static class MyViewResolver implements ViewResolver{
		@Override
		public View resolveViewName(String viewName, Locale locale) throws Exception {
			return null;
		}
	}

}
