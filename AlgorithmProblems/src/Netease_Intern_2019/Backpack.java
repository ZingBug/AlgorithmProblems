package Netease_Intern_2019;

import java.util.Scanner;

/**
 * 8、背包问题
 * 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容量为w。
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。
 * 牛牛想知道在总体积不超过背包容量的情况下,他一共有多少种零食放法(总体积为0也算一种放法)。
 *
 * 输入描述:
 * 输入包括两行
 * 第一行为两个正整数n和w(1 <= n <= 30, 1 <= w <= 2 * 10^9),表示零食的数量和背包的容量。
 * 第二行n个正整数v[i](0 <= v[i] <= 10^9),表示每袋零食的体积。
 *
 * 输出描述:
 * 输出一个正整数, 表示牛牛一共有多少种零食放法。
 *
 * 输入例子1:
 * 3 10
 * 1 2 4
 *
 * 输出例子1:
 * 8
 *
 * 例子说明1:
 * 三种零食总体积小于10,于是每种零食有放入和不放入两种情况，一共有2*2*2 = 8种情况。
 */
public class Backpack {
    private static int n;
    private static long w;
    private static Long[] v;
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        w=in.nextLong();

        v=new Long[n];

        for(int i=0;i<n;i++)
        {
            v[i]=in.nextLong();
        }
        /*
        Arrays.sort(v,new Comparator<Long>()
        {
            @Override
            public int compare(Long o1, Long o2) {
                return -Long.compare(o1,o2);
            }
        });
        */
        //Arrays.sort(v,(o1,o2)->-Long.compare(o1,o2));//Lambda表达式
        //也不用排序即可
        long sumV=0;
        for(int i=0;i<n;i++)
        {
            sumV+=v[i];
        }
        int result=getNum(0,sumV,w);
        System.out.println(result);
    }
    public static int getNum(int index,long v1,long w1)//index 当前下标;
                                                    //v1 零食剩余总重量
                                                    //w1 背包剩余空间
    {
        int num=0;
        if(index>=n)
        {
            return 1;
        }
        if(v1<=w1)//背包剩余空间可以放下剩余零食
        {
            num+=Math.pow(2,n-index);
        }
        else if(v[index]<=w1)//当前背包剩余空间允许放下当前零食
        {
            num+=getNum(index+1,v1-v[index],w1-v[index]);//如果当前下标零食放置
            num+=getNum(index+1,v1-v[index],w1);//如果当前下标零食不放置
        }
        else//当前背包剩余空间不允许放下当前零食
        {
            num+=getNum(index+1,v1-v[index],w1);//如果当前下标零食不放置
        }
        return num;
    }
}
