package LeetCode;

/**
 * 191. Number of 1 Bits
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为  ‘1’  的个数。
 *
 * Example 1:
 *
 * Input: 00000000000000000000000000001011
 * Output: 3
 * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 * Example 2:
 *
 * Input: 00000000000000000000000010000000
 * Output: 1
 * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
 * Example 3:
 *
 * Input: 11111111111111111111111111111101
 * Output: 31
 * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 *
 * https://leetcode.com/problems/number-of-1-bits/
 *
 * Created by ZingBug on 2019/5/13.
 */
public class NumberOf1Bits {

    private int hammingWeight1(int n)
    {
        int res=0;
        while (n!=0)
        {
            n=n&(n-1);//通过 n&(n-1)这个操作，可以起到消除最后一个1的作用。
            res++;
        }
        return res;
    }

    private int hammingWeight2(int n)
    {
        int res=0;
        for(int i=0;i<32;i++)
        {
            res+=(n&1);
            n=n>>1;
        }
        return res;
    }

    public static void main(String[] args)
    {
        NumberOf1Bits n=new NumberOf1Bits();
        System.out.println(n.hammingWeight2(3));
    }
}
