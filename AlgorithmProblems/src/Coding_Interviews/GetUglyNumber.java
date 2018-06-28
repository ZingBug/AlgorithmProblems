package Coding_Interviews;

/**
 * 丑数
 * 题目描述
 * 把只包含因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 *
 * https://www.nowcoder.com/practice/6aa9e04fc3794f68acf8778237ba065b?tpId=13&tqId=11186&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 所有丑数都是由之前的丑数×2或×3或×5得到的。而且乘以2后，下次乘2的数就向后移
 * Created by ZingBug on 2018/6/27.
 */
public class GetUglyNumber {
    public int GetUglyNumber_Solution(int index)
    {
        if(index<1)
        {
            return 0;
        }
        int count=1;
        int[] ugly=new int[index+1];
        ugly[0]=1;
        int p2=0;
        int p3=0;
        int p5=0;
        while (count<=index)
        {
            int ugly2=ugly[p2]*2;
            int ugly3=ugly[p3]*3;
            int ugly5=ugly[p5]*5;
            if(ugly2<=ugly3&&ugly2<=ugly5)
            {
                p2++;//后移
                if(ugly2==ugly[count-1])//如果与上一个值重复，则略过
                {
                    continue;
                }
                ugly[count]=ugly2;
            }
            else if(ugly3<=ugly2&&ugly3<=ugly5)
            {
                p3++;
                if(ugly3==ugly[count-1])
                {
                    continue;
                }
                ugly[count]=ugly3;
            }
            else if(ugly5<=ugly2&&ugly5<=ugly3)
            {
                p5++;
                if(ugly5==ugly[count-1])
                {
                    continue;
                }
                ugly[count]=ugly5;
            }
            count++;
        }
        return ugly[index-1];
    }

    public static void main(String[] args)
    {
        GetUglyNumber g=new GetUglyNumber();
        System.out.println(g.GetUglyNumber_Solution(8));
    }
}
