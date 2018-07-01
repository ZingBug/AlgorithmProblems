package Coding_Interviews;

/**
 * 数组中的逆序对
 * 题目描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。即输出P%1000000007
 *
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 *
 * 数据范围：
 * 对于%50的数据,size<=10^4
 * 对于%75的数据,size<=10^5
 * 对于%100的数据,size<=2*10^5
 *
 * https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=13&tqId=11188&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 1、暴力求解，顺序扫描整个数组，每扫描到一个数字的时候，逐个比较该数字和它前面的数字的大小。如果前面的数字比它大，则这两个数字就组成一个逆序对。时间复杂度为O(n*n)
 * 2、利用归并分治方法，先求前面一半数组的逆序数，再求后面一半数组的逆序数，然后求前面一半数组比后面一半数组中大的数的个数（也就是逆序数），这三个过程加起来就是整体的逆序数目了。
 * 注意：在归并过程中，需要左右数组是排序好的，这块需要临时辅助数组来帮忙。
 * Created by ZingBug on 2018/6/28.
 */
public class InversePairs {
    private int solution1(int[] array)
    {
        //第一种方法，暴力求解
        if(array==null||array.length==0)
        {
            return -1;
        }
        int len=array.length;
        int P=0;
        for(int i=0;i<len;i++)
        {
            for(int j=0;j<i;j++)
            {
                if(array[j]>array[i])
                {
                    P++;
                    if(P>=1000000007)
                    {
                        P%=1000000007;
                    }
                }
            }
        }
        return P;
    }
    private int solution2(int[] array)
    {
        //第二种方法，归并分治
        if(array==null||array.length==0)
        {
            return -1;
        }
        return mergeSort(array,0,array.length-1);
    }

    private int mergeSort(int[] array,int left,int right)
    {

        if(left>=right)
        {
            return 0;
        }
        int mid=(left+right)/2;

        //递归调用
        int leftCount=mergeSort(array,left,mid);
        int rightCount=mergeSort(array,mid+1,right);

        //归并
        int[] temp=new int[right-left+1];//临时辅助数组
        int t=right-left;//临时数组最大下标
        int m=mid;
        int r=right;
        int count=0;
        while (m>=left&&r>=mid+1)
        {
            if(array[m]>array[r])
            {
                count+=(r-mid);//若前面数组m位置值大于后面数组r位置值，则代表前面数组m位置值，大于后面数组从mid-r之间所有的值。因为前后数组都是排序的。
                if(count>=1000000007)
                {
                    count%=1000000007;
                }
                temp[t--]=array[m--];
            }
            else
            {
                temp[t--]=array[r--];
            }
        }
        while (m>=left)
        {
            temp[t--]=array[m--];
        }
        while (r>=mid+1)
        {
            temp[t--]=array[r--];
        }
        //将辅助数组值转移到原数组中
        for(int i=0;i<=right-left;i++)
        {
            array[left+i]=temp[i];
        }

        return (leftCount+rightCount+count)%1000000007;
    }

    public int InversePairs(int[] array)
    {
        return solution2(array);
    }
    public static void main(String[] args)
    {
        InversePairs i=new InversePairs();
        int[] array={1,2,3,4,5,6,7,0};
        System.out.println(i.InversePairs(array));

    }
}
