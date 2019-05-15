package Coding_Interview_Guide.String;

/**
 * 字符串的统计字符串
 * 给定一个字符串str，返回str的统计字符串。例如，"aaabbadddffc"的统计字符串为"a_3_b_2_a_1_d_3_f_2_c_1"
 *
 * 补充问题
 * 给定一个字符串的统计字符串cstr，再给定一个整数index，返回cstr所代表的原始字符串的第index个字符。
 * 例如，"a_1_b_100"所代表的原始字符串上第0个字符是'a'，第50个字符是'b'
 * page253
 *
 * Created by ZingBug on 2019/3/23.
 */
public class GetCountString {

    private String getCountString(String str)
    {
        if(str==null||str.equals(""))
        {
            return str;
        }
        char[] chas=str.toCharArray();
        String res=String.valueOf(chas[0]);
        int num=1;
        for(int i=1;i<chas.length;i++)
        {
            if(chas[i]==chas[i-1])
            {
                num++;
            }
            else
            {
                res=res+"_"+String.valueOf(num);
                res=res+"_"+String.valueOf(chas[i]);
                num=1;
            }
        }
        res=res+"_"+String.valueOf(num);
        return res;
    }

    private char getCharAt(String cstr,int index)
    {
        if(cstr==null||cstr.equals(""))
        {
            return 0;
        }
        char[] chas=cstr.toCharArray();
        char cur=0;
        boolean stage=true;
        int sum=0;
        int num=0;
        for(int i=0;i!=chas.length;i++)
        {
            if(chas[i]=='_')
            {
                stage=!stage;
            }
            else if(stage)
            {
                sum+=num;
                if(sum>index)
                {
                    return cur;
                }
                num=0;
                cur=chas[i];
            }
            else
            {
                num=num*10+chas[i]-'0';
            }
        }
        return sum+num>index?cur:0;
    }

    public static void main(String[] args)
    {
        GetCountString getCountString=new GetCountString();
        String str="aaabbadddffc";
        String cstr=getCountString.getCountString(str);
        System.out.println(cstr);
        System.out.println(getCountString.getCharAt(cstr,0));
    }
}
