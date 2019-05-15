package Coding_Interview_Guide.String;

/**
 * 翻转字符串
 * 给定一个字符类型的数组chas，请在单词间做逆序调整。只要做到单词顺序逆序即可，对空格的位置没有特别要求。
 *
 * 如果把chas看作字符串为"dog loves pig"，调整为"pig loves dog"
 * 如果把chas看作字符串为"I'm a student"，调整为"student a I'm"
 * Page262
 * Created by ZingBug on 2019/4/1.
 */
public class RotateWord {
    private void rotateWord(char[] chas)
    {
        if(chas==null||chas.length==0)
        {
            return;
        }
        //先整体反转
        reverse(chas,0,chas.length-1);
        int start=-1;
        int end=-1;
        //再逐个单词反转
        for(int i=0;i<chas.length;i++)
        {
            if(chas[i]!=' ')
            {
                start=i==0||chas[i-1]==' '?i:start;
                end=i==chas.length-1||chas[i+1]==' '?i:end;
            }
            if(start!=-1&&end!=-1)
            {
                reverse(chas,start,end);
                start=-1;
                end=-1;
            }
        }
    }

    private void reverse(char[] chas,int start,int end)
    {
        char temp;
        while (start<=end)
        {
            temp=chas[start];
            chas[start]=chas[end];
            chas[end]=temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args)
    {
        RotateWord rotateWord=new RotateWord();
        String str1="dog loves pig";
        char[] chas1=str1.toCharArray();
        String str2="I'm a student";
        char[] chas2=str2.toCharArray();
        char[] chas={'a','b','c'};
        rotateWord.reverse(chas,0,chas.length-1);
        for(char c:chas)
        {
            System.out.print(c+" ");
        }
        System.out.println();

        rotateWord.rotateWord(chas2);
        System.out.print(String.valueOf(chas2));
    }
}
