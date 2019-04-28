package early_bird.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird.bean
 * @date 26 14:${MIMUTE}
 * @modified
 *
 * @Value 是spring底层注解，作用：将对应的值注入到变量中
 *      @Value
 *          不支持获取复杂的封装类型（例如：map）
 *          不支持校验
 */

@Component
public class Book {

    // @Value("${}"): 获取对应属性文件中定义的属性值（一般从配置文件总获取）
    @Value("${book.name}")
    private String name;

    @Value("烽火戏诸侯")
    private String author;

    // @Value("#{}"): SpEL表达式；通常用来获取bean的属性，或者调用bean的某个方法,还可以表示常量
    @Value("#{0.05*1200}")
    private Double price;

    @Value("#{'天不生我李淳罡 剑道万古如长夜'}")
    private String description;

    @Value("true")
    private boolean popular;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", popular=" + popular +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }
}
