package MIOJ_2018;

/**
 * 爬楼梯
 * 描述
 * 在你面前有一个n阶的楼梯，你一步只能上1阶或2阶。 请问计算出你可以采用多少种不同的方式爬完这个楼梯。
 *
 * 输入
 * 一个正整数，表示这个楼梯一共有多少阶
 *
 * 输出
 * 一个正整数，表示有多少种不同的方式爬完这个楼梯
 *
 * 输入样例
 * 5
 * 10
 * 输出样例
 * 8
 * 89
 *
 * https://code.mi.com/problem/list/view?id=10
 * Created by ZingBug on 2018/9/8.
 */
public class ClimbStairs {
    private static int step=0;
    private static String solution(String line)
    {
        int num=Integer.valueOf(line);
        step=0;
        climb(num);
        return String.valueOf(step);

    }
    private static void climb(int num)
    {
        if(num==0)
        {
            step+=1;
            return;
        }
        if(num>=1)
        {
            climb(num-1);
        }
        if(num>=2)
        {
            climb(num-2);
        }
    }

    public static void main(String[] args)
    {
        ClimbStairs c=new ClimbStairs();
        String line1="5";
        String line2="10";
        System.out.println(c.solution(line1));
        System.out.println(c.solution(line2));
    }
}
