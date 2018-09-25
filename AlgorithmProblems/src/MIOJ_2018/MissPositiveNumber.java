package MIOJ_2018;

import java.util.*;

/**
 * 第一个缺失正数
 * 描述
 * 给出一个无序的数列，找出其中缺失的第一个正数，要求复杂度为 O(n) 如：[1,2,0]，第一个缺失为3。 如：[3,4,-1,1]，第一个缺失为2。
 *
 * 输入
 * 1,2,0
 *
 * 输出
 * 3
 *
 * 输入样例
 * 1,2,0
 * 3,4,-1,1
 * -1,-3,-5
 * 1,2,3
 * -1,-10,0
 *
 * 输出样例
 * 3
 * 2
 * 1
 * 4
 * 1
 * https://code.mi.com/problem/list/view?id=7
 * Created by ZingBug on 2018/9/5.
 */
public class MissPositiveNumber {
    private static String solution1(String line)
    {
        //第一种方法很简单，就是不断找上下限
        String[] array=line.split(",");
        HashSet<Integer> set=new HashSet<>();
        int i=0;
        for(String str:array)
        {
            int num=Integer.valueOf(str);
            if(num>=0)
            {
                set.add(num);
            }
        }
        int max=0;
        for(int num:set)
        {
            int cur=num;
            while (true)
            {
                if(!set.contains(++cur))
                {
                    if(set.contains(cur+1))
                    {
                        return String.valueOf(cur);
                    }
                    break;
                }
            }
            cur=num;
            while (true)
            {
                if(!set.contains(--cur))
                {
                    if(set.contains(cur-1))
                    {
                        return String.valueOf(cur);
                    }
                    break;
                }
            }
            max=Math.max(max,num);
        }
        return String.valueOf(max+1);
    }

    private static String solution2(String line)
    {
        //利用桶排序方法
        //https://blog.csdn.net/guoziqing506/article/details/50952027
        String[] array=line.split(",");
        int len=array.length;
        int[] nums=new int[len];
        int i=0;
        for(String str:array)
        {
            int num=Integer.valueOf(str);
            nums[i++]=num;
        }
        //桶排序
        for(i=0;i<len;i++)
        {
            while (nums[i]!=i+1)
            {
                if(nums[i]<=0||nums[i]>len&&nums[i]==nums[nums[i]-1])
                {
                    break;
                }
                //以下三行代码实现nums[i]与nums[nums[i] - 1]的交换
                int temp=nums[i];
                nums[i]=nums[temp-1];
                nums[temp-1]=temp;
            }
        }
        for(i=0;i<len;i++)
        {
            if(nums[i]!=i+1)
                return String.valueOf(i+1);
        }
        return String.valueOf(len+1);
    }

    public static void main(String[] args)
    {
        MissPositiveNumber m=new MissPositiveNumber();
        String line1="1,2,0";
        String line2="3,4,-1,1";
        String line3="-1,-3,-5";
        String line4="1,2,3";
        String line5="-1,-10,0";

        System.out.println(m.solution2(line1));
        System.out.println(m.solution2(line2));
        System.out.println(m.solution2(line3));
        System.out.println(m.solution2(line4));
        System.out.println(m.solution2(line5));
    }
}
