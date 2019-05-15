package Coding_Interview_Guide.String;

/**
 * 替换字符串中连续出现的指定字符串
 * 给定三个字符串str、from和to，已知from字符串中无重复字符，把str中所有from的子串全部替换成to字符串，对连续出现from的部分要求只替换成一个to字符串，返回最终的结果字符串。
 * page251
 *
 * Created by ZingBug on 2019/3/20.
 */
public class Replace {

    private String replace(String str,String from,String to)
    {
        if(str==null||from==null||to==null||str.equals("")||from.equals(""))
        {
            return str;
        }
        char[] chas=str.toCharArray();
        char[] chaf=from.toCharArray();
        int match=0;
        for(int i=0;i<chas.length;i++)
        {
            if(chas[i]==chaf[match++])
            {
                if(match==chaf.length)
                {
                    clear(chas,i,match);
                    match=0;
                }
            }
            else
            {
                if(chas[i]==chaf[match])
                {
                    i--;//退回上一步
                }
                match=0;
            }
        }
        String res="";
        String cur="";
        for(int i=0;i<chas.length;i++)
        {
            if(chas[i]!=0)
            {
                cur=cur+String.valueOf(chas[i]);
            }
            if(chas[i]==0&&(i==0||chas[i-1]!=0))
            {
                res=res+cur+to;
                cur="";
            }
        }
        return res+cur;
    }

    private void clear(char[] chas,int end,int len)
    {
        while (len--!=0)
        {
            chas[end--]=0;
        }
    }

    public static void main(String args[])
    {
        Replace replace=new Replace();
        String str1="123abc";
        String from1="abc";
        String to1="4567";
        String str2="123";
        String from2="abc";
        String to2="456";
        String str3="123abcabc";
        String from3="abc";
        String to3="X";
        System.out.println(replace.replace(str1,from1,to1));
        System.out.println(replace.replace(str2,from2,to2));
        System.out.println(replace.replace(str3,from3,to3));

    }
}
