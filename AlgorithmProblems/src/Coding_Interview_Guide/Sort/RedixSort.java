package Coding_Interview_Guide.Sort;

/**
 * 基数排序
 * 基本思想是：将整数按位数切割成不同的数字，然后按每个位数分别比较。
 *
 * 将所有待比较数值统一为统一位数长度，接着从最低位开始，依次进行排序。
 * 1. 按照个位数进行排序。
 * 2. 按照十位数进行排序。
 * 3. 按照百位数进行排序。
 * 等等
 * 排序后，数列就变成了一个有序序列。
 *
 * 每位排序过程相当于计数排序（桶排序）
 *
 * https://www.cnblogs.com/skywang12345/p/3603669.html
 * Created by ZingBug on 2019/3/25.
 */
public class RedixSort {

    private int[] sort(int[] arr)
    {
        int exp=1;//指数。当对数组个位进行排序的时候，exp=1；按十位排序的时候，exp=10；
        int max=Integer.MIN_VALUE;
        for(int a:arr)
        {
            max=Math.max(a,max);//得到最大值
        }
        for(;max/exp>0;exp*=10)
        {
            countingSort(arr,exp);
        }
        return arr;
    }

    //对数组按照"某个位数"进行排序(计数排序)
    private void countingSort(int a[],int exp)
    {
        int output[]=new int[a.length];
        int i;
        int buckets[]=new int[10];

        //将数据出现的次数存储在buckets[]中
        for(i=0;i<a.length;i++)
        {
            buckets[(a[i]/exp)%10]++;
        }
        //更改buckets[i]。目的是让更改后的buckets[i]的值，是该数据在output[]中的位置。类似于计数排序
        for(i=1;i<10;i++)
        {
            buckets[i]+=buckets[i-1];
        }
        //将数据存储到临时数组output[]中
        for(i=a.length-1;i>=0;i--)
        {
            output[buckets[(a[i]/exp)%10]-1]=a[i];
            buckets[(a[i]/exp)%10]--;
        }
        //将排序好的数据赋值给a[]
        for(i=0;i<a.length;i++)
        {
            a[i]=output[i];
        }
    }

    public static void main(String[] args)
    {
        RedixSort sort = new RedixSort();
        int[] a = {2, 1, 5, 9, 6, 8, 4, 5, 7};
        int[] r = sort.sort(a);
        for (int i : r) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
