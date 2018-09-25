package Algorithms_4th.Sorting;
/**
 * 查找中位数
 * 查找队列中的某一顺序位，比如k=N/2为中位数
 * by ZingBug 2017/9/1
 */
public class SelectMedian {

    /**
     * 选择队列中顺序位的第k位
     * @param a
     * @param k
     * @return 顺序位的值
     */
    public static Comparable select(Comparable[] a,int k)
    {
        int lo=0;
        int hi=a.length-1;
        while (hi>lo)
        {
            int j=partition(a,lo,hi);
            if(j==k) return a[k];
            else if(j<k) lo=j+1;
            else if(j>k) hi=j-1;
        }
        return a[k];
    }
    /**
     * 切分
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a,int lo,int hi)
    {
        Comparable k=a[lo];
        int i=lo;
        int j=hi+1;
        while (true)
        {
            while (less(a[++i],k))
            {
                if(i==hi)
                    break;
            }
            while (less(k,a[--j]))
            {
                if(j==lo)
                    break;
            }
            if(i>=j)
                break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }
    private static void exch(Comparable[] a,int i,int j)
    {
        Comparable swap=a[i];
        a[i]=a[j];
        a[j]=swap;
    }
    private static boolean less(Comparable v,Comparable w)
    {
        return v.compareTo(w)<0;
    }

    public static void main(String[] args)
    {
        String[] a= new String[] {"S","O","R","T","E","X","A","M","P","L","E"};
        int N=a.length/2;//寻找中位数
        System.out.println(select(a,N));
    }
}
