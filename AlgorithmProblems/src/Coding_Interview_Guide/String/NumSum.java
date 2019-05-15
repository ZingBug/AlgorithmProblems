package Coding_Interview_Guide.String;

/**
 * 字符串中数字子串的求和
 * 给定一个字符串str，求其中全部数字串所代表的数字之和
 * str="A1CD2E33"，返回36
 * str="A-1B--2C--D6E"，返回7
 * page243
 *
 * Created by ZingBug on 2019/3/19.
 */

public class NumSum {
    private int numSum(String str)
    {
        if(str==null)
        {
            return 0;
        }
        char[] charArr=str.toCharArray();
        int res=0;
        int num=0;
        boolean posi=true;
        int cur=0;
        for(int i=0;i<charArr.length;i++)
        {
            cur=charArr[i]-'0';
            if(cur<0||cur>9)
            {
                res+=num;
                num=0;
                if(charArr[i]=='-')
                {
                    if(i>0&&charArr[i-1]=='-')
                    {
                        posi=!posi;
                    }
                    else
                    {
                        posi=false;
                    }
                }
                else
                {
                    posi=true;
                }
            }
            else
            {
                num=num*10+(posi?cur:-cur);
            }
        }
        res+=num;
        return res;
    }

    public static void main(String[] args)
    {
        NumSum numSum=new NumSum();
        String str1="-2A1CD2E33";
        String str2="A-1B--2C--D6E";
        System.out.println(numSum.numSum(str2));
    }
}
