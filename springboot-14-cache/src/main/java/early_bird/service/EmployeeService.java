package early_bird.service;

import early_bird.bean.Employee;
import early_bird.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.service
 * @date 2019/5/27 11:11
 * @description
 */

@CacheConfig(cacheNames = "emp") // 抽取缓存的公共配置
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

   /*
       Cacheable 的几个属性：
            cacheNames/value: 指定缓存组件的名字（将方法的返回结果放在哪个cache中，cacheName类型为String类型的数组，即可以指定多个cache）
            key: 指定缓存数据使用的 key；默认是使用方法的参数的值，也可以自己指定
            keyGenerator:key生成策略 （key 和 keyGenerator 只能存在一个）
            cacheManager: 指定缓存管理器
            cacheResolver: 指定缓存解析器（cacheResolver 或 cacheManager 只能存在一个）
            condition: 生成缓存的条件
            unless:不生成缓存的条件（可以获取方法的返回值来做判断）
            sync：是否使用同步模式（默认为否，即默认使用异步模式，当开启同步模式的时候，不能支持unless）



    原理：
	查看自动配置类：CacheAutoConfiguration
		主要是导入了 CacheConfigurationImportSelector：该类的作用是 根据 当前ioc容器的环境，生效不同缓存配置
		默认生效的是：SimpleCacheConfiguration。
			SimpleCacheConfiguration 给ioc容器中注册了一个 CacheManager
				CacheManager： getCache 方法可以根据名称来获取或者创建缓存组件（数据保存在 ConcurrentMap中）
				可以在 ConcurrentMapCacheManager 的 getCache 方法打断点 和
					  ConcurrentMapCache 的 put 方法 打断点 来调试
    运行流程：
    @Cacheable
        1.方法运行之前，先按照 cacheNames 去 CacheManager中查询 Cache（缓存组件），如果没有，则创建
            CacheManager 如果没有指定则默认为 ConcurrentMapCacheManager ,Cache 则默认为 ConcurrentMapCache
        2.去 Cache 中使用 key 查找 缓存的内容。（key 是按照某种策略生成，默认使用 SimpleKeyGenerator 生成）
            SimpleKeyGenerator生成key的默认策略：
                如果没有参数：key = new SimpleKey()
                如果有一个参数：key = 参数的值
                如果有多个参数：key = new SimpleKey(params)
        3.在Cache 中没查找到。则调用目标方法（例如： getEmp）
        4.将目标方法的返回结果，放进Cache中
        5.以后再来调用目标方法时，就可以直接从 Cache 中获取数据，而不再去查询数据库了

         ps: #result：获取方法的返回结果
            condition = "#a0>1" 当目标方法的第一个参数>1 的时候生成缓存.
                这个地方可以使用 spEL 表达式写很多判断条件
            unless = "#a0==2" 当目标方法的第一个参数 =2的时候不生成缓存
   */
    //@Cacheable(cacheNames = {"emp","temp"}, key = "#root.methodName + '[' + #id + ']'")
    @Cacheable(cacheNames = {"emp","temp"}, keyGenerator = "myKeyGenerator", condition = "#a0>1", unless = "#a0==2")
    public Employee getEmp(Integer id){
        System.out.println("查询" +"id"+ "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /*
        @CachePut: 即调用方法，又更新缓存数据
        运行流程：
            先调用目标方法
            后将目标方法的结果缓存起来
    */
    @CachePut(cacheNames = "emp", key = "#result.id")
    public Employee updateEmp(Employee e){
        System.out.println("更新：" + e);
        employeeMapper.updateEmp(e);
        return e;
    }

    /*
        @CacheEvict: 清除缓存
            可以通过 key 指定需要清除的数据
            allEntries = true ：清除当前缓存（emp）所有数据
            beforeInvocation: 缓存是否在方法开始执行之前清除--- true是，false否
                true：也就是说，如果当前方法在执行的过程中出错，还是会清除缓存
                false：则是，如果当前方法执行出错，不清除 缓存
        运行流程：
            先清除缓存数据
            再清除数据库数据
    */
    //@CacheEvict(cacheNames = "emp", key = "#id")
    @CacheEvict(cacheNames = "emp", allEntries = true, beforeInvocation = true)
    public void deleteEmp(Integer id){
        System.out.println("删除：" + id);
        //employeeMapper.deleteEmpById(id);
    }

    /*
        @Caching: 组合注解
            这个例子中：
                当第一次调用 getEmpByLastName 时，缓存Cache中就添加了3个键值对：
                    lastName-Employee
                    id-Employee
                    email-Employee
                所以，之后再根据 id 或者 email 来查询数据，都不需要去数据库中查询
                但是，当 再次调用 getEmpByLastName 方法时，还是会去数据库中查询
                    因为 被 @CachePut 标注的方法被调用时，方法体一定会执行
    */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }
}
