package early_bird;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird
 * @date 07 11:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) {
        Person p = new Person();
        List<Person> list = new ArrayList<>();

        for (int i=0; i<10; i++){
            p.setName("二狗子" + i);
            p.setAge("5");
            list.add(p);
        }

        System.out.println(list);
    }

    static class Person{
        String name;
        String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
