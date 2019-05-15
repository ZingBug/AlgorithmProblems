package Coding_Interview_Guide.String;

/**
 * 去掉字符串种连续出现K个0的字串
 * 给定一个字符串str和一个整数k，如果str种正好有连续的k个'0'字符出现时，把k个连续的'0'字符去除，返回处理后的字符串。
 * str="A00B"，k=2，返回"AB"
 * str="A0000B000"，k=3，返回"A0000B"
 * page245
 *
 * Created by ZingBug on 2019/3/19.
 */
public class RemoveKZeros {

    private String removeKZeros(String str,int k)
    {
        if(str==null||k<1||str.length()<k)
        {
            return str;
        }
        int count=0;
        int start=-1;
        char[] chars=str.toCharArray();
        for(int i=0;i<chars.length;i++)
        {
            if(chars[i]=='0')
            {
                count++;
                start=start==-1?i:start;
            }
            else
            {
                if(count==k)
                {
                    while (count--!=0)
                    {
                        chars[start++]=0;
                    }
                }
                count=0;
                start=-1;
            }
        }
        if(count==k)
        {
            while (count--!=0)
            {
                chars[start++]=0;
            }
        }

        return String.valueOf(chars);
    }

    public static void main(String[] args)
    {
        RemoveKZeros removeKZeros=new RemoveKZeros();
        String str1="A00B";
        String str2="A0000B000";
        System.out.println(removeKZeros.removeKZeros(str2,3));

    }
}
