package Coding_Interview_Guide.Sort;

/**
 * 冒泡排序
 * 这个算法的名字由来是因为越大的元素会经由交换慢慢“浮”到数列的末端。
 * 相当于每次循环都把最大值放到最后
 *
 * 冒泡排序的优点：每进行一趟排序，就会少比较一次，因为每进行一趟排序都会找出一个较大值。
 * 第一趟比较之后，排在最后的一个数一定是最大的一个数，第二趟排序的时候，只需要比较除了最后一个数以外的其他的数，同样也能找出一个最大的数排在参与第二趟比较的数后面，
 * 第三趟比较的时候，只需要比较除了最后两个数以外的其他的数，以此类推……也就是说，没进行一趟比较，每一趟少比较一次，一定程度上减少了算法的量。
 *
 * 平均时间复杂度为O(n*n)
 *
 * Created by ZingBug on 2019/3/24.
 */
public class BubbleSort {
    private int[] sort(int[] a)
    {
        for(int i=0;i<a.length-1;i++)
        {
            //每次循环都挑选出最大值，然后放入最后
            for(int j=0;j<a.length-1-i;j++)
            {
                if(a[j]>a[j+1])
                {
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        int[] a = {2, 1, 5, 9, 6, 8, 4, 5, 7};
        int[] r = sort.sort(a);
        for (int i : r) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
