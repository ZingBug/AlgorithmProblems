package Coding_Interviews;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by ZingBug on 2018/7/7.
 */
public class DuplicationOfArray {
    private boolean solution1(int numbers[],int length,int[] duplication)
    {
        //第一种方法，用排序的方法。时间复杂度为排序的O(nlogn)，空间复杂度为O(1)
        if(numbers==null||length<1)
        {
            return false;
        }
        Arrays.sort(numbers);
        int i=0;
        while (i+1<length)
        {
            if(numbers[i]==numbers[i+1])
            {
                duplication[0]=numbers[i];
                return true;
            }
            ++i;
        }
        return false;
    }
    private boolean solution2(int numbers[],int length,int[] duplication)
    {
        //第二种方法，用哈希表的方式，时间复杂度为O(n)，空间复杂度为O(n)
        if(numbers==null||length<1)
        {
            return false;
        }
        HashSet<Integer> set=new HashSet<>();
        for(int val:numbers)
        {
            if(set.contains(val))
            {
                duplication[0]=val;
                return true;
            }
            else
            {
                set.add(val);
            }
        }
        return false;
    }

    private boolean solution3(int numbers[],int length,int[] duplication)
    {
        //第三种方法，利用交换思想。时间复杂度为O(n)，空间复杂度为O(1)
        //从头扫到尾，只要当前元素值与下标不同，就做一次判断,numbers[i]与numbers[numbers[i]]，相等就认为找到了
        //重复元素，返回true,否则就交换两者，继续循环。直到最后还没找到认为没找到重复元素，返回false
        if(numbers==null||length<1)
        {
            return false;
        }
        for(int i=0;i<length;i++)
        {
            if(numbers[i]!=i)
            {
                if(numbers[i]==numbers[numbers[i]])
                {
                    //相当于找到了
                    duplication[0]=numbers[i];
                    return true;
                }
                else
                {
                    //交换
                    int temp=numbers[numbers[i]];
                    numbers[numbers[i]]=numbers[i];
                    numbers[i]=temp;
                }
            }
        }
        return false;
    }

    public boolean duplicate(int numbers[],int length,int[] duplication)
    {
        return solution3(numbers,length,duplication);
    }
    public static void main(String[] args)
    {
        DuplicationOfArray d=new DuplicationOfArray();
        int[] array1={2,3,1,0,2,5,3};
        int[] duplication=new int[1];
        boolean result=d.duplicate(array1,array1.length,duplication);
        System.out.println(result);
        if(result)
        {
            System.out.println(duplication[0]);
        }

    }
}
