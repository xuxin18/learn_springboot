package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
SpringBoot 默认的错误处理机制：
	浏览器访问，出现错误，默认返回一个错误页面 Whitelabel Error Page
	客户端访问，默认响应一个 json 数据

	原理：参见 ErrorMvcAutoConfiguration（错误处理自动配置）
		ErrorMvcAutoCOnfiguration 帮我们给容器中添加了以下组件：
			DefaultErrorAttributes
				该类的 getErrorAttributes 方法，帮我们在页面填充错误信息
			BasicErrorController（处理 error相关请求）
				如果是浏览器请求，则处理方法为 errorHtml
				如果是客户端请求，则处理方法为 error
					浏览器请求 与 客户端请求的 主要区别是：
						浏览器请求时，请求头中 RequestHeaders 的 Accept 属性中有 text/html
						客户端请求时，请求头中 RequestHeaders 的 Accept 属性中有  * / *
						这也是BasicErrorController对浏览器和客户端浏览时，能返回不同的数据格式信息的原因
			ErrorPageCustomizer
				该类的 registerErrorPages 方法可以看出当系统出现错误（this.properties.getError().getPath()）以后来到error请求进行处理（自定义了一个页面返回）
			DefaultErrorViewResolver
				resolveErrorView方法：将 ErrorViewResolver 解析得到 ModelAndView（即决定了错误响应内容）
				resolver 方法：决定了响应内容
					在该方法中可以看出：
						对默认的错误页面 ，系统中引入了模板引擎，则由模板引擎对页面进行解析后返回；如果没有则在静态文件夹下(resource.static.error)找到对应的页面进行处理后返回
						如果是自定义的错误页面，如果想要让模板引擎解析，则需要放在 resource.template.error 目录下

	如何定制错误响应：
		1.在有模板引擎的情况下：
			将错误页面命名为 错误状态码.html 放在模板引擎文件夹（template文件夹）里面的 error文件夹下，，发生此状态的错误码就会来到对应的页面
				我们也可以用 4xx 和 5xx 作为错误页面的文件名来匹配这种类型的所有错误（DefaultErrorViewResolver 的 SERIES_VIEWS 提供的支持）
				优先寻找 精确的状态码.html
				页面能获取的信息（参见 DefaultErrorAttributes 的 getErrorAttributes 方法）：
					timestamp：时间戳   status：状态码  error：错误提示  exception：异常对象   message：异常消息
					errors：JSR303数据校验的错误
		2.没有模板引擎的情况下 去 resource.template.error 目录下匹配错误码
		3.如果以上目录都没有错误页面，则默认来到springboot的默认的错误提示页面
			当浏览器访问后端出现异常时：代码流为：
				BasicErrorController 的 errorHtml 方法 当调用resolveErrorView 方法解析出 modelAndView 为null时，默认
				返回new ModelAndView("error", model)，根据 ViewName 为 error 找到 ErrorMvcAutoConfiguration 中的
				defaultErrorView 方法，该方法会构造出一个 默认视图（defaultErrorView）返回

*/
@SpringBootApplication
public class Springboot08WebRestfulcrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot08WebRestfulcrudApplication.class, args);
	}

}
