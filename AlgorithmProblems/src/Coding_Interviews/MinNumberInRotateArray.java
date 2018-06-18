package Coding_Interviews;

/**
 * 旋转数组的最小数字
 * 题目描述
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * https://www.nowcoder.com/practice/9f3231a991af4f55b95579b44b7a01ba?tpId=13&tqId=11159&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class MinNumberInRotateArray {
    public int solution1(int[] array)
    {
        //第一种最简单的方法就是遍历整个数组，找出最小的元素，复杂度O(N)
        int min=Integer.MAX_VALUE;
        for(int val:array)
        {
            min=Math.min(val,min);
        }
        return min;
    }
    public int solution2(int[] array)
    {
        //第二种方法是二元查找法
        int len=array.length;
        int left=0;
        int right=len-1;
        int mid=left;
        //当最左边值大于最右边值
        while (array[left]>=array[right])
        {
            //如果此时数组只剩下两个值
            if(right-left==1)
            {
                //最小的就是右边
                mid=right;
                break;
            }
            //如果数组长度在2个以上
            mid=(left+right)/2;
            //假如最左边和中间以及最右边值都相等，只能进行顺序查找，如{1,1,1,0,1}
            if(array[left]==array[right]&&array[left]==array[mid])
            {
                int min=array[left];
                for(int i=left+1;i<=right;i++)
                {
                    min=Math.min(min,array[i]);
                }
                return min;
            }
            // 如果最左边小于等于中间，说明最小值在后半部分，把mid位置标记为最左侧如{3,4,5,1,2}
            if(array[mid]>=array[left])
            {
                left=mid;
            }
            // 如果最左侧大于等于中间值，说明最小值在前半部分，把mid位置标记为最右侧{4,5,1,2,3}
            else if(array[mid]<=array[left])
            {
                right=mid;
            }
        }
        return array[mid];
    }
    public int minNumberInRotateArray(int[] array)
    {
        if(array.length==0)
        {
            return -1;
        }
        return solution2(array);
    }

    public static void main(String[] args)
    {
        int[] array={3,4,5,1,2};
        MinNumberInRotateArray m=new MinNumberInRotateArray();
        System.out.println(m.minNumberInRotateArray(array));
    }
}
