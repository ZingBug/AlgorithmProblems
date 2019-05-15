package LeetCode;
import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 *
 * Implement the following operations of a queue using stacks.
 *
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Example:
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // returns 1
 * queue.pop();   // returns 1
 * queue.empty(); // returns false
 *
 * 第一种思路用到两个栈，在每次入栈的时候，都借助第二个栈来排好序。
 * 第二种思路也用到两个栈，只是在每次出栈的时候，都会将第一个栈的数据转移到第二个栈中，第二个栈的顺序是正确的。
 *
 * https://leetcode.com/problems/implement-queue-using-stacks
 * Created by ZingBug on 2018/11/16.
 */
public class QueueByStacks {
    private static class MyQueue1 {

        Stack<Integer> stack1;
        Stack<Integer> stack2;
        /** Initialize your data structure here. */
        public MyQueue1() {
            stack1=new Stack<>();
            stack2=new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            while (!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
            stack1.push(x);
            while (!stack2.isEmpty())
            {
                stack1.push(stack2.pop());
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return stack1.pop();
        }

        /** Get the front element. */
        public int peek() {
            return stack1.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty();
        }
    }

    private static class MyQueue2{
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        /** Initialize your data structure here. */
        public MyQueue2() {
            stack1=new Stack<>();
            stack2=new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(stack2.isEmpty())
            {
                if(stack1.isEmpty())
                {
                    throw new NullPointerException("no data");
                }
                else
                {
                    while (!stack1.isEmpty())
                    {
                        stack2.push(stack1.pop());
                    }
                    return stack2.pop();
                }
            }
            else
            {
                return stack2.pop();
            }
        }

        /** Get the front element. */
        public int peek() {
            if(stack2.isEmpty())
            {
                if(stack1.isEmpty())
                {
                    throw new NullPointerException("no data");
                }
                else
                {
                    while (!stack1.isEmpty())
                    {
                        stack2.push(stack1.pop());
                    }
                    return stack2.peek();
                }
            }
            else
            {
                return stack2.peek();
            }
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.empty()&&stack2.empty();
        }
    }

    public static void main(String[] args)
    {
        MyQueue2 queue=new MyQueue2();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.empty());
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }

}
