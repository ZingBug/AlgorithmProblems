package CodeM_2018.Qualification;

import java.util.Scanner;

/**
 * 2、可乐
 * 题目描述
 * 小美和小团最近沉迷可乐。可供TA们选择的可乐共有k种，比如可口可乐、零度可乐等等，每种可乐会带给小美和小团不同的快乐程度。
 * TA们一共要买n瓶可乐，每种可乐可以买无限多瓶，小美会随机挑选其中的m瓶喝，剩下的n-m瓶小团喝。
 * 请问应该如何购买可乐，使得小美和小团得到的快乐程度的和的期望值最大？
 * 现在请求出购买可乐的方案。
 *
 * 输入描述:
 * 第一行三个整数n，m，k分别表示要买的可乐数、小美喝的可乐数以及可供选择的可乐种数。
 * 接下来k行，每行两个整数a，b分别表示某种可乐分别给予小美和小团的快乐程度。
 * 对于所有数据，1 <= n <= 10,000, 0 <= m <= n, 1 <= k <= 10,000, -10,000 <= a, b <= 10,000
 *
 * 输出描述:
 * 一行k个整数，第i个整数表示购买第i种可乐的数目。
 * 如果有多解，请输出字典序最小的那个。
 * 对于两个序列 a1, a2, ..., ak, b1, b2, ..., bk，a的字典序小于b，当且仅当存在一个位置i <= k满足：
 * ai < bi且对于所有的位置 j < i，aj = bj；
 *
 * 示例1
 * 输入
 * 2 1 2
 * 1 2
 * 3 1
 *
 * 输出
 * 0 2
 *
 * 说明
 * 一共有三种购买方案：
 * 1. 买2瓶第一类可乐，小美和小团各喝一瓶，期望得到的快乐程度和为1+2=3；
 * 2. 买1瓶第一类可乐和1瓶第二类可乐，小美和小团各有二分之一的概率喝到第一类可乐，另有二分之一的概率喝到第二类可乐，期望得到的快乐程度和为1*0.5+3*0.5+2*0.5+1*0.5=3.5；
 * 3. 买2瓶第二类可乐，小美和小团各喝一瓶，期望得到的快乐程度和为3+1=4。
 */

public class Coke {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int k=in.nextInt();
        int[] a=new int[k];
        int[] b=new int[k];
        for(int i=0;i<k;i++)
        {
            a[i]=in.nextInt();
            b[i]=in.nextInt();
        }

        int happiness=Integer.MIN_VALUE;
        int index=0;
        for(int i=0;i<k;i++)
        {
            int temp=m*a[i]+(n-m)*b[i];
            if(temp>=happiness)
            {
                happiness=temp;
                index=i;
            }
        }

        int[] result=new int[k];
        result[index]=n;
        for(int i=0;i<k;i++)
        {
            System.out.print(result[i]);
            if(i!=k-1)
            {
                System.out.print(" ");
            }
        }


    }
}
