package Coding_Interviews;

/**
 * 调整数组顺序使奇数位于偶数前面
 * 题目描述
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * https://www.nowcoder.com/practice/beb5aa231adc45b2a5dcc5b62c93f593?tpId=13&tqId=11166&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 这个题和腾讯2017暑假实习题中的字符移动(Tencent_Summer_Intern_2017.CharacterShift)相似。都是从后往前遍历，遇到偶数就依次往后移动。
 *
 * Created by ZingBug on 2018/6/19.
 */
public class ReOrderArray {
    public void reOrderArray(int[] array)
    {
        int len=array.length;
        for(int i=len-1;i>=0;i--)
        {
            if(array[i]%2==0)
            {
                //如果当前为偶数，需要往后移动
                int j=i;
                while ((j+1<len)&&(array[j+1]%2==1))
                {
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                    j++;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        int[] array={1,2,3,4,5,6,7};
        ReOrderArray r=new ReOrderArray();
        r.reOrderArray(array);
        for(int val:array)
        {
            System.out.print(val+" ");
        }
    }
}
