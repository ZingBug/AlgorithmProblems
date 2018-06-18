package Coding_Interviews;

import java.util.Stack;

/**
 * 用两个栈实现队列
 * 题目描述
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=13&tqId=11158&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class CreatQueueWithTwoStacks {
    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();

    public void push(int node)
    {
        stack1.push(node);
    }
    public int pop()
    {
        if(stack1.isEmpty())
        {
            throw new NullPointerException("no data");
        }
        while (stack1.size()>1)
        {
            stack2.push(stack1.pop());
        }
        int result=stack1.pop();
        while (!stack2.isEmpty())
        {
            stack1.push(stack2.pop());
        }
        return result;
    }

    public static void main(String[] args)
    {
        CreatQueueWithTwoStacks queue=new CreatQueueWithTwoStacks();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        for(int i=0;i<3;i++)
        {
            System.out.print(queue.pop()+" ");
        }
    }

}
