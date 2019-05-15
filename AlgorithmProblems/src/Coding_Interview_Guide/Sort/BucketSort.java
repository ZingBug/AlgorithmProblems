package Coding_Interview_Guide.Sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 桶排序
 * 桶排序的基本思想是：把数组 arr 划分为n个大小相同子区间（桶），每个子区间各自排序，最后合并。
 *
 * 1.找出待排序数组中的最大值max、最小值min
 * 2.我们使用 动态数组ArrayList 作为桶，桶里放的元素也用 ArrayList 存储。桶的数量为(max-min)/arr.length+1
 * 3.遍历数组 arr，计算每个元素 arr[i] 放的桶
 * 4.每个桶各自排序
 * 5.遍历桶数组，把排序好的元素放进输出数组
 *
 * 桶排序最好情况下使用线性时间O(n)
 *
 * Created by ZingBug on 2019/3/25.
 */
public class BucketSort {

    private int[] sort(int[] arr)
    {
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int a:arr)
        {
            max=Math.max(a,max);
            min=Math.min(a,min);
        }

        //桶的数量
        int bucketNum=(max-min)/arr.length+1;
        ArrayList<ArrayList<Integer>> bucketArr=new ArrayList<>(bucketNum);

        for(int i=0;i<bucketNum;i++)
        {
            bucketArr.add(new ArrayList<>());
        }

        //将每个元素放入桶中
        for(int a:arr)
        {
            int num=(a-min)/arr.length;
            bucketArr.get(num).add(a);
        }

        int[] res=new int[arr.length];
        int k=0;

        //对每个桶进行排序
        for(int i=0;i<bucketNum;i++)
        {
            Collections.sort(bucketArr.get(i));
            for(int j=0;j<bucketArr.get(i).size();j++)
            {
                res[k++]=bucketArr.get(i).get(j);
            }
        }

        return res;

    }

    public static void main(String[] args)
    {
        BucketSort sort = new BucketSort();
        int[] a = {2, 1, 5, 9, 6, 8, 4, 5, 7};
        int[] r = sort.sort(a);
        for (int i : r) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
