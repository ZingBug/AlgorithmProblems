package LeetCode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * Implement the following operations of a stack using queues.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Example:
 *
 * MyStack stack = new MyStack();
 *
 * stack.push(1);
 * stack.push(2);
 * stack.top();   // returns 2
 * stack.pop();   // returns 2
 * stack.empty(); // returns false
 *
 * 第一种方法是建立两个队列，然后交替存储数据
 * 第二种方法是在每次push数据时，都将数据调换一下位置，从头到尾换一下。
 *
 * https://leetcode.com/problems/implement-stack-using-queues
 * Created by ZingBug on 2018/11/13.
 */
public class StackByQueues {
    private static class MyStack1
    {
        Queue<Integer> queue1;
        Queue<Integer> queue2;
        /** Initialize your data structure here. */
        public MyStack1() {
            queue1=new LinkedList<>();
            queue2=new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            if(!queue1.isEmpty())
            {
                queue1.offer(x);
            }
            else
            {
                queue2.offer(x);
            }

        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if(queue1.isEmpty())
            {
                while (queue2.size()>1)
                {
                    queue1.offer(queue2.poll());
                }
                return queue2.poll();
            }
            else
            {
                while (queue1.size()>1)
                {
                    queue2.offer(queue1.poll());
                }
                return queue1.poll();
            }
        }

        /** Get the top element. */
        public int top() {
            int tmp;
            if(queue1.isEmpty())
            {
                while (queue2.size()>1)
                {
                    queue1.offer(queue2.poll());
                }
                tmp=queue2.peek();
                queue1.offer(queue2.poll());
            }
            else
            {
                while (queue1.size()>1)
                {
                    queue2.offer(queue1.poll());
                }
                tmp=queue1.peek();
                queue2.offer(queue1.poll());
            }
            return tmp;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty()&&queue2.isEmpty();
        }
    }

    private static class MyStack2
    {
        Queue<Integer> queue;
        /** Initialize your data structure here. */
        public MyStack2() {
            queue=new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            int size=queue.size();
            queue.offer(x);
            for(int i=0;i<size;i++)
            {
                queue.offer(queue.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args)
    {
        MyStack2 stack=new MyStack2();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
    }
}
