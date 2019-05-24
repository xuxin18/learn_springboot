package early_bird.entity;

import javax.persistence.*;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.entity
 * @date 2019/5/23 10:18
 * @description 实体类 User
 */

@Entity //使用 jpa注解 配置映射关系，告诉 jpa 这是一个实体类（和数据表映射的类）
@Table(name = "tb_user") // 指定和数据库中的哪个数据表对应，如果省略默认表名就是 user（类名小写）
public class User {

    @Id //表名这个字段是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自增
    private Integer id;

    @Column(name="last_name", length = 50) //这是和数据表中 的 last_name 列 对应属性名
    private String lastName;

    @Column //省略默认列名就是属性名
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
