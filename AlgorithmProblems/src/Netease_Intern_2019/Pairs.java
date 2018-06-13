package Netease_Intern_2019;

import java.util.Scanner;

/**
 * 4、数对
 * 牛牛以前在老师那里得到了一个正整数数对(x, y), 牛牛忘记他们具体是多少了。
 * 但是牛牛记得老师告诉过他x和y均不大于n, 并且x除以y的余数大于等于k。
 * 牛牛希望你能帮他计算一共有多少个可能的数对。
 *
 * 输入描述:
 * 输入包括两个正整数n,k(1 <= n <= 10^5, 0 <= k <= n - 1)。
 *
 * 输出描述:
 * 对于每个测试用例, 输出一个正整数表示可能的数对数量。
 *
 * 输入例子1:
 * 5 2
 *
 * 输出例子1:
 * 7
 *
 * 例子说明1:
 * 满足条件的数对有(2,3),(2,4),(2,5),(3,4),(3,5),(4,5),(5,3)
 */
public class Pairs {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        long n=in.nextInt();
        long k=in.nextInt();

        long num=0;
        long p=0;
        long x=0;

        for(long y=k+1;y<=n;y++)
        {
            p=0;
            while (true)
            {
                x=p*y+k;
                if(x<=n)
                {
                    //num+=(y-k);
                    num+=Math.min(y-k,n-x+1);
                }
                else
                {
                    break;
                }
                p++;
            }
        }
        if(k==0)
        {
            num-=n;
        }

        System.out.println(num);
        //System.out.println(testReal(n,k));
    }
    public static long testReal(long n,long k)
    {
        long num=0;
        for(long i=1;i<=n;i++)
        {
            for (int j=1;j<=n;j++)
            {
                if(i%j>=k)
                {
                    num++;
                }
            }
        }
        return num;
    }
}
