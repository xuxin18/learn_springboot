package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(value = "early_bird.mapper") // 使用 @MapperScan 指定扫描mapper所在的包，这样就可以不用在 Dao层的 接口上加@Mapper注解
@SpringBootApplication
public class Springboot11DataMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot11DataMybatisApplication.class, args);
	}

}
