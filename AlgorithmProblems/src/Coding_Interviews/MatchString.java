package Coding_Interviews;

/**
 * 正则表达式匹配
 * 题目描述
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 *
 * https://www.nowcoder.com/practice/45327ae22b7b413ea21df13ee7d6429c?tpId=13&tqId=11205&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * Created by ZingBug on 2018/7/7.
 */
public class MatchString {
    public boolean match(char[] str,char[] pattern)
    {
        if(str==null||pattern==null)
        {
            return false;
        }
        return matchCore(str,0,pattern,0);
    }

    private boolean matchCore(char[] str,int s,char[] pattern,int p)
    {
        if(str.length<=s&&pattern.length<=p)
        {
            //都匹配完了
            return true;
        }
        if(str.length>s&&pattern.length<=p)
        {
            //模式完了，但字符串还有
            return false;
        }
        if((p+1<pattern.length)&&pattern[p+1]=='*')
        {
            if(str.length<=s)
            {
                //字符串完了，但模式没完
                return matchCore(str,s,pattern,p+2);//主要看看后面是否都能消失
            }
            else
            {
                if(str[s]==pattern[p]||(pattern[p]=='.'))
                {
                    //当前位置匹配玩，移动到下一个位置
                    return matchCore(str,s+1,pattern,p+2)
                            ||matchCore(str,s+1,pattern,p)
                            ||matchCore(str,s,pattern,p+2);
                }
                else
                {
                    return matchCore(str,s,pattern,p+2);
                }
            }

        }
        if(str.length<=s)
        {
            //字符串完了，但后面还没有*，那就只能是不能匹配了
            return false;
        }
        else
        {
            if(str[s]==pattern[p]||pattern[p]=='.')
            {
                return matchCore(str,s+1,pattern,p+1);
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        MatchString m=new MatchString();
        String s1="aaa";
        String p1="a.a";
        String p2="ab*ac*a";
        String p3="aa.a";
        String p4="ab*a";
        char[] str=s1.toCharArray();
        char[] pattern=p4.toCharArray();
        System.out.println(m.match(str,pattern));
    }
}
