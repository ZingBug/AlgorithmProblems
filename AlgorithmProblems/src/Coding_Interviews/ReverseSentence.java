package Coding_Interviews;

/**
 * 翻转单词顺序列
 * 题目描述
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 *
 * https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13&tqId=11197&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 先整体翻转整个句子，然后逐个再翻转每个单词，拼接在一起即可。
 * Created by ZingBug on 2018/7/5.
 */
public class ReverseSentence {
    public String ReverseSentence(String str)
    {
        if(str==null||str.length()==0)
        {
            return "";
        }

        //先反转整个句子
        String s1=reverseString(str);
        //再逐个反转每个单词
        String[] s2=s1.split(" ");
        if(s2.length==0)
        {
            return s1;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s2.length;i++)
        {
            sb.append(reverseString(s2[i]));
            if(i<s2.length-1)
            {
                sb.append(" ");
            }
        }

        return sb.toString();
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

    public static void main(String[] args)
    {
        ReverseSentence r=new ReverseSentence();
        String s1="I am a student.";
        String s2=" ";
        System.out.println(r.ReverseSentence(s2));
    }
}
