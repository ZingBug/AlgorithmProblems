package Tencent_Summer_Intern_2017;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 3、有趣的数字
 * 小Q今天在上厕所时想到了这个问题：有n个数，两两组成二元组，差最小的有多少对呢？差最大呢？
 *
 * 输入描述:
 *  输入包含多组测试数据。
 *  对于每组测试数据：
 *  N - 本组测试数据有n个数
 *  a1,a2...an - 需要计算的数据
 *
 *  保证:
 *  1<=N<=100000,0<=ai<=INT_MAX.
 *
 * 输出描述:
 * 对于每组数据，输出两个数，第一个数表示差最小的对数，第二个数表示差最大的对数。
 *
 * 输入例子1:
 * 6
 * 45 12 45 32 5 6
 *
 * 输出例子1:
 * 1 2
 */
public class NumberDifference {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int N=in.nextInt();
            int[] a=new int[N];
            for(int i=0;i<N;i++)
            {
                a[i]=in.nextInt();
            }
            Arrays.sort(a);
            if(a[0]==a[N-1])//如果数组中的值全相同，直接两两组合
            {
                int tmp=(N*(N-1))/2;
                System.out.println(tmp+" "+tmp);
                continue;
            }
            //求最大差对数
            int num_min=1;
            int num_max=1;
            for(int i=0;(i<N-1)&&(a[i]==a[i+1]);i++)
            {
                num_min++;
            }
            for(int i=N-1;(i>0)&&(a[i]==a[i-1]);i--)
            {
                num_max++;
            }
            int diff_max=num_max*num_min;
            //求最小差对数
            Map<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<N;i++)
            {
                if(map.containsKey(a[i]))
                {
                    map.put(a[i],map.get(a[i])+1);
                }
                else
                {
                    map.put(a[i],1);
                }
            }
            int diff_min=0;
            if(map.size()==N)
            {
                int diff=Integer.MAX_VALUE;
                for(int i=0;i<N-1;i++)
                {
                    int temp=Math.abs(a[i+1]-a[i]);
                    if(temp<diff)
                    {
                        diff=temp;
                        diff_min=1;
                    }
                    else if(temp==diff)
                    {
                        diff_min++;
                    }
                }
            }
            else
            {
                //有重复值
                for(int val:map.values())
                {
                    if(val>1)
                    {
                        diff_min+=(val*(val-1))/2;
                    }
                }
            }


            System.out.println(diff_min+" "+diff_max);
        }
    }
}
