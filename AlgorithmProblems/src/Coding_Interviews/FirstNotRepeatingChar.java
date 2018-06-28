package Coding_Interviews;

/**
 * 第一个只出现一次的字符
 * 题目描述
 * 在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
 *
 * https://www.nowcoder.com/practice/1c82e8cf713b4bbeb2a5b31cf5b0417c?tpId=13&tqId=11187&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 只需要记录每个字符的出现次数，并将出现次数为1次的找到出来即可。时间复杂度为O(n)
 * Created by ZingBug on 2018/6/27.
 */
public class FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str)
    {
        if(str==null)
        {
            return -1;
        }
        char[] chars=str.toCharArray();
        int[] indexs=new int[123];//字母的最后一个ASCII码为122
        for(int i=0;i<123;i++)
        {
            indexs[i]=-1;//初始化为-1
        }
        int i=0;
        for(char c:chars)
        {
            if(indexs[c]!=-1)
            {
                indexs[c]=-2;
            }
            else
            {
                indexs[c]=i;
            }
            i++;
        }
        int result=10001;
        for(i=0;i<123;i++)
        {
            if(indexs[i]!=-1&&indexs[i]!=-2)
            {
                result=Math.min(result,indexs[i]);
            }
        }
        return result>10000?-1:result;
    }

    public static void main(String[] args)
    {
        FirstNotRepeatingChar f=new FirstNotRepeatingChar();
        String str1="avda";
        String str2="arPKABWuHcTtlbowHwwZvIdUplXdhAxnncWjFNVYGXsohzPkdQLPvUjSfvEhpMWgIuKcjT";
        System.out.println(f.FirstNotRepeatingChar(str2));
    }
}
