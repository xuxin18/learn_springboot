package early_bird.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.health
 * @date 2019/6/5 20:03
 * @description
 */
@Component
public class MyHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        /*
        自定义健康检查状态
            Health.up().build() 代表健康
        */
        return Health.down().withDetail("msg", "服务异常").build();
    }
}
