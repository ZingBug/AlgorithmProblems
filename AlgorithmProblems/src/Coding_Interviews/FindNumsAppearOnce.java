package Coding_Interviews;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中只出现一次的数字
 * 题目描述
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 *
 * https://www.nowcoder.com/practice/e02fdb54d7524710a7d664d082bb7811?tpId=13&tqId=11193&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 1、先考虑一个数组里只有一个数出现一次，其他两个数都出现两次的情况：异或运算中，任何一个数字和自己本身异或都是0，任何一个数字和0异或都是本身。
 * 一个数组里只有一个数出现一次其他两次，挨个异或后最后得到的结果就是只出现一次的那个数。
 *
 * 2、我们尝试吧原数组分成两个子数组，是的每个子数组包含一个只出现一次的数字，而其他数字都成对出现两次。
 *
 * 3、我们从头到尾依次异或数组中的每一个数字，那么最终得到的结果就是两个只出现一次的数字的异或结果。
 * 由于这两个数字肯定不一样，那么异或的结果肯定不为0，也就是说在这个结果数字的二进制表示中至少有一个位为1。
 * 我们在结果数字中找到第一个为1的位的位置，记为第n位。
 * 现在我们以第n位是不是1为标准把原数组中的数字分成两个数组，第一个子数组中每个数字的第n为都是1，第二个数组中每个数组的第n位都是0。
 * 由于我们分组的标准是数字中的某一位是1还是0，那么出现了两次的数字肯定被分配到同一个子数组。
 * 因为两个相同的数字任意一位都是相同的，我们不可能把两个相同的数字分配到两个子数组中去，于是我们已经把原数组分成两个子数组，每个子数组都包含哪一个只出现一次的数字，而其他数字出现两次。
 * 问题解决。
 *
 * Created by ZingBug on 2018/7/3.
 */
public class FindNumsAppearOnce {
    private void solution1(int[] array,int num1[],int num2[])
    {
        //第一种方法是暴力求解，利用哈希表来存储，时间复杂度为O(n+2)
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for(int val:array)
        {
            if(hashMap.containsKey(val))
            {
                hashMap.remove(val);
            }
            else
            {
                hashMap.put(val,1);
            }
        }
        //遍历hashmap
        boolean first=true;
        for(Map.Entry<Integer,Integer> entry:hashMap.entrySet())
        {
            if(first)
            {
                //只出现一次
                num1[0]=entry.getKey();
                first=false;
            }
            else
            {
                num2[0]=entry.getKey();
            }
        }
    }

    private void solution2(int[] array,int num1[],int num2[])
    {
        //第二种方法，用位异或方法来做。时间复杂度为O(n)，空间复杂度为O(1)
        int length=array.length;
        if(length==2)
        {
            num1[0]=array[0];
            num2[0]=array[1];
            return;
        }
        int bitResult=0;
        for(int i=0;i<length;i++)
        {
            bitResult^=array[i];
        }
        int index=findFirst1(bitResult);
        for(int i=0;i<length;++i)
        {
            if(isBit1(array[i],index))
            {
                num1[0]^=array[i];
            }
            else
            {
                num2[0]^=array[i];
            }
        }

    }
    private int findFirst1(int bitResult)
    {
        //找到第一个是1的位数
        int index=0;
        while (((bitResult&1)==0)&&index<32)
        {
            bitResult>>=1;
            index++;
        }
        return index;
    }
    private boolean isBit1(int target,int index)
    {
        //判断target中的第n位是否为1，用来划分数组。
        return ((target>>index)&1)==1;
    }
    public void FindNumsAppearOnce(int[] array,int num1[],int num2[])
    {
        //num1,num2分别为长度为1的数组。传出参数
        //将num1[0],num2[0]设置为返回结果
        solution2(array,num1,num2);
    }

    public static void main(String[] args)
    {
        FindNumsAppearOnce f=new FindNumsAppearOnce();
        int[] array={1,2,1,2,3,4,4,56,56,7,9,7};
        int[] num1=new int[1];
        int[] num2=new int[1];
        f.FindNumsAppearOnce(array,num1,num2);
        System.out.println(num1[0]+" "+num2[0]);
    }
}
