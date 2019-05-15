package LeetCode;

/**
 * 231. Power of Two
 *
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 *
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 *
 * Input: 218
 * Output: false
 *
 * https://leetcode.com/problems/power-of-two/
 * Created by ZingBug on 2019/5/13.
 */
public class PowerOfTwo {
    private boolean isPowerOfTwo1(int n)
    {
        //2 的次方数都只有一个 1 ，剩下的都是 0 。根据这个特点，只需要每次判断最低位是否为 1 ，然后向右移位，最后统计 1 的个数即可判断是否是 2 的次方数。
        int res=0;
        while (n!=0)
        {
            res+=(n&1);
            n=n>>1;
        }
        return res==1;
    }

    private boolean isPowerOfTwo2(int n)
    {
        //利用n&(n-1)这个操作
        //如果一个数是 2 的次方数的话，那么它的二进数必然是最高位为1，其它都为 0 ，
        //那么如果此时我们减 1 的话，则最高位会降一位，其余为 0 的位现在都为变为 1，那么我们把两数相与，就会得到 0。
        return (n>0)&&((n&(n-1))==0);
    }

    public static void main(String[] args)
    {
        PowerOfTwo p=new PowerOfTwo();
        System.out.println(p.isPowerOfTwo1(1));
        System.out.println(p.isPowerOfTwo1(16));
        System.out.println(p.isPowerOfTwo1(218));
        System.out.println(p.isPowerOfTwo2(1));
        System.out.println(p.isPowerOfTwo2(16));
        System.out.println(p.isPowerOfTwo2(218));
    }
}
