package Algorithms_4th.Sorting;
/**
 * 堆排序
 * 主要过程分为两部分：堆的构造和下沉排序
 * 先进行堆的构造，构造一个堆有序的数组并使自大元素位于数组的开头（次大的元素在附近）
 * 继而进行排序，将堆中的最大元素删除，然后放入堆缩小后数组中空出的位置（将数组开头最大的元素交换到末尾），然后再在数组开头元素进行下沉排序，再次寻找剩下数组的最大元素。依次排序
 * 与书不同，我写的这个堆是从0开始实现的，而非1。便于排序处理
 * 将N个元素排序，堆排序只需少于（2NlgN+2N）次比较（以及一半次数的交换）
 * by ZingBug 2017/9/1
 */
public class HeapSort {

    private static void sort(Comparable[] a)
    {
        //排序下标从0开始
        int N=a.length-1;

        for(int k=N/2;k>=0;k--)
        {
            sink(a,k,N);
        }
        while (N>0)
        {
            exch(a,0,N--);
            sink(a,0,N);
        }
    }
    /**
     * 下沉操作，使堆有序化
     * @param a 数组
     * @param k 下沉元素下标
     * @param N 数组元素总个数
     */
    private static void sink(Comparable[] a,int k,int N)
    {
        while (2*k+1<=N)
        {
            int j=2*k+1;
            if(j+1<=N&&less(a,j,j+1))
            {
                j++;
            }
            if(less(a,j,k))
                break;
            exch(a,k,j);
            k=j;
        }
    }
    private static void exch(Comparable[] a,int i,int j)
    {
        Comparable swap=a[i];
        a[i]=a[j];
        a[j]=swap;
    }
    private static boolean less(Comparable[] a,int i,int j)
    {
        return a[i].compareTo(a[j])<0;
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
    private static boolean isSorted(Comparable[] a)
    {
        for(int i=1;i<a.length;i++)
        {
            if(less(a,i,i-1))
            {
                return false;
            }
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
