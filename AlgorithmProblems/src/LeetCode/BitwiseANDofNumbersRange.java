package LeetCode;

/**
 * 201. Bitwise AND of Numbers Range
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 *
 * Example 1:
 *
 * Input: [5,7]
 * Output: 4
 * Example 2:
 *
 * Input: [0,1]
 * Output: 0
 *
 * 输入: [26,30]
 * 输出: 24
 * 首先，将 [ 26 , 30 ] 的范围数字用二进制表示出来：
 *
 * 11010　　11011　　11100　　11101　　11110
 *
 * 而输出 24 的二进制是 11000 。
 *
 * 可以发现，只要找到二进制的 左边公共部分 即可。
 *
 * 所以，可以先建立一个 32 位都是 1 的 mask，然后每次向左移一位，比较 m 和 n 是否相同，不同再继续左移一位，直至相同，然后把 m 和 mask 相与就是最终结果。
 *
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/
 *
 * Created by ZingBug on 2019/5/13.
 */
public class BitwiseANDofNumbersRange {

    private int rangeBitwiseAnd(int m,int n)
    {
        int d=Integer.MAX_VALUE;
        while ((m&d)!=(n&d))
        {
            d=d<<1;
        }
        return m&d;
    }

    public static void main(String[] args)
    {
        BitwiseANDofNumbersRange b=new BitwiseANDofNumbersRange();
        System.out.println(b.rangeBitwiseAnd(5,7));
        System.out.println(b.rangeBitwiseAnd(0,1));
    }
}
