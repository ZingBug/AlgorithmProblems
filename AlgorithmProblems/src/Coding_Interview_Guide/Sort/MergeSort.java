package Coding_Interview_Guide.Sort;

/**
 * 归并排序
 *
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 *
 * 把长度为n的输入序列分成两个长度为n/2的子序列；
 * 对这两个子序列分别采用归并排序；
 * 将两个排序好的子序列合并成一个最终的排序序列。
 *
 * 时间复杂度为O(nlogn）
 *
 * Created by ZingBug on 2019/3/24.
 */
public class MergeSort {

    private int[] sort(int[] a,int start,int end)
    {
        int mid=(start+end)/2;
        if(start<end)
        {
            sort(a,start,mid);
            sort(a,mid+1,end);
            merge(a,start,mid,end);
        }
        return a;
    }

    //归并
    private void merge(int[] a,int start,int mid,int end)
    {
        int[] temp=new int[end-start+1];
        int i=start;
        int j=mid+1;
        int k=0;
        //把较小的数先移到新数组
        while (i<=mid&&j<=end)
        {
            if(a[i]<a[j])
            {
                temp[k++]=a[i++];
            }
            else
            {
                temp[k++]=a[j++];
            }
        }

        //把左边剩余的数移入数组
        while (i<=mid)
        {
            temp[k++]=a[i++];
        }
        //把右边剩余的数移入数组
        while (j<=end)
        {
            temp[k++]=a[j++];
        }
        //把新数组中的数覆盖a数组
        for(int x=0;x<temp.length;x++)
        {
            a[x+start]=temp[x];
        }
    }

    public static void main(String[] args)
    {
        MergeSort sort=new MergeSort();
        int[] a={2,1,5,9,6,8,4,5,7};
        int[] r=sort.sort(a,0,a.length-1);
        for(int i:r)
        {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
