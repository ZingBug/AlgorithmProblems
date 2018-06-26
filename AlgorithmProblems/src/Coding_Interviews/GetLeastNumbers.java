package Coding_Interviews;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

/**
 * 最小的K个数
 * 题目描述
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 *
 * https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=13&tqId=11182&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 1、通过Tree来做，Tree树是自动排序的，然后前面的K个值则是最小的K个值，输出即可。
 * 2、利用冒泡排序的思想，每一轮排序把剩余数组中最小的一个数字放到前面已排序的后面，时间复杂度为O(kn)
 * 3、利用快速排序划分思想，基于数组的第k个数字来调整，使得比k个数字小的所有数字都位于数组的左边，比第k个数字大的所有数字都位于数组的右边。
 * Created by ZingBug on 2018/6/25.
 */
public class GetLeastNumbers {
    private ArrayList<Integer> solution1(int[] input,int k)
    {
        if(input==null)
        {
            return null;
        }
        ArrayList<Integer> list=new ArrayList<>();
        if(k>input.length||k<=0)
        {
            return list;
        }
        TreeSet<Integer> tree=new TreeSet<>();
        for(int val:input)
        {
            tree.add(val);
        }

        for(int i=0;i<k;i++)
        {
            list.add(tree.pollFirst());
        }
        return list;
    }
    private ArrayList<Integer> solution2(int[] input,int k)
    {
        if(input==null)
        {
            return null;
        }
        ArrayList<Integer> list=new ArrayList<>();
        if(k>input.length||k<=0)
        {
            return list;
        }
        int temp=0;
        for(int i=0;i<k;i++)
        {
            for(int j=i+1;j<input.length;j++)
            {
                if(input[i]>input[j])
                {
                    //交换
                    temp=input[i];
                    input[i]=input[j];
                    input[j]=temp;
                }
            }
            list.add(input[i]);
        }
        return list;
    }
    private ArrayList<Integer> solution3(int[] input,int k)
    {
        if(input==null)
        {
            return null;
        }
        ArrayList<Integer> list=new ArrayList<>();
        if(k>input.length||k<=0)//这块一定加上k<=0的判断，否则超时
        {
            return list;
        }
        int start=0;
        int high=input.length-1;
        int index=partition(input,start,high);
        while (index!=k-1)
        {
            if(index>k-1)
            {
                high=index-1;
            }
            else
            {
                start=index+1;
            }
            index=partition(input,start,high);
        }
        for(int i=0;i<k;i++)
        {
            list.add(input[i]);
        }
        return list;
    }
    private int partition(int[] input,int start,int end)
    {
        int pivot=input[start];
        while (start<end)
        {
            while (start<end&&input[end]>=pivot)
            {
                end--;
            }
            input[start]=input[end];
            while (start<end&&input[start]<=pivot)
            {
                start++;
            }
            input[end]=input[start];

        }
        input[start]=pivot;
        return start;
    }
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input,int k)
    {
        return solution3(input,k);
    }
    public static void main(String[] args)
    {
        int[] input={4,5,1,6,2,7,3,8};
        GetLeastNumbers g=new GetLeastNumbers();
        ArrayList<Integer> list=g.GetLeastNumbers_Solution(input,4);
        for(int val:list)
        {
            System.out.print(val+" ");
        }
    }
}
