package LeetCode;

import java.util.Stack;

/**
 * 20. Valid Parentheses
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 *
 * https://leetcode.com/problems/valid-parentheses
 *
 * Created by ZingBug on 2018/11/15.
 */
public class ValidParentheses {

    public boolean isValid(String s)
    {
        if(s==null)
        {
            return true;
        }
        int len=s.length();
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<len;i++)
        {
            if(s.charAt(i)=='('||s.charAt(i)=='{'||s.charAt(i)=='[')
            {
                stack.push(s.charAt(i));
            }
            else if(!stack.isEmpty()&&(Math.abs(s.charAt(i)-stack.peek())==1||Math.abs(s.charAt(i)-stack.peek())==2))
            {
                stack.pop();
            }
            else
            {
                return false;
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args)
    {
        ValidParentheses v=new ValidParentheses();
        String s1="()";
        String s2="()[]{}";
        String s3="(]";
        String s4="([)]";
        String s5="{[]}";

        System.out.println(v.isValid(s1));
        System.out.println(v.isValid(s2));
        System.out.println(v.isValid(s3));
        System.out.println(v.isValid(s4));
        System.out.println(v.isValid(s5));

    }
}
