package Coding_Interview_Guide.StackAndQueue;

import java.util.Stack;

/**
 * 用一个栈实现另一个栈的排序
 * 一个栈中元素的类型为整形，现在想将该栈从顶到底按照从大到小的顺序排序，只许申请一个栈。除此之外，可以申请新的变量，但不能申请额外的数据结构。
 * <p>
 * 解题思路
 * 将要排序的栈记为stack，申请的辅助栈为help。在stack上执行pop操作，弹出的元素记为cur
 * 如果cur小于或等于help的栈顶元素，则将cur直接压入help；
 * 如果cur大于help的栈顶元素，则将help的元素逐一弹出，逐一压入stack栈中，知道cur小于或等于help的栈顶元素，再将cur压入help。
 * <p>
 * Created by ZingBug on 2018/10/8.
 */
public class SortStackByStack {
    private void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        SortStackByStack s = new SortStackByStack();

        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(1);
        stack.push(3);

        s.sortStackByStack(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}
