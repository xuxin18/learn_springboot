package early_bird.repository;

import early_bird.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.repository
 * @date 2019/5/23 10:32
 * @description 继承 JpaRepository 来操作数据库
 */
public interface UserRepository extends JpaRepository<User, Integer>{ // 第二个参数 为 id 的数据类型
}
