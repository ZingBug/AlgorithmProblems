package Coding_Interviews;

/**
 * 跳台阶
 * 题目描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=13&tqId=11161&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解体思路：
 * 假设，一级台阶，有f(1)种方法，二级有f(2)种，以此类推，n级有f(n)种方法。
 * 可以看出，f(1)=1;f(2)=2。
 * 那么，假设n级台阶，那么第一步就有两种情况，跳一步，跟跳两步。
 * 情况一：跳一步，那么接下去的就是f(n-1)；
 * 情况二：跳两步，那么接下去的就是f(n-2)。
 * 所以总数是f(n)=f(n-1)+f(n-2)。
 */
public class JumpFloor {
    public int JumpFloor(int target)
    {
        if(target==1)
        {
            return 1;
        }
        else if(target==2)
        {
            return 2;
        }
        else
        {
            return JumpFloor(target-1)+JumpFloor(target-2);
        }
    }
    public static void main(String[] args)
    {
        JumpFloor j=new JumpFloor();
        System.out.println(j.JumpFloor(3));
    }
}
