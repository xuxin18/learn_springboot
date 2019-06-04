package early_bird.service;

import early_bird.bean.Department;
import early_bird.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.service
 * @date 2019/5/30 15:11
 * @description
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;


    /*
        运行后，发现缓存的数据能存入 redis
        但是 第二次从缓存中查询时不能反序列化。
        原因：
            虽然我们存入的是 Department 的 json 数据，但是 RedisCacheManager 默认使用的是 RedisTemplate<Object, Employee> 来操作 Redis
            所以，反序列化时会出错。解决方案：
                在 程序中添加与 Department 相关的 RedisCacheManager.(详见 MyRedisConfig)

    */
    /*@Cacheable(cacheNames = "dept", cacheManager = "departmentCacheManager") // 指定 该方法的 CacheManager 为 departmentCacheManager
    public Department getDepartmentById(Integer id){
        System.out.println("查询部门" + id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }*/

    /*
        除了 在注解中使用 cacheManager 来指定 缓存管理器外，我们还可以 直接指定 cacheManager 做缓存相关操作
    */

    @Autowired
    RedisCacheManager departmentCacheManager;

    public Department getDepartmentById(Integer id){
        System.out.println("查询部门" + id);
        Department department = departmentMapper.getDeptById(id);

        //使用缓存管理器得到缓存，进行 api 调用
        Cache dept = departmentCacheManager.getCache("dept");
        dept.put("dept",department);

        return department;
    }

}
