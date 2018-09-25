package Algorithms_4th.Sorting;

/**
 * 快速的三向切分的快速排序
 * 将数组切分为三部分，分别对应小于、等于和大于切分元素的数组元素
 * 额外的交换用于和切分元素相等的元素
 * 对应练习2.3.22
 * 参考：http://algs4.cs.princeton.edu/23quicksort/QuickX.java.html
 * by ZingBug 2017/8/31
 */
public class Quick3way2 {
    private static void sort(Comparable[] a)
    {
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int lo,int hi)
    {
        if(lo>=hi) return;
        int p=lo;
        int i=lo;
        int j= hi+1;
        int q=hi+1;
        Comparable k=a[lo];
        while (true)
        {
            while (less(a[++i],k))
            {
                if(i==hi)
                    break;
            }
            while (less(k,a[--j]))
            {
                if(i==lo)
                    break;
            }
            if(i==j&&equal(a[i],k))
            {
                exch(a,++p,i);
            }
            if(i>=j)
            {
                break;
            }
            exch(a,i,j);
            if(equal(a[i],k))
            {
                exch(a,i,++p);
            }
            if(equal(a[j],k))
            {
                exch(a,j,--q);
            }

        }
        for(int l=lo;l<=p;l++)
        {
            exch(a,l,j--);
        }
        for(int l=hi;l>=q;l--)
        {
            exch(a,l,i++);
        }
        sort(a,lo,j);
        sort(a,i,hi);
    }
    private static boolean equal(Comparable v,Comparable w)
    {
        return v.compareTo(w)==0;
    }
    private static boolean less(Comparable v,Comparable w)
    {
        return v.compareTo(w)<0;
    }
    private static void exch(Comparable[] a,int i,int j)
    {
        if(i==j) return;//省区下面步骤
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
    private static void show(Comparable[] a)
    {
        //在单行中打印数组
        for(int i=0;i<a.length;++i)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static boolean isSorted(Comparable[] a)
    {
        //测试数组元素是否有序
        for(int i=1;i<a.length;++i)
        {
            if(less(a[i],a[i-1]))
                return false;
        }
        return true;
    }
    public static void main(String[] args)
    {
        String[] a= new String[] {"S","O","R","T","E","X","A","M","P","S","L","E"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
