package Coding_Interview_Guide.Sort;

/**
 * 快速排序
 * 快速排序的基本思想：选择一个关键值作为基准值。比基准值小的都在左边序列（一般是无序的），比基准值大的都在右边（一般是无序的）。一般选择序列的第一个元素。
 *
 * 一次循环：从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置，如果没有继续比较下一个，直到找到第一个比基准值小的值才交换。
 * 找到这个值之后，又从前往后开始比较，如果有比基准值大的，交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的值才交换。
 * 直到从前往后的比较索引>从后往前比较的索引，结束第一次循环，此时，对于基准值来说，左右两边就是有序的了。
 * 接着分别比较左右两边的序列，重复上述的循环。
 *
 * Created by ZingBug on 2019/3/24.
 */
public class QuickSort {

    private int[] sort(int[] a,int low,int high)
    {
        int start=low;
        int end=high;
        int key=a[low];

        while (end>start)
        {
            //从后往前比较
            while (end>start&&a[end]>=key)//如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
            {
                end--;
            }
            if(a[end]<=key)
            {
                int temp=a[end];
                a[end]=a[start];
                a[start]=temp;
            }
            //从前往后比较
            while (end>start&&a[start]<=key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
            {
                start++;
            }
            if(a[start]>=key)
            {
                int temp=a[start];
                a[start]=a[end];
                a[end]=temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        if(start>low)
        {
            sort(a,low,start-1);//左边序列。第一个索引位置到关键值索引-1
        }
        if(end<high)
        {
            sort(a,end+1,high);//右边序列。从关键值索引+1到最后一个
        }
        return a;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] a = {2, 1, 5, 9, 6, 8, 4, 5, 7};
        int[] r = sort.sort(a, 0, a.length - 1);
        for (int i : r) {
            System.out.print(i + " ");
        }
        System.out.println();

    }


}
