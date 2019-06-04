package early_bird.mapper;

import early_bird.bean.Department;
import org.apache.ibatis.annotations.Select;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.mapper
 * @date 2019/5/27 10:49
 * @description
 */
public interface DepartmentMapper {
    @Select("SELECT * FROM department WHERE id = #{id}")
    Department getDeptById(Integer id);
}
