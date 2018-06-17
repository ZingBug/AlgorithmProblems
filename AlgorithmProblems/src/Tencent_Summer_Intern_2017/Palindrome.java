package Tencent_Summer_Intern_2017;

import java.util.Scanner;

/**
 * 1、构造回文
 * 给定一个字符串s，你可以从中删除一些字符，使得剩下的串是一个回文串。如何删除才能使得回文串最长呢？
 * 输出需要删除的字符个数。
 *
 * 输入描述:
 * 输入数据有多组，每组包含一个字符串s，且保证:1<=s.length<=1000.
 *
 * 输出描述:
 * 对于每组数据，输出一个整数，代表最少需要删除的字符个数。
 *
 * 输入例子1:
 * abcda
 * google
 *
 * 输出例子1:
 * 2
 * 2
 */
public class Palindrome {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            String str=in.next();
            System.out.println(str.length()-getPalindrome(str));

        }
    }
    public static int getPalindrome(String str)//用到动态规划的状态转移方程
    {
        String anti_str=new StringBuffer(str).reverse().toString();
        char[] c1=str.toCharArray();
        char[] c2=anti_str.toCharArray();
        int n=str.length();
        int[][] result=new int[n+1][n+1];
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(c1[i-1]==c2[j-1])
                {
                    result[i][j]=result[i-1][j-1]+1;
                }
                else
                {
                    result[i][j]=Math.max(result[i-1][j],result[i][j-1]);
                }
            }
        }

        return result[n][n];
    }
}
