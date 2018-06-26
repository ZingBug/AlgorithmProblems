package Coding_Interviews;

/**
 * 解题思路
 * 1、最简单粗暴的方法，进行双层循环遍历。找出最大值,时间复杂度为O(n*n)
 * 2、第二种方法比较简洁，从头开始累加数组的元素，如果累加元素<0,则舍去前面的累加值，从后面的元素开始累加。时间复杂度为O(n)
 * 3、第三种方法是动态规划，和第二种方法有异曲同工之妙。通过状态方程sum=Math.max(sum+array[i],array[i]);时间复杂度也为O(n)
 * Created by ZingBug on 2018/6/26.
 */
public class FindGreatestSumOfSubArray {
    private int solution1(int[] array)
    {
        int n=array.length;

        int max=Integer.MIN_VALUE;

        for(int i=0;i<n;i++)
        {
            int sum=0;
            for(int j=i;j<n;j++)
            {
                if(sum+array[j]>0)
                {
                    sum+=array[j];
                    if(sum>max)
                    {
                        max=sum;
                    }
                }
                else
                {
                    break;
                }

            }
        }
        return max;
    }
    private int solution2(int[] array)
    {
        int n=array.length;
        int max=Integer.MIN_VALUE;
        int sum=0;
        for(int i=0;i<n;i++)
        {
            if(sum<0)
            {
                sum=0;
            }
            sum+=array[i];
            if(sum>max)
            {
                max=sum;
            }
        }
        return max;
    }

    private int solution3(int[] array)
    {
        int n=array.length;
        int max=Integer.MIN_VALUE;
        int sum=0;

        for(int i=0;i<n;i++)
        {
            sum=Math.max(sum+array[i],array[i]);
            if(sum>max)
            {
                max=sum;
            }
        }

        return max;

    }

    public int FindGreatestSumOfSubArray(int[] array)
    {
        return solution3(array);
    }
    public static void main(String[] args)
    {
        int[] array={6,-3,-2,7,-15,1,2,2};
        FindGreatestSumOfSubArray f=new FindGreatestSumOfSubArray();
        System.out.println(f.FindGreatestSumOfSubArray(array));
    }
}
