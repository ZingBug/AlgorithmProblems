package Algorithms_4th.Sorting;

/**
 * 插入排序
 * 将数组中的每一个元素插入到其右侧有序元素的适当位置
 * by ZingBug 2017/8/30
 */
public class Insertion {
    private static void sort(Comparable[] a)
    {
        int N=a.length;
        for(int i=1;i<N;++i)
        {
            for(int j=i;j>0&&less(a[j],a[j-1]);--j)
            {
                exch(a,j,j-1);//相邻两个换
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
