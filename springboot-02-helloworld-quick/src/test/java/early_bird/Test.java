package early_bird;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuxin
 * @version v1.0
 * @project learn_springboot
 * @package early_bird
 * @date 26 17:${MIMUTE}
 * @modified
 */
public class Test {
    public static void main(String[] args) {

        List list = new ArrayList<>();
        System.out.println(list.hashCode());
        addS(list);
        System.out.println(list.hashCode());


    }

    public static void addS(List list){
        list.add("sss");
    }
}
