package Coding_Interviews;

import java.util.Stack;

/**
 * 包含min函数的栈
 * 题目描述
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 *
 * https://www.nowcoder.com/practice/4c776177d2c04c2494f2555c9fcc1e49?tpId=13&tqId=11173&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 添加辅助栈，辅助栈当前位置为目前数据栈中的最小值。
 *
 * Created by ZingBug on 2018/6/21.
 */
public class MinStack {
    //数据栈
    private Stack<Integer> dataStack=new Stack<>();
    //最小栈
    private Stack<Integer> minStack=new Stack<>();

    public void push(int node)
    {
        dataStack.push(node);
        if(minStack.isEmpty())
        {
            minStack.push(node);
        }
        else
        {
            int temp=minStack.peek();
            minStack.push(Math.min(temp,node));
        }
    }

    public void pop()
    {
        dataStack.pop();
        minStack.pop();
    }

    public int top()
    {
        return dataStack.peek();
    }

    public int min()
    {
        return minStack.peek();
    }

    public static void main(String[] args)
    {
        MinStack stack=new MinStack();
        stack.push(2);
        stack.push(3);
        stack.push(1);
        stack.push(6);
        System.out.println(stack.top()+"-"+stack.min());
        stack.pop();
        System.out.println(stack.top()+"-"+stack.min());
        stack.pop();
        System.out.println(stack.top()+"-"+stack.min());
        stack.pop();
        System.out.println(stack.top()+"-"+stack.min());
    }
}
