package Algorithms_4th.Sorting;

/**
 * 快速排序
 * 它将一个数组分成两个子数组，将两部分独立地排序。该方法的关键在于切分。
 * 快速排序和归并排序是互补的。
 * by ZingBug 2017/8/31
 */
public class Quick {
    private static void sort(Comparable[] a)
    {
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int lo,int hi)
    {
        if(lo>=hi) return;
        int j=partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
    /**
     * 切分方法
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a,int lo,int hi)
    {
        Comparable k=a[lo];//先选取a[lo]作为切分元素
        int i=lo;
        int j=hi+1;
        while (true)
        {
            while (less(a[++i],k))//从数组的左端开始向右扫描直到找到一个大于等于它的元素
            {
                if(i==hi)
                    break;
            }
            while (less(k,a[--j]))//从数组的右端开始向左扫描直到找到一个小于等于它的元素
            {
                if(j==lo)
                    break;
            }
            if(i>=j)
                break;
            exch(a,i,j);//交换位置
        }
        exch(a,lo,j);
        return j;
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
