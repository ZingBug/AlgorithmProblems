package Coding_Interviews;

/**
 * 数组中出现次数超过一半的数字
 * 题目描述
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163?tpId=13&tqId=11181&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 1、通过HashMap方式来做，以数字为key，value是重复的次数，最后找出重复次数最多的那个，判断是否大于length/2
 * 2、类似于“士兵攻阵地”，我们把数组想象为一群士兵，这些士兵来自不同阵营，士兵们一个一个走出军营去攻打阵地，
 * 第一个兵占领阵地以后，后面来的兵可能是自己人，也可能不是自己人，是自己人的话，count+1，不是自己人的话，同归于尽，
 * 最后肯定剩下一个人活到最后，但是这个人并不一定属于人最多的那一个阵营。
 *
 * 比如:'3,3,3,1,2,0'，第一个3先上去，第二个3再上去，第三个3再上去，这时候count=3，后面1上去，3-1=2，2上去，2-1=1，1上去，1-1=0，
 * 这时候留在最后的是0，但是0显然不是人数最多那个阵营的兵，人数最多的那个阵营都被别的阵营消耗掉了。如果出场顺序变为:'3,1,3,3,2,0'，
 * 那最后留下的人就是3，但是3个3并没有> (6/2)。如果3的数量再多一个，那么不论怎么出场，最后剩下的就是3，毕竟人多，与一半的人同归于尽后总会剩下人的，
 * 这时候显然4个3>(7/2)。
 *
 * Created by ZingBug on 2018/6/25.
 */
public class MoreThanHalfNum {
    public int MoreThanHalfNum_Solution(int[] array)
    {
        int len=array.length;
        if(len==0)
        {
            return 0;
        }
        int n=array[0];
        int count=0;
        for(int val:array)
        {
            if(val==n)
            {
                count++;
            }
            else
            {
                count--;
            }
            if(count==0)
            {
                n=val;
                count=1;
            }
        }
        //再确定一下
        count=0;
        for(int val:array)
        {
            if(n==val)
            {
                count++;
            }
        }

        return count*2>len?n:0;
    }
    public static void main(String[] args)
    {
        MoreThanHalfNum m=new MoreThanHalfNum();
        int[] array={1,2,3,2,2,2,5,4,2};
        System.out.println(m.MoreThanHalfNum_Solution(array));
    }
}
