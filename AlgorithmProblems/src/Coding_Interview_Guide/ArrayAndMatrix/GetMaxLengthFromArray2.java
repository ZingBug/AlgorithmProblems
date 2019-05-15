package Coding_Interview_Guide.ArrayAndMatrix;

/**
 * 未排序数组中累加值小于或等于给定值的最长子数组长度
 * 给定一个无序数组arr，其中元素可正、可负、可0，给定一个整数k。求arr所有的子数组中累加和小于或等于k的最小子数组长度。
 * 例如：arr=[3,-2,-4,0,6]，k=-2，相加和小于或等于-2的最长子数组为{3,-2,-4,0}，所以结果返回4。
 * Page358
 * Created by ZingBug on 2018/11/29.
 */
public class GetMaxLengthFromArray2 {
    private int getMaxLength(int[] arr,int k)
    {
        if(arr==null||arr.length<=0)
        {
            return 0;
        }
        int[] h=new int[arr.length+1];
        h[0]=0;
        int sum=0;
        for(int i=0;i!=arr.length;i++)
        {
            sum+=arr[i];
            h[i+1]=Math.max(h[i],sum);
        }
        int len=0;
        int pre=-1;
        int res=0;
        sum=0;
        for(int i=0;i!= arr.length;i++)
        {
            sum+=arr[i];
            pre=getIndex(h,sum-k);
            len=pre==-1?0:i-pre+1;
            res=Math.max(res,len);
        }
        return res;
    }
    //在有序数组中通过二分法查找大于等于num的值出现第一次的位置
    private int getIndex(int[] arr,int num)
    {
        int left=0;
        int mid=0;
        int right=arr.length-1;
        int res=-1;
        while (left<right)
        {
            mid=(left+right)/2;
            if(arr[mid]>=num)
            {
                res=mid;
                right=mid-1;
            }
            else
            {
                left=mid+1;
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        GetMaxLengthFromArray2 g=new GetMaxLengthFromArray2();
        int[] arr={3,-2,-4,0,6};
        int k=-2;
        System.out.println(g.getMaxLength(arr,k));
    }
}
