package LeetCode;

/**
 * 342. Power of Four
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 *
 * Example 1:
 *
 * Input: 16
 * Output: true
 * Example 2:
 *
 * Input: 5
 * Output: false
 * Follow up: Could you solve it without loops/recursion?
 *
 * https://leetcode.com/problems/power-of-four/
 * Created by ZingBug on 2019/5/13.
 */
public class PowerOfFour {
    //最简单的方法，通过循环
    private boolean isPowerOfFour1(int num)
    {
        while ((num!=0)&&num%4==0)
        {
            num=num/4;
        }
        return num==1;
    }

    //不采用循环
    //4 的幂次方的数的二进制表示 1 的位置都是在奇数位。
    //使用位运算：将这个数与一个特殊的数做位运算。
    //这个特殊的数有如下特点：
    //足够大，但不能超过 32 位，即最大为 31 个 1
    //它的二进制表示中奇数位为 1 ，偶数位为 0
    //符合这两个条件的二进制数是：
    //1010101010101010101010101010101
    //如果用一个 4 的幂次方数和它做与运算，得到的还是 4 的幂次方数。
    //将这个二进制数转换成 16 进制表示：0x55555555 。
    private boolean isPowerOfFour2(int num)
    {
        if(num<=0)
        {
            return false;
        }
        //先判断是否为2的幂
        if((num&(num-1))!=0)
        {
            return false;
        }
        //再判断如果进行与运算之后是否还是本身
        if((num&0x55555555)==num)
        {
            return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        PowerOfFour p=new PowerOfFour();
        System.out.println(p.isPowerOfFour1(16));
        System.out.println(p.isPowerOfFour1(5));
        System.out.println(p.isPowerOfFour2(16));
        System.out.println(p.isPowerOfFour2(5));
    }
}
