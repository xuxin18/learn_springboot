package early_bird.controller;

import early_bird.dao.DepartmentDao;
import early_bird.dao.EmployeeDao;
import early_bird.entities.Department;
import early_bird.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.controller
 * @date 2019/5/13 15:16
 * @description
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        //将返回结果放在请求域中
        model.addAttribute("emps",employees);
        //thymeleaf 会默认拼接路径，拼接后的结果为 /templates/***.html （具体参考 ThymeleafProperties 类的 DEFAULT_PREFIX 属性）
        return "/emp/list";
    }

    //先跳转到 员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        return "emp/add";
    }
}
