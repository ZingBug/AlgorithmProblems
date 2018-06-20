package Coding_Interviews;

/**
 * 二进制中1的个数
 * 题目描述
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 * https://www.nowcoder.com/practice/8ee967e43c2c4ec193b040ea7fbb10b8?tpId=13&tqId=11164&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class NumberOf1 {
    public int solution1(int n)
    {
        //第一种方法，利用Java内置toBinaryString方法来实现
        String str=Integer.toBinaryString(n);
        int count=0;
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='1')
            {
                count++;
            }
        }
        return count;
    }
    public int solution2(int n)
    {
        //第二种方法，利用位运算来实现
        //将n与n-1做与运算，会把最右边的1去掉。 比如： 1100 & 1011 = 1000 ，即 12 & 11 = 8，再利用计算器count++计算出有多少个 1 即可。
        int count=0;
        while (n!=0)
        {
            count++;
            n=n&(n-1);
        }
        return count;
    }
    public int NumberOf1(int n)
    {
        //return solution1(n);
        return solution2(n);
    }
    public static void main(String[] args)
    {
        NumberOf1 n=new NumberOf1();
        System.out.println(n.NumberOf1(3));
    }
}
