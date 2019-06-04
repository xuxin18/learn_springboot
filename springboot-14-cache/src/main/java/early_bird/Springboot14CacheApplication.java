package early_bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*
搭建环境：
	1.导入数据库文件，创建出 department 和 employ 表
	2.创建 javaBean 封装数据
	3.整合MyBatis 操作数据库
		1.配置数据源信息
		2.使用注解版MyBatis
			@MapperScan 指定需要扫描的 mapper 接口所在的包

使用缓存：
	1.开启基于注解的缓存 @EnableCaching
	2.标注缓存注解即可
		@Cacheable 主要针对方法配置，能够根据方法的请求参数，对其结果进行缓存
		@CacheEvict: 清空缓存
		@CachePut 保证方法被调用，又希望结果被缓存
	3.一些其他概念：
		CacheManager：缓存管理器，管理各种缓存组件，每个缓存组件都有自己的唯一名字， 对于缓存的真正CRUD操作是在Cache组件中。
		keyGenerator：缓存数据时key生成策略
		serialize：缓存数据时value序列化策略

开发过程中常用的缓存中间件：redis、memcache、ehcache
	redis 是内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件
整合 Reis 缓存：
	1.使用docker 安装redis
	2.引入 redis 的 starter
	3.配置redis
	当整合 Redis 作为缓存是：
		1.CacheManager 由 默认的 ConcurrentMapCacheManager 改为了 RedisCacheManager （详见 RedisCacheConfiguration）
		2.RedisCacheManager 创建 RedisCache 来作为缓存组件
		3.默认数据 k-v 都是 Object 时，利用 jdk的序列化来保存数据
			RedisCacheManager 操作 redis 是默认 使用的是 RedisTemplate<Object, Object>
				RedisTemplate<Object, Object> 的创建 详见 RedisConfiguration
				而 RedisTemplate<Object, Object> 默认使用的序列化器就是 jdk的序列化器
		所以如果我们需要指定 以json的格式来保存数据，可以通过定制 RedisCacheTemplate 方法是实现 （在 RedisCacheManager中指定使用 自己定制的 redisTemplate）

*/
@MapperScan("early_bird.mapper")
@SpringBootApplication
@EnableCaching
public class Springboot14CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot14CacheApplication.class, args);
	}

}
