package Algorithms_4th.Sorting;

/**
 * 三向切分的快速排序
 * 将数组切分为三部分，分别对应小于、等于和大于切分元素的数组元素
 * 额外的交换用于和切分元素不等的元素
 * by ZingBug 2017/8/31
 */
public class Quick3way{
        private static void sort(Comparable[] a)
        {
            sort(a,0,a.length-1);
        }
        private static void sort(Comparable[] a,int lo,int hi)
        {
            if(lo>=hi) return;
            int lt=lo;
            int i=lo+1;
            int gt=hi;
            Comparable k=a[lo];
            while (i<=gt)
            {
                int cmp=a[i].compareTo(k);
                if(cmp<0)
                    exch(a,lt++,i++);
                if(cmp>0)
                    exch(a,i,gt--);
                else
                    i++;
            }
            sort(a,lo,lt-1);
            sort(a,gt+1,hi);
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
