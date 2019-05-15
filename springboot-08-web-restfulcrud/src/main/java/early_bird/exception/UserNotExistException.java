package early_bird.exception;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird.exception
 * @date 2019/5/15 15:54
 * @description 自定义一个 用户不存在异常 继承 RuntimeException（让它在运行时能抛出）
 */
public class UserNotExistException extends RuntimeException {
    public UserNotExistException(){
        super("用户不存在");
    }
}
