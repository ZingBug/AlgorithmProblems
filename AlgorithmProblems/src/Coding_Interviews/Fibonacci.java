package Coding_Interviews;

/**
 * 斐波那契数列
 * 题目描述
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 * n<=39
 *
 * https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3?tpId=13&tqId=11160&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class Fibonacci {
    public int solution1(int n)
    {
        //第一种方法，递归，很容易导致内存溢出
        if(n==1||n==2)
        {
            return 1;
        }
        else
        {
            return Fibonacci(n-1)+Fibonacci(n-2);
        }
    }
    public int solution2(int n)
    {
        if(n==1||n==2)
        {
            return 1;
        }
        int first=1;
        int second=1;
        int target=0;
        for(int i=0;i<n-2;i++)
        {
            target=first+second;
            first=second;
            second=target;
        }
        return target;
    }
    public int Fibonacci(int n)
    {
        return solution2(n);
    }

    public static void main(String[] args)
    {
        Fibonacci f=new Fibonacci();
        System.out.println(f.Fibonacci(9));
    }
}
