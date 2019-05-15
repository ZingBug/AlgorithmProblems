package Coding_Interview_Guide.Sort;

/**
 * 堆排序
 * 堆排序的基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。
 * 将其与末尾元素进行交换，此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
 *
 * https://www.cnblogs.com/chengxiao/p/6129630.html
 *
 * Created by ZingBug on 2019/3/24.
 */
public class HeapSort {
    private int[] sort(int[] a)
    {
        //构建大顶堆
        for(int i=a.length/2-1;i>=0;i--)
        {
            //从第一个非叶子节点从上至下，从左到右调整
            adjustHeap(a,i,a.length);
        }
        //调整堆结构，交换堆顶元素与末尾元素
        for(int j=a.length-1;j>0;j--)
        {
            swap(a,0,j);//将堆顶元素与末尾元素进行交换，将最大值移到末尾
            adjustHeap(a,0,j);//重新对堆进行调整
        }
        return a;
    }

    //调整大顶堆
    private void adjustHeap(int[] arr,int i,int length)
    {
        int temp=arr[i];//先取出当前元素i
        for(int k=i*2+1;k<length;k=2*k+1)//从i结点的左子结点开始，也就是2i+1处开始
        {
            if(k+1<length&&arr[k]<arr[k+1])//如果左子结点小于右子结点，k指向右子结点
            {
                k++;
            }
            if(arr[k]>temp)//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            {
                arr[i]=arr[k];
                i=k;
            }
            else
            {
                break;
            }
        }
        arr[i]=temp;//将temp值放到最终的位置
    }

    //交换元素
    private void swap(int[] arr,int i,int j)
    {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        int[] a = {2, 1, 5, 9, 6, 8, 4, 5, 7};
        int[] r = sort.sort(a);
        for (int i : r) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
