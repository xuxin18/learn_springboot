package early_bird;

/**
 * @author xuxin
 * @project learn_springboot
 * @package early_bird
 * @date 2019/5/29 20:44
 * @description
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(rup_1(8));
        System.out.println(rup(8));
        System.out.println(rup_1(16));
        System.out.println(rup(16));

    }

    private static int rup_1(int number){
        return number >= (1 << 30)
                ? (1 << 30)
                : (number > 1)? Integer.highestOneBit((number-1)<<1):1;
    }

    private static int rup(int number){
        return number >= (1 << 30)
                ? (1 << 30)
                : (number > 1)? Integer.highestOneBit(number<<1):1;
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }
}
