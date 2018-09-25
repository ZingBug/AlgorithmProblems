package MIOJ_2018;

import java.util.ArrayList;

/**
 * 找出可能的合的组合
 * 描述
 * 给出一组不重复的正整数，从这组数中找出所有可能的组合使其加合等于一个目标正整数 M，如：
 *
 * 一组数为 1, 2, 3，目标数为 4，那么可能的加合组合为： 1, 1, 1, 1 1, 1, 2 1, 2, 1 1, 3 2, 1, 1 2, 2 3, 1 注意相同的组合数字顺序不同也算一种，所以这个例子的结果是 7 种。
 *
 * 输入
 * 一组连续不重复的 N 个正整数（, 隔开，0<N<100）以及目标正整数（与数组之间用空格隔开）
 *
 * 输出
 * 所有可能的加合等于目标正整数 M 的组合种数
 *
 * 输入样例
 * 1,2,3 4
 * 输出样例
 * 7
 *
 * https://code.mi.com/problem/list/view?id=12
 * Created by ZingBug on 2018/9/8.
 */
public class FindCombination {
    private static int n=0;
    private static String solution(String line)
    {
        //利用回溯法来做，又称试探法，按照条件向前搜索。
        //找第一个数再找下一个数字，最后凑够sum
        String[] array=line.split(" ");
        String[] numArray=array[0].split(",");

        int sum=Integer.valueOf(array[1]);

        int[] nums=new int[numArray.length];
        n=0;
        int i=0;
        for(String str:numArray)
        {
            int num=Integer.valueOf(str);
            nums[i++]=num;
        }
        trace(nums,sum);
        return String.valueOf(n);
    }
    private static void trace(int[] nums,int sum)
    {
        if(sum<0)
        {
            return;
        }
        else if(sum==0)
        {
            n++;
            return;
        }
        else
        {
            for(int num:nums)
            {
                trace(nums,sum-num);
            }
        }
    }

    public static void main(String[] args)
    {
        FindCombination f=new FindCombination();
        String line="1,2,3 4";
        System.out.println(f.solution(line));
    }
}
