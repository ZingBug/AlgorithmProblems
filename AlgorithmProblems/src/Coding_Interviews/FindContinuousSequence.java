package Coding_Interviews;

import java.util.ArrayList;

/**
 * 和为S的连续正数序列
 *
 * 题目描述
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 *
 * https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe?tpId=13&tqId=11194&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 1、第一种方法思路很简单，不在赘述
 *
 * 2、我们考虑用两个数small和big分别表示序列的最小值和最大值。
 * 首先把small初始化为1，big初始化为2。如果从small到big的序列的和大于sum，我们就从序列中去掉较小的值，也就是增大small的值。
 * 如果从small到big的序列和小于sum，我们可以增大big，让这个序列包含更多的数字。
 * 因为这个序列至少需要两个数字，我们一直增加small到(1+sum)/2为止。
 *
 * 方法与FindNumbersWithSum相似。
 *
 * Created by ZingBug on 2018/7/3.
 */
public class FindContinuousSequence {

    private ArrayList<ArrayList<Integer>> solution1(int sum)
    {
        //第一种方法，最简单粗暴的遍历，时间复杂度为O(n^2)
        ArrayList<ArrayList<Integer>> lists=new ArrayList<>();
        for(int i=1;i<sum;i++)
        {
            int s=0;
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=i;j<sum;j++)
            {
                list.add(j);
                s+=j;
                if(s==sum)
                {
                    lists.add(list);
                    break;
                }
                else if(s>sum)
                {
                    break;
                }
            }
        }
        return lists;
    }
    private ArrayList<ArrayList<Integer>> solution2(int sum)
    {
        //第二个方法，利用剑指offer书上的方法，逐个往前移动。时间复杂度为O(n)
        ArrayList<ArrayList<Integer>> lists=new ArrayList<>();
        int small=1;
        int big=2;
        int middle=(1+sum)/2;
        int curSum=small+big;
        while (small<middle)
        {
            if(curSum==sum)
            {
                ArrayList<Integer> list=new ArrayList<>();
                for(int i=small;i<=big;i++)
                {
                    list.add(i);
                }
                lists.add(list);
                big++;
                curSum+=big;
            }
            else if(curSum<sum)
            {
                big++;
                curSum+=big;
            }
            else
            {
                curSum-=small;
                small++;
            }
        }
        return lists;


    }
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum)
    {
        return solution2(sum);
    }

    public static void main(String[] args)
    {
        FindContinuousSequence f=new FindContinuousSequence();
        ArrayList<ArrayList<Integer>> lists=f.FindContinuousSequence(100);
        for(ArrayList<Integer> list:lists)
        {
            for(int val:list)
            {
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
}
