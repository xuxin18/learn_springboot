package early_bird.mapper;

import early_bird.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.mapper
 * @date 2019/5/22 15:59
 * @description
 */
@Mapper  //指定这是一个操作数据库的mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id = #{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id") // 这样可以将自增长的主键 id 的值返回
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
