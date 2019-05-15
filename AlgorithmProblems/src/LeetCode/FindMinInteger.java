package LeetCode;

import java.util.BitSet;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * 说明: 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * https://mp.weixin.qq.com/s/neUHYvXoBTM08xv2I37i2w
 *
 * Created by ZingBug on 2019/5/12.
 */
public class FindMinInteger {

    private int findMinInteger1(int[] array)
    {
        if(array==null||array.length==0)
        {
            return 1;
        }
        int len=array.length;
        int[] a=new int[len+1];
        for(int i:array)
        {
            if(i<=len&&i>0)
            {
                a[i]++;
            }
        }
        for(int i=1;i<=array.length;i++)
        {
            if(a[i]==0)
            {
                return i;
            }
        }
        return len+1;
    }

    //用bisSet来实现，复杂度虽然还是n，但速度快了32倍，和上面用数组原理一样
    private int findMinInteger2(int[] array)
    {
        if(array==null||array.length==0)
        {
            return 1;
        }
        int len=array.length;
        BitSet bitSet=new BitSet();
        for(int i:array)
        {
            if(i<=len&&i>0)
            {
                bitSet.set(i);
            }
        }
        for(int i=1;i<=len;i++)
        {
            if(!bitSet.get(i))
            {
                return i;
            }
        }
        return len+1;
    }

    //用技术排序的方法，对处于 1~n 之间的数进行排序
    private int findMinInteger3(int[] array)
    {
        if(array==null||array.length==0)
        {
            return 1;
        }
        int len=array.length;
        for(int i=0;i<len;i++)
        {

            //注意 nums[i] 与下标为 nums[i]-1的数如果相等的话，不需要交换
            while (array[i]>0&&array[i]<=len&&array[i]!=i+1&&array[i]!=array[array[i]-1])
            {
                int temp=array[array[i]-1];
                array[array[i]-1]=array[i];
                array[i]=temp;
            }
        }
        for(int i=0;i<len;i++)
        {
            if(array[i]!=i+1)
            {
                return i+1;
            }
        }
        return len+1;
    }



    public static void main(String[] args)
    {
        FindMinInteger f=new FindMinInteger();
        int[] array1={1,2,0};
        int[] array2={3,4,-1,1};
        int[] array3={7,8,9,11,12};
        System.out.println("***********集合方法************");
        System.out.println(f.findMinInteger1(array1));
        System.out.println(f.findMinInteger1(array2));
        System.out.println(f.findMinInteger1(array3));
        System.out.println("***********bitset方法************");
        System.out.println(f.findMinInteger2(array1));
        System.out.println(f.findMinInteger2(array2));
        System.out.println(f.findMinInteger2(array3));
        System.out.println("***********计数排序方法************");
        System.out.println(f.findMinInteger3(array1));
        System.out.println(f.findMinInteger3(array2));
        System.out.println(f.findMinInteger3(array3));

    }
}
