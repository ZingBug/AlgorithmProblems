package Coding_Interviews;

/**
 * 求1+2+3+...+n
 * 题目描述
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * https://www.nowcoder.com/practice/7a0da8fc483247ff8800059e12d7caf1?tpId=13&tqId=11200&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * Created by ZingBug on 2018/7/5.
 */
public class SumFrom1ToN {
    private int solution1(int n)
    {
        //第一种方法，用数学公式
        return (1+n)*n/2;
    }
    private int solution2(int n)
    {
        //第二种方法，用递归来实现。用或条件来终止递归。
        int sum=n;
        boolean b=(n==0)||(sum+=solution2(n-1))>0;
        return sum;
    }
    private int solution3(int n)
    {
        //第三种方法，用递归来实现。用异常来中止递归。
        try
        {
            int i=1%n;
            return n+solution3(n-1);
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    public int Sum_Solution(int n)
    {
        return solution2(n);
    }

    public static void main(String[] args)
    {
        SumFrom1ToN s=new SumFrom1ToN();
        System.out.println(s.Sum_Solution(100));
    }
}
