package Coding_Interview_Guide.String;


/**
 * 判断字符数组中是否所有的字符都只出现过一次
 * 给定一个字符类型数组chas[]，判断chas中是否所有的字符都只出现过一次，请根据以下不同的两种要求实现两个函数。
 * 1、实现时间复杂度为O(N)的方法
 * 2、在保证额外空间复杂度为O(1)的前提下，请实现时间复杂度尽量低的方法。
 *
 * Created by ZingBug on 2019/3/23.
 */
public class IsUnique {

    //时间复杂度O(n)
    private boolean isUnique1(char[] chas)
    {
        if(chas==null)
        {
            return true;
        }
        boolean[] map=new boolean[256];
        for(char c:chas)
        {
            if(map[c])
            {
                return false;
            }
            map[c]=true;
        }
        return true;
    }

    //要求空间复杂度O(1)，先用堆排序
    private boolean isUnique2(char[] chas)
    {
        if(chas==null)
        {
            return false;
        }
        heapSort(chas);//排序
        for(int i=1;i<chas.length;i++)
        {
            if(chas[i-1]==chas[i])
            {
                return false;
            }
        }
        return true;
    }

    private void heapSort(char[] chas)
    {
        int len=chas.length;
        //构建大顶堆
        for(int i=len/2-1;i>=0;i--)
        {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(chas,i,len);
        }
        //调整堆结构，交换堆顶元素与末尾元素
        for(int j=len-1;j>0;j--)
        {
            swap(chas,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(chas,0,j);//重新对堆进行调整
        }

    }

    private void adjustHeap(char[] chas,int i,int length)
    {
        char temp=chas[i];
        for(int k=2*i+1;k<length;k=k*2+1)
        {
            if(k+1<length&&chas[k]<chas[k+1])
            {
                k++;
            }
            if(temp<chas[k])
            {
                chas[i]=chas[k];
                i=k;
            }
        }
        chas[i]=temp;
    }

    private void swap(char[] chas,int i,int j)
    {
        char temp=chas[i];
        chas[i]=chas[j];
        chas[j]=temp;
    }


    public static void main(String[] args)
    {
        IsUnique isUnique=new IsUnique();
        char[] chas1={'a','b','c'};
        char[] chas2={'1','2','1'};

        System.out.println(isUnique.isUnique1(chas1));
        System.out.println(isUnique.isUnique1(chas2));
        System.out.println(isUnique.isUnique2(chas1));
        System.out.println(isUnique.isUnique2(chas2));
    }
}
