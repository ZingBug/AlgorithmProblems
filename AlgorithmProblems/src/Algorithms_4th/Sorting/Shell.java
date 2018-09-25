package Algorithms_4th.Sorting;

/**
 * 希尔排序
 * 实质是分组插入排序，隔步长选择一组元素进行排序，然后缩短步长排序，类推，最后步长为1，完成排序。
 * by ZingBug 2017/8/30
 */
public class Shell {
    private static void sort(Comparable[] a)
    {
        int N=a.length;
        int h=1;
        while (h<N/3)
        {
            h=3*h+1;
        }
        while (h>=1)
        {
            //将数组变为h有序
            for(int i=h;i<N;i++)
            {
                //将a[i]插入到a[i-h],a[i-2*h]...之中
                for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h)//左侧元素都是有序的
                {
                    exch(a,j,j-h);
                }
            }
            h=h/3;
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
