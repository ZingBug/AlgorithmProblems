package Tencent_Summer_Intern_2017;

import java.util.Scanner;

/**
 * 2、字符移位
 * 小Q最近遇到了一个难题：把一个字符串的大写字母放到字符串的后面，各个字符的相对位置不变，且不能申请额外的空间。
 * 你能帮帮小Q吗？
 *
 * 输入描述:
 * 输入数据有多组，每组包含一个字符串s，且保证:1<=s.length<=1000.
 *
 * 输出描述:
 * 对于每组数据，输出移位后的字符串。
 *
 * 输入例子1:
 * AkleBiCeilD
 *
 * 输出例子1:
 * kleieilABCD
 */
public class CharacterShift {
    //AkleBiCeilD
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            String str=in.next();
            int num=str.length();
            char[] cs=str.toCharArray();
            for(int i=num-1;i>=0;i--)
            {
                if(cs[i]>=65&&cs[i]<=90)
                {
                    //为大写字母
                    int j=i;
                    while ((j+1<num)&&(cs[j+1]>=97)&&(cs[j+1]<=122))
                    {
                        char temp=cs[j];
                        cs[j]=cs[j+1];
                        cs[j+1]=temp;
                        j++;
                    }
                }
            }
            System.out.println(String.copyValueOf(cs));
        }
    }
}
