package Coding_Interviews;

import java.util.ArrayList;

/**
 * 和为S的两个数字
 * 题目描述
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 *
 * https://www.nowcoder.com/practice/390da4f7a00f44bea7c2f3d19491311b?tpId=13&tqId=11195&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 我们考虑用两个数small和big分别表示两个数的小值和大值，分别初始化为排序数组的头和尾。
 * 如果两个数的和等于sum，则这两个数正好是成绩最小的两个数。
 * 如果两个数的和小于sum，则将small值往后移一位。
 * 如果两个数的和大于sum，则将big值往前移一位。
 * 依次循环。
 * 方法与FindContinuousSequence相似。
 * Created by ZingBug on 2018/7/4.
 */
public class FindNumbersWithSum {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> list=new ArrayList<>();
        if(array==null||array.length==0||array.length==1)
        {
            return list;
        }
        int len=array.length;
        int small=0;
        int big=len-1;
        int curSum=array[small]+array[big];
        while (small<big)
        {
            if(curSum==sum)
            {
                list.add(array[small]);
                list.add(array[big]);
                return list;
            }
            else if(curSum<sum)
            {
                curSum-=array[small];
                small++;
                curSum+=array[small];
            }
            else
            {
                curSum-=array[big];
                big--;
                curSum+=array[big];
            }
        }
        return list;
    }
    public static void main(String[] args)
    {
        FindNumbersWithSum f=new FindNumbersWithSum();
        int[] array={1,2,3,4,5,6,7,8};
        ArrayList<Integer> list=f.FindNumbersWithSum(array,10);
        for(int val:list)
        {
            System.out.print(val+" ");
        }
        System.out.println();
    }
}
