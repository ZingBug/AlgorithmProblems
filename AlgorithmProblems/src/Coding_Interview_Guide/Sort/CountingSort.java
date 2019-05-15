package Coding_Interview_Guide.Sort;

/**
 * 计数排序
 * 计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
 * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
 *
 * 时间复杂度为O(n)
 *
 * https://www.cnblogs.com/developerY/p/3166462.html
 *
 * Created by ZingBug on 2019/3/25.
 */
public class CountingSort {

    private int[] sort(int[] a,int maxValue)
    {
        int[] c=new int[maxValue+1];//构建C数组
        int[] b=new int[a.length];//构建B数组
        int sum=0;
        for(int i=0;i<a.length;i++)
        {
            c[a[i]]++;//统计A数组各元素的个数，存入C数组
        }
        for(int i=0;i<c.length;i++)//修改C数组
        {
            sum+=c[i];
            c[i]=sum;
        }
        for(int i=a.length-1;i>=0;i--)//遍历A数组，构造B数组
        {
            b[c[a[i]]-1]=a[i];//将A中该元素放到排序后数组B中指定的位置
            c[a[i]]--;//将C中该元素-1，方便存放下一个同样大小的元素
        }

        return b;
    }
    public static void main(String[] args)
    {
        CountingSort sort=new CountingSort();
        int[] a={2,1,5,9,6,8,4,5,7};
        int[] r=sort.sort(a,9);
        for(int i:r)
        {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
