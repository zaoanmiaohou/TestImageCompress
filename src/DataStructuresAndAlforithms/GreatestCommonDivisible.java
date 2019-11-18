package DataStructuresAndAlforithms;

/**
 * description
 *
 * @user cathy
 * @date 2019-11-18 18:15:46
 */
public class GreatestCommonDivisible {

    /**
     * 使用暴力枚举法求出最大公约数
     * @param a
     * @param b
     * @return
     */
    private static int getGreatestCommonDivisible(int a, int b){
        int big = a;
        int small = b;
        if (big % small == 0){
            return small;
        }
        for (int i = small/2; i >1; i--){
            if (big % i == 0 && small % i == 0){
                return i;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisible(25, 5));
        System.out.println(getGreatestCommonDivisible(100, 10));
        System.out.println(getGreatestCommonDivisible(27, 14));
    }
}
