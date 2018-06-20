package Coding_Interviews;

/**
 * 矩形覆盖
 * 题目描述
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * https://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6?tpId=13&tqId=11163&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 类似于青蛙跳台阶,当n=1时，只有一种横向排列的方式。
 * 当n等于二时，2*2有两种选择，横向或者是竖向。
 * 当n等于3的时候对于2*3来说,如果选择的是竖向排列，则剩下的就是2*2排列，如果选择的是横向,则对于2*n剩下的则只有1*n的一种选择。
 * 所以依次类推，找到迭代RectCover(target-1)+RectCover(target-2)。
 */
public class RectCover {
    public int RectCover(int target)
    {
        if(target==1)
        {
            return 1;
        }
        else if(target==2)
        {
            return 2;
        }
        int first=1;
        int second=2;
        int result=0;
        for(int i=0;i<target-2;i++)
        {
            result=first+second;
            first=second;
            second=result;
        }
        return result;
    }
    public static void main(String[] args)
    {
        RectCover r=new RectCover();
        System.out.println(r.RectCover(3));
    }
}
