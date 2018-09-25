package Algorithms_4th.Strings;

/**
 * 三向字符串快速排序
 * 根据键的首字母将数组切分为三部分，仅在中间子数组中的下一个字符（因为键的首字母都与切分字符相等）继续递归排序
 * Created by ZingBug on 2017/10/18.
 */
public class Quick3string {
    private static int charAt(String s,int d)
    {
        if(d<s.length())
        {
            return s.charAt(d);
        }
        else
        {
            return -1;
        }
    }
    private static void exch(String[] a,int k,int l)
    {
        if(k<0||l<0||k>=a.length||l>a.length)
        {
            throw new IllegalArgumentException("超出索引");
        }
        String temp=a[k];
        a[k]=a[l];
        a[l]=temp;
    }
    public static void sort(String[] a)
    {
        sort(a,0,a.length-1,0);
    }
    private static void sort(String[] a,int lo,int hi,int d)
    {
        if(hi<=lo)
        {
            return;
        }
        int lt=lo,gt=hi;
        int v=charAt(a[lo],d);
        int i=lo+1;
        while (i<=gt)
        {
            int t=charAt(a[i],d);
            if(t<v)
            {
                exch(a,lt++,i++);
            }
            else if(t>v)
            {
                exch(a,i,gt--);
            }
            else
            {
                i++;
            }
        }
        sort(a,lo,lt-1,d);
        if(v>=0)
        {
            sort(a,lt,gt,d+1);
        }
        sort(a,gt+1,hi,d);
    }
    public static void main(String[] args)
    {
        String a[]={"4P","2IY","3CI"};
        sort(a);
        for(int i=0;i<a.length;i++)
        {
            System.out.println(a[i]);
        }
    }
}
