package Coding_Interviews;

/**
 * 数字在排序数组中出现的次数
 * 题目描述
 * 统计一个数字在排序数组中出现的次数。
 *
 * https://www.nowcoder.com/practice/70610bf967994b22bb1c26f9ae901fa2?tpId=13&tqId=11190&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 对于排序数组，使用二分法是很好的选择，时间复杂度为O(logN)
 * Created by ZingBug on 2018/6/30.
 */
public class GetNumberOfK {
    private int solution1(int[] array,int k)
    {
        //第一种方法是简单的暴力求解
        int num=0;
        boolean b=true;
        for(int val:array)
        {
            if(!b&&val!=k)
            {
                break;
            }
            if(val==k)
            {
                b=false;
                num++;
            }
        }
        return num;
    }
    private int solution2(int[] array,int k)
    {
        //第二种方法，用二分法去做

        if(array==null)
        {
            return -1;
        }
        int right=binarySearch(array,0,array.length-1,k);//得到某一个k的位置
        int left=right;
        if(right==-1)
        {
            return 0;
        }
        //左右搜索
        while (left>0&&array[left-1]==k)
        {
            left--;
        }
        while (right<array.length-1&&array[right+1]==k)
        {
            right++;
        }
        return right-left+1;

    }
    private int binarySearch(int[] array,int start,int end,int k)
    {
        if(array==null||start>end)
        {
            return -1;
        }
        while (start<=end)
        {
            int mid=(end-start)/2+start;
            if(array[mid]==k)
            {
                return mid;
            }
            else if(array[mid]<k)
            {
                start=mid+1;
            }
            else
            {
                end=mid-1;
            }
        }
        return -1;
    }
    public int GetNumberOfK(int[] array,int k)
    {
        return solution2(array,k);
    }

    public static void main(String[] args)
    {
        int[] array1={1,3,3,3,3,4,5};
        int k1=2;
        int[] array2={3};
        int k2=3;
        GetNumberOfK g=new GetNumberOfK();
        System.out.println(g.GetNumberOfK(array2,k2));
    }
}
