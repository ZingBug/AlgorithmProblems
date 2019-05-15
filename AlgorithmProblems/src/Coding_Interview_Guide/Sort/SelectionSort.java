package Coding_Interview_Guide.Sort;

/**
 * 选择排序
 * 选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * Created by ZingBug on 2019/3/24.
 */
public class SelectionSort {

    private int[] sort(int[] a)
    {
        int minIndex=0;
        for(int i=0;i<a.length-1;i++)
        {
            minIndex=i;
            for(int j=i+1;j<a.length;j++)
            {
                if(a[j]<a[minIndex])
                {
                    minIndex=j;
                }
            }
            int temp=a[i];
            a[i]=a[minIndex];
            a[minIndex]=temp;
        }
        return a;
    }

    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        int[] a = {2, 1, 5, 9, 6, 8, 4, 5, 7};
        int[] r = sort.sort(a);
        for (int i : r) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
