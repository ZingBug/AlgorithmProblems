package LeetCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 316. Remove Duplicate Letters
 *
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *
 * Input: "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: "cbacdcbc"
 * Output: "acdb"
 *
 * 关键在于：如果当前字符比前面字符小，则当前放入字符后面是否存在之前数组的重复字符，如果存在，则直接把之前数组删除
 * https://leetcode.com/problems/remove-duplicate-letters/
 *
 * Created by ZingBug on 2018/11/13.
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s)
    {
        if(s==null||s.length()<=1)
        {
            return s;
        }
        int[] charCounter=new int[256];
        for(int i=0;i<s.length();i++)
        {
            charCounter[s.charAt(i)]++;
        }

        Stack<Character> stack=new Stack<>();
        Set<Character> set=new HashSet<>();//也可以用一个波尔数组来代替set

        for(int i=0;i<s.length();i++)
        {
            if(!set.contains(s.charAt(i)))
            {
                while (!stack.isEmpty()&&s.charAt(i)<stack.peek()&&charCounter[stack.peek()]>0)
                {
                    set.remove(stack.pop());
                }
                stack.push(s.charAt(i));
                set.add(s.charAt(i));
            }
            charCounter[s.charAt(i)]--;
        }
        StringBuilder sb=new StringBuilder();
        while (!stack.isEmpty())
        {
            sb.insert(0,stack.pop());
        }
        return sb.toString();
    }

    public static void main(String args[])
    {
        String s1="bcabc";
        String s2="cbacdcbc";
        RemoveDuplicateLetters d=new RemoveDuplicateLetters();
        System.out.println(d.removeDuplicateLetters(s1));
        System.out.println(d.removeDuplicateLetters(s2));
    }
}
