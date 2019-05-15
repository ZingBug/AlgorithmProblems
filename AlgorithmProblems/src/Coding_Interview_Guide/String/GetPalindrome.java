package Coding_Interview_Guide.String;

/**
 * 添加最小字符使字符串整体都是回文字符串
 * 给定一个字符串str，如果可以在str的任意位置添加字符，请返回在添加字符最小的情况下，让str整体都是回文字符串的一种结果。
 *
 * Page269
 * Created by ZingBug on 2019/5/7.
 */
public class GetPalindrome {

    //如果str的长度为N，设计动态规划表为N*N的矩阵，记为dp[][]。
    //dp[i][j]的值代表子串str[i..j]最少添加几个字符可以使str[i..j]整体都是回文串
    private int[][] getDP(char[] str)
    {
        int[][] dp=new int[str.length][str.length];
        for(int j=1;j<str.length;j++)
        {
            dp[j-1][j]=str[j-1]==str[j]?0:1;
            for(int i=j-2;i>-1;i--)
            {
                if(str[i]==str[j])
                {
                    dp[i][j]=dp[i+1][j-1];
                }
                else
                {
                    dp[i][j]=Math.min(dp[i+1][j],dp[i][j-1])+1;
                }
            }
        }
        return dp;
    }

    private String getPalindromel(String str)
    {
        if(str==null||str.length()<2)
        {
            return str;
        }
        char[] chas=str.toCharArray();
        int[][] dp =getDP(chas);
        char[] res=new char[chas.length+dp[0][chas.length-1]];
        int i=0;
        int j=chas.length-1;
        int resl=0;
        int resr=res.length-1;
        while (i<=j)
        {
            if(chas[i]==chas[j])
            {
                res[resl++]=chas[i++];
                res[resr--]=chas[j--];
            }
            else if(dp[i][j-1]<dp[i+1][j])
            {
                res[resl++]=chas[j];
                res[resr--]=chas[j--];
            }
            else
            {
                res[resl++]=chas[i];
                res[resr--]=chas[i++];
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args)
    {
        String str1="ABABC";
        String str2="ab";
        GetPalindrome getPalindrome=new GetPalindrome();
        System.out.println(getPalindrome.getPalindromel(str1));
        System.out.println(getPalindrome.getPalindromel(str2));
    }
}
