package early_bird;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird
 * @date 2019/6/18 15:52
 * @description
 */
public class Main {
    public static void main(String[] args) {
        A a = new A("张三");
        a.get();
        A b = new A();
        b.get();
    }
}

class A{
    String name;

    public A(String name) {
        this.name = name;
    }

    public A() {
    }

    public void get(){
        System.out.println(name);
    }
}