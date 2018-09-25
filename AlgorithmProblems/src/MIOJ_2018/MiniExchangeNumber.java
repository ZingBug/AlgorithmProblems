package MIOJ_2018;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * 最小交换次数
 * 描述
 * 给出一个无序数列，每次只能交换相邻两个元素，求将原数列变成递增数列的最少交换次数。 如：数列：2,3,1，交换3和1后变成：2,1,3；交换1和2之后变成：1,2,3。总共交换2次。
 *
 * 输入
 * 逗号隔开的正整数数列
 *
 * 输出
 * 正整数
 *
 * 输入样例
 * 2,3,1
 * 输出样例
 * 2
 * Created by ZingBug on 2018/9/6.
 */
public class MiniExchangeNumber {
    //如果只是交换相邻两数，那么最少交换次数为该序列的逆序数。如果是交换任意两数，则最少交换次数为数字的总个数减去循环节的个数
    //求逆序数有两种做法，一种是归并排序，另一种是离散化 + 树状数组
    private static int result;
    private static String solution(String line)
    {
        //先来用归并排序
        String[] array=line.split(",");
        int[] num=new int[array.length];
        int i=0;
        for(String str:array)
        {
            int n=Integer.valueOf(str);
            num[i++]=n;
        }
        result=0;
        sort(num,0,num.length-1);
        return String.valueOf(result);
    }

    //注意：数组是引用传递
    private static void sort(int[] num,int low,int high)
    {
        int mid=(low+high)/2;
        if(low<high)
        {
            //左边排序
            sort(num,low,mid);
            //右边排序
            sort(num,mid+1,high);
            //左右合并分治
            mergeSort(num,low,mid,high);
        }

    }

    //分治
    private static void mergeSort(int[] num,int low,int mid,int high)
    {
        int[] temp=new int[high-low+1];
        int i=low;//左指针
        int j=mid+1;//右指针
        int k=0;
        //把较小的数先移到新数组
        while (i<=mid&&j<=high)
        {
            if(num[i]<=num[j])
            {
                temp[k++]=num[i++];
            }
            else
            {
                temp[k++]=num[j++];
                result+=(mid-i+1);
            }
        }
        //把左边剩余的数移到数组
        while (i<=mid)
        {
            temp[k++]=num[i++];
        }
        //把右边生于的数移入数组
        while (j<=high)
        {
            temp[k++]=num[j++];
        }

        //把新数组中的数覆盖nums数组
        for(i=0;i<temp.length;i++)
        {
            num[i+low]=temp[i];
        }
    }

    public static void main(String[] args)
    {
        MiniExchangeNumber m=new MiniExchangeNumber();
        String line1="2,3,1";
        String line2="4,8,2,7,5,6,1,3";
        System.out.println(m.solution(line1));
        System.out.println(m.solution(line2));
    }
}
