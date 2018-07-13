package Coding_Interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 滑动窗口的最大值
 * 题目描述
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
 * 他们的最大值分别为{4,4,6,6,6,5}；针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}，
 * {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 *
 * https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13&tqId=11217&tPage=4&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 借助一个辅助队列，从头遍历数组，根据如下规则进行入队列或出队列操作：
 * 0. 如果队列为空，则当前数字入队列
 * 1. 如果当前数字大于队列尾，则删除队列尾，直到当前数字小于等于队列尾，或者队列空，然后当前数字入队列
 * 2. 如果当前数字小于队列尾，则当前数字入队列
 * 3. 如果队列头超出滑动窗口范围，则删除队列头
 * 这样能始终保证队列头为当前的最大值
 * Created by ZingBug on 2018/7/12.
 */
public class MaxInWindows {
    private ArrayList<Integer> solution1(int[] num,int size)
    {
        //第一种方法，暴力求解法，扫描滑动窗口中的每一个数字，并找出其中的最大值。如果滑动窗口的大小为m，则找到窗口内的最大值需要O(k)时间。
        // 对于长度为n的数组，总的时间复杂度为O(mn)
        ArrayList<Integer> list=new ArrayList<>();
        if(size<=0)
        {
            return list;
        }
        int len=num.length;

        for(int i=0;i<len-size+1;i++)
        {
            int n=Integer.MIN_VALUE;
            for(int j=i;j<i+size;j++)
            {
                n=Math.max(n,num[j]);
            }
            list.add(n);
        }
        return list;
    }
    private ArrayList<Integer> solution2(int[] num,int size)
    {
        ArrayList<Integer> list=new ArrayList<>();
        if(size<=0)
        {
            return list;
        }
        int len=num.length;
        List<Integer> queue=new LinkedList<>();
        for(int i=0;i<len;i++)
        {
            if(!queue.isEmpty())
            {
                // 如果队列头元素不在滑动窗口中了，就删除头元素
                if(i>=((LinkedList<Integer>) queue).peek()+size)
                {
                    ((LinkedList<Integer>) queue).pop();
                }

                while (!queue.isEmpty()&&num[i]>=num[((LinkedList<Integer>) queue).getLast()])
                {
                    ((LinkedList<Integer>) queue).removeLast();
                }
            }
            ((LinkedList<Integer>) queue).offer(i);
            if(i+1>=size)
            {
                list.add(num[((LinkedList<Integer>) queue).peek()]);
            }
        }
        return list;
    }
    public ArrayList<Integer> maxInWindows(int[] num,int size)
    {
        return solution2(num,size);
    }

    public static void main(String[] agrs)
    {
        MaxInWindows m=new MaxInWindows();
        int[] num1={2,3,4,2,6,2,5,1};
        int size1=3;
        int[] num2={1,3,5,7,9,11,13,15};
        int size2=4;
        ArrayList<Integer> list=m.maxInWindows(num2,size2);

        for(int val:list)
        {
            System.out.print(val+" ");
        }
    }

}
