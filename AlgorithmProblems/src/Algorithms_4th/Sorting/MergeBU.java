package Algorithms_4th.Sorting;

/**
 * 归并排序
 * 自低往上，从底部开始分
 * 要将一个数组排序，可以先（递归地）将它分成两半分别排序，然后将结果归并起来。
 * by ZingBug 2017/8/30
 */
public class MergeBU {
    private static Comparable[] aux;
    private static void sort(Comparable[] a)
    {
        int N=a.length;
        aux=new Comparable[N];
        for(int sz=1;sz<N;sz=sz+sz)//从最底层逐渐往上，每一层子数组长度都是上一层的2倍
        {
            for(int lo=0;lo<N-sz;lo=lo+sz+sz)//某一层归并，首先从相邻元素组成的子数组开始
            {
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }
    /**
     * 将分别有序的子数组a[lo..mid]和a[mid+1..hi]归并成一个有序的完整数组
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    private static void merge(Comparable[] a,int lo,int mid,int hi)
    {
        int i=lo;
        int j=mid+1;
        for(int k=lo;k<=hi;++k)//将a[lo..hi]复制到aux[lo..hi]
        {
            aux[k]=a[k];
        }
        for(int k=lo;k<=hi;++k)//归并操作
        {
            if(i>mid)
            {
                //如果第一个子数组全部放入归并数组中
                a[k]=aux[j++];
            }
            else if(j>hi)
            {
                //如果第二个子数组全部放入归并数组中
                a[k]=aux[i++];
            }
            else if(less(aux[j],aux[i]))
            {
                //aux[j]比aux[i]小
                a[k]=aux[j++];
            }
            else
            {
                //aux[i]比aux[j]小
                a[k]=aux[i++];
            }
        }
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
        String[] a= new String[] {"S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
