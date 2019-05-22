package early_bird.mapper;

import early_bird.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

/*
    配置版 mybatis
*/
@Mapper //@Mapper或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
