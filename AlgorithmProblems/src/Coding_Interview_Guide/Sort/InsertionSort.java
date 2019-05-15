package Coding_Interview_Guide.Sort;

/**
 * 插入排序
 * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 *
 * Created by ZingBug on 2019/3/24.
 */
public class InsertionSort {
    private int[] sort(int[] a)
    {
        int preIndex;
        int current;
        for(int i=1;i<a.length;i++)
        {
            current=a[i];
            preIndex=i-1;
            while (preIndex>=0&&a[preIndex]>current)
            {
                a[preIndex+1]=a[preIndex];//往后平移一位
                preIndex--;
            }
            a[preIndex+1]=current;
        }
        return a;
    }

    public static void main(String[] args) {
        InsertionSort sort = new InsertionSort();
        int[] a = {2, 1, 5, 9, 6, 8, 4, 5, 7};
        int[] r = sort.sort(a);
        for (int i : r) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
