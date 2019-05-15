package Coding_Interview_Guide.ArrayAndMatrix;

import java.util.HashMap;
import java.util.Map;

/**
 * 未排序数组中累加和为给定值的最长子数组系列问题
 * 给定一个无序数组arr，其中元素可正、可负、可0，给定一个整数k。求arr所有的子数组中累加和为k的最长子数组长度。
 * 解题思路：记录从0到所有位置的累加和sum，然后保存在map中，然后找是否有sum-k的累加和。
 * Page355
 * Created by ZingBug on 2018/11/29.
 */
public class GetMaxLengthFromArray {
    private int getMaxLength(int[] arr ,int k)
    {
        if(arr==null||arr.length<=0)
        {
            return 0;
        }
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int len=0;
        int sum=0;
        for(int i=0;i<arr.length;i++)
        {
            sum+=arr[i];
            if(map.containsKey(sum-k))
            {
                len=Math.max(len,i-map.get(sum-k));
            }
            if(!map.containsKey(sum))
            {
                map.put(sum,i);
            }
        }
        return len;
    }

    //补充题目1：给定一个无序数组，其中元素可正、可负、可0。求arr所有的子数组中正数与负数个数相等的最长子数组长度。
    //解题思路：先把数组arr中所有的正数全部变成1，负数全部变成-1，0不变，然后求累加和为0的最长子数组长度即可。
    private int supplement1(int[] arr)
    {
        if(arr==null||arr.length<=0)
        {
            return 0;
        }
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]>0)
            {
                arr[i]=1;
            }
            else if(arr[i]<0)
            {
                arr[i]=-1;
            }
        }
        int sum=0;
        int len=0;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<arr.length;i++)
        {
            sum+=arr[i];
            if(map.containsKey(sum))
            {
                len=Math.max(len,i-map.get(sum));
            }
            else
            {
                map.put(sum,0);
            }
        }
        return len;
    }
    //补充题目2：给定一个无序数组，其中元素只是1和0。求arr所有的子数组中正数与负数个数相等的最长子数组长度。
    //解题思路：把数组arr中所有的0全部变成-1，1不变，然后求累加和为0的最长子数组长度即可。
    private int supplement2(int[] arr)
    {
        if(arr==null||arr.length<=0)
        {
            return 0;
        }
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==0)
            {
                arr[i]=-1;
            }
        }
        int len=0;
        int sum=0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++) {
            sum += arr[i];
            if (map.containsKey(sum))
            {
                len=Math.max(len,i-map.get(sum));
            }
            else
            {
                map.put(sum,i);
            }
        }
        return len;
    }
    public static void main(String[] args)
    {
        GetMaxLengthFromArray g=new GetMaxLengthFromArray();
        int[] arr={1,2,3,3};
        int k=6;
        System.out.println(g.getMaxLength(arr,k));

        int[] arr1={1,2,3,-1,-2,-3,0,5};
        int[] arr2={1,1,1,0,0,1,0,0,1,1};
        System.out.println(g.supplement1(arr1));
        System.out.println(g.supplement2(arr2));
    }
}
