package early_bird.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.bean
 * @date 26 10:${MIMUTE}
 * @modified
 *
 * 将配置文件中配置的 一些属性的值 映射到这个 组件中
 *      @ConfigurationProperties：告诉springboot将本类中所有的属性与配置文件中相关的配置进行绑定（默认从全局配置文件中获取）
 *          prefex: 指定 配置文件中的哪个配置与当前类对应
 *      @ConfigurationProperties 还支持jsr303数据校验校验，即支持 @Validated 注解
 *
 * 这里为什么还要用 @Component？
 *      只有这个组件是容器中的组件时，才能使用 容器提供的 @ConfigurationProperties 的功能
 *
 * 除了 @ConfigurationProperties 可以加载 配置文件外，还有两个注解可以加载配置文件：
 *      @PropertySource：加载指定的配置文件
 *      @ImportResource：导入spring的配置文件，让配置文件里面的内容生效（写在 启动类上）
 *          springboot中没有spring的配置文件，我们自己写的配置文件也不能识别，想让spring配置文件生效，
 *          则 使用 @ImportResource 标注在 启动类上
 *
 *          注意：springboot推荐给容器中添加组价的方式为 全注解的方式：
 *                  1.使用配置类 @Configuration 来替代 spring的xml配置文件
 *                  2.使用 @Bean 给容器中添加组件
 *
 * 配置文件中还支持随机数和占位符:
 *      例如：
 *          person.last-name=张三${random.uuid}
 *          person.age=${random.int(100)}
 *          person.dog.name=${person.hello:hello}_dog :这个狗的名字为：取 person.hello 的value + '_dog' 拼接；
 *                                                                   如果配置文件中没有 person.hello,则狗的名字为：'hello_dog'
 *
 */

@PropertySource(value = {"classpath:person.properties"})
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
    @NotBlank
    private String name;
    private int age;
    private boolean boss;
    private Date birth;
    private Map<String, String> electronic_product;
    private Map<String, String> favorite;
    private List<String> friends;
    private List<String> pets;
    private List<Person> children;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", electronic_product=" + electronic_product +
                ", favorite=" + favorite +
                ", friends=" + friends +
                ", pets=" + pets +
                ", children=" + children +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, String> getElectronic_product() {
        return electronic_product;
    }

    public void setElectronic_product(Map<String, String> electronic_product) {
        this.electronic_product = electronic_product;
    }

    public Map<String, String> getFavorite() {
        return favorite;
    }

    public void setFavorite(Map<String, String> favorite) {
        this.favorite = favorite;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getPets() {
        return pets;
    }

    public void setPets(List<String> pets) {
        this.pets = pets;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }
}
