package early_bird.controller;

import early_bird.dao.DepartmentDao;
import early_bird.dao.EmployeeDao;
import early_bird.entities.Department;
import early_bird.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //添加员工。springmvc能自动将请求参数和入参对象的属性进行一一绑定（前提：请求参数的名字和 javabean入参的对象里面的属性名是一样的）
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息：" + employee);
        employeeDao.save(employee);
        //添加后来到员工列表页面。（redirect: 重定向  forword: 转发）
        return "redirect:/emps";
    }

    //先查出当前员工进行回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //去到修改页面 （add页面既可以添加也可以修改）
        return "/emp/add";
    }

    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        System.out.println("修改的员工数据" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
