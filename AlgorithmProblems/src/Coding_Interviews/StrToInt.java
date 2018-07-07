package Coding_Interviews;

/**
 * 把字符串转换成整数
 * 题目描述
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 *
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 *
 * 输出描述:
 * 如果是合法的数值表达则返回该数字，否则返回0
 *
 * 示例1
 * 输入
 * +2147483647
 * 输出
 * 2147483647
 *
 * 实例2
 * 输入
 * 1a33
 * 输出
 * 0
 *
 * https://www.nowcoder.com/practice/1277c681251b4372bdef344468e4f26e?tpId=13&tqId=11202&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * Created by ZingBug on 2018/7/6.
 */
public class StrToInt {
    public int StrToInt(String str)
    {
        if(str==null||str.length()==0)
        {
            return 0;
        }
        char[] chars=str.toCharArray();
        //先判断数正负
        boolean sign=true;//默认为正数
        int i=0;
        if(chars[0]=='+')
        {
            sign=true;
            i++;
        }
        else if(chars[0]=='-')
        {
            sign=false;
            i++;
        }
        int num=0;
        while (i<str.length())
        {
            if(chars[i]>='0'&&chars[i]<='9')
            {
                num=num*10+chars[i]-'0';
            }
            else
            {
                return 0;
            }
            i++;
        }
        return sign?num:-num;
    }

    public static void main(String[] args)
    {
        StrToInt s=new StrToInt();
        String s1="-2147483647";
        String s2="1a33";
        System.out.println(s.StrToInt(s1));
    }
}
