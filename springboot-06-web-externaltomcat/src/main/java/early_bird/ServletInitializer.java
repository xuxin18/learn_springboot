package early_bird;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//传入 SpringBoot应用的主程序类
		return application.sources(Springboot06WebExternaltomcatApplication.class);
	}

}
