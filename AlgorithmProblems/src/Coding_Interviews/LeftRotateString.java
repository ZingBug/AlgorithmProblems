package Coding_Interviews;

/**
 * 左旋转字符串
 * 题目描述
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
 * 要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 *
 * https://www.nowcoder.com/practice/12d959b108cb42b1ab72cef4d36af5ec?tpId=13&tqId=11196&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * Created by ZingBug on 2018/7/4.
 */
public class LeftRotateString {
    private String solution1(String str,int n)
    {
        //第一种方法，遍历一遍，逐个放置，时间复杂度为O(n)
        if(str==null||str.length()==0)
        {
            return "";
        }
        char[] chars=str.toCharArray();
        int len=str.length();
        char[] r=new char[len];
        n=n%len;
        for(int i=0;i<len;i++)
        {
            int j=i-n;
            if(j>=0)
            {
                r[j]=chars[i];
            }
            else
            {
                r[j+len]=chars[i];
            }
        }
        return String.valueOf(r);
    }
    private String solution2(String str,int n)
    {
        //第二种方法，用反转来做。先将左移的部分反转，然后将后半部分反转，最后整体再反转。这个时间复杂度稍微低一些。
        if(str==null||str.length()==0)
        {
            return "";
        }
        int len=str.length();
        n=n%len;
        String s1=str.substring(0,n);
        String s2=str.substring(n,len);
        String re=reverseString(s1)+reverseString(s2);
        return reverseString(re);
    }
    private String reverseString(String str)
    {
        //字符串反转，也可以直接用库函数StringBuffer(str).reverse().toString()
        char[] chars=str.toCharArray();
        char[] reverse=new char[chars.length];
        for(int i=0,j=str.length()-1;i<=j;i++,j--)
        {
            reverse[i]=chars[j];
            reverse[j]=chars[i];
        }
        return String.valueOf(reverse);
    }
    public String LeftRotateString(String str,int n)
    {
        return solution2(str,n);
    }
    public static void main(String[] args)
    {
        LeftRotateString l=new LeftRotateString();
        String s1="abcXYZdef";
        String s2="ab";
        System.out.println(l.LeftRotateString(s1,3));

    }
}
