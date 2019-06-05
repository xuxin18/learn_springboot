package early_bird.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.service
 * @date 2019/6/5 10:04
 * @description
 */
@Service
public class ScheduledService {
    /*
     cron 表达式 从左到右依次代表： 秒、分、时、日期（day of month）、月、星期（day of week）
        0：代表 整时
        *：代表 任意
        /：代表 步长
        ,：代表 枚举
        -：代表 区间
        ？：代表 冲突匹配
        在星期中：0和7代表周日，1-6代表周一到周六
    */
    @Scheduled(cron = "*/5 * * ? * 1-6") //每个月的周一到周六，每隔5s启动一次 （不是每一天都在周一到周六区间，所以用 ？ 进行冲突匹配）
    public void hello(){
        System.out.println("hello...");
    }
}
