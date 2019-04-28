package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird
 * @date 25 15:${MIMUTE}
 * @modified
 *
 * @SpringBootApplication 来标注一个主程序类，说明这是一个 springboot应用的入口类
 *
 * @SpringBootApplication 注解的重要组成：
 *      @SpringBootConfiguration：标注这是一个Spring Boot的配置类
 *          @SpringBootConfiguration -> @Configuration -> @Component : 可以发现：配置类也是容器中的一个组件
 *
 *      @EnableAutoConfiguration：开启自动配置功能
 *
 *          @Import(EnableAutoConfigurationImportSelector.class) ： 选择器-决定了应该导入哪些组件
 *              通过 selectImports 方法可以看出：这些组件（自动配置类： xxxAutoConfiguration）会被添加到容器中,并配置好这些组件后，
 *                  以数组（存放组件的全类名）方式返回；
 *                  selectImports() -> getCandidateConfigurations() -> SpringFactoriesLoader.loadFactoryNames() 可以看出：
 *                      springboot在启动的时候会从 /spring-boot-autoconfigure-1.5.9.RELEASE.jar/META-INF/spring.factories 中
 *                      获取 EnableAutoConfiguration 指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效了，帮我们进行自动
 *                      配置（以前需要我们自己配置的东西，自动配置都帮我们配置好了）。
 *                  j2ee的整体整合方案和自动配置方案都在 spring-boot-autoconfigure-1.5.9.RELEASE.jar
 *
 *          @AutoConfigurationPackage -> @Import(AutoConfigurationPackages.Registrar.class)
 *          说明：@Import 是 Spring 底层注解，作用是给容器中导入一个组件。导入的组件为 AutoConfigurationPackages.Registrar.class
 *               通过 @AutoConfigurationPackage类 中的代码发现：
 *                  @AutoConfigurationPackage 的作用就是：将主配置类（即 @SpringBootApplication 标注的类）所在的包及下面的子包里面所有的
 *                      组件扫面到 spring容器中
 */

@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args) {
        //启动springboot应用
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
