package Coding_Interview_Guide.ArrayAndMatrix;

/**
 * 未排序正数数组中累加和为给定值的最长子数组长度
 * 给定一个数组arr，该数组无序，但每个值均为正数，再给定一个正数k。求arr的所有子数组中所有元素相加和为k的最长子数组长度。
 * 例如，arr=[1,2,1,1,1]，k=3
 * 累加和为3的最长子数组为[1,1,1]，所以结果返回3
 * 解题思路：用两个位置来标记子数组的左右两头，然后依次往后面移动。
 * Page354
 * Created by ZingBug on 2018/11/28.
 */
public class GetMaxLengthFromPositiveArray {
    private int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {return 0;

        }
        int left=0;
        int right=0;
        int len=0;
        int sum=arr[0];
        while (right<arr.length)
        {
            if(sum==k)
            {
                len=Math.max(len,right-len+1);
                right++;
                if(right==arr.length)
                {
                    break;
                }
                sum+=arr[right];
            }
            else if(sum<k)
            {
                right++;
                if(right==arr.length)
                {
                    break;
                }
                sum+=arr[right];
            }
            else
            {
                sum-=arr[left];
            }
        }
        return len;
    }

    public static void main(String[] args)
    {
        GetMaxLengthFromPositiveArray g=new GetMaxLengthFromPositiveArray();
        int[] arr={1,2,1,1,1};
        int k=3;
        System.out.println(g.getMaxLength(arr,k));
    }
}
