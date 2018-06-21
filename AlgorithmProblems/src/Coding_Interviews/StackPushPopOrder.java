package Coding_Interviews;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * 题目描述
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 *
 * https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106?tpId=13&tqId=11174&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 建立一个辅助栈
 * 将第一个序列的数字压入辅助栈，如果第二个序列当前位置的数字正好是辅助栈的栈顶数字，那就弹出辅助栈。依次循环。
 * 循环结束后，检查辅助栈内是否为空。为空则代表序列正确。
 *
 * Created by ZingBug on 2018/6/21.
 */
public class StackPushPopOrder {
    public boolean IsPopOrder(int[] pushA,int[] popA)
    {
        int len=pushA.length;
        Stack<Integer> stack=new Stack<>();
        boolean result=false;
        int index1=0;
        int index2=0;
        while (index2<len&&index1<len)
        {
            stack.push(pushA[index1]);
            while (!stack.isEmpty()&&stack.peek()==popA[index2])
            {
                stack.pop();
                index2++;
            }
            index1++;
        }
        if(stack.isEmpty())
        {
            result=true;
        }
        return result;
    }

    public static void main(String[] args)
    {
        int[] pushA={1,2,3,4,5};
        int[] popA={4,5,3,2,1};
        int[] popB={4,3,5,1,2};
        StackPushPopOrder s=new StackPushPopOrder();
        System.out.println(s.IsPopOrder(pushA,popB));
    }
}
