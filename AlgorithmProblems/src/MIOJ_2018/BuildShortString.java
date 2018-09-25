package MIOJ_2018;

/**
 * 构建短字符串
 * 描述
 * 给定任意一个较短的子串，和另一个较长的字符串，判断短的字符串是否能够由长字符串中的字符组合出来，且长串中的每个字符只能用一次。
 *
 * 输入
 * 一行数据包括一个较短的字符串和一个较长的字符串，用一个空格分隔，如： ab aab bb abc aa cccc uak areuok
 *
 * 输出
 * 如果短的字符串可以由长字符串中的字符组合出来，返回字符串 “true”，否则返回字符串 "false"，注意返回字符串类型而不是布尔型。
 *
 * 输入样例
 * a b
 * aa ab
 * aa aab
 * uak areuok
 * 输出样例
 * false
 * false
 * true
 * true
 *
 * https://code.mi.com/problem/list/view?id=11
 * Created by ZingBug on 2018/9/8.
 */
public class BuildShortString {
    private static String solution1(String line)
    {
        //简单的遍历法子
        String[] array=line.split(" ");
        boolean flag=true;
        char[] shortStr=array[0].toCharArray();
        char[] longStr=array[1].toCharArray();
        int[] hash=new int[26];
        for(int i=0;i<longStr.length;i++)
        {
            hash[longStr[i]-97]+=1;
        }
        for(int i=0;i<shortStr.length;i++)
        {
            hash[shortStr[i]-97]--;
            if(hash[shortStr[i]-97]<0)
            {
                flag=false;
                break;
            }
        }

        return String.valueOf(flag);
    }

    private static String solution2(String line)
    {
        //素数法
        //定义一个长度为26的数组，数组元素为最小的26个素数，分别是与a－z对应；
        //定义一个乘积变量，遍历长字符串，将每个字符对应的素数相乘，乘积赋给乘积变量
        //遍历短字符串，遍历的每个字符除乘积变量，如果有余数，则说明不匹配，退出遍历，否则相匹配。
        String[] array=line.split(" ");
        boolean flag=true;
        long multi=1;
        char[] shortStr=array[0].toCharArray();
        char[] longStr=array[1].toCharArray();
        int[] prime=new int[26];
        //填充素数

        for(int i=2,count=0;count<26;i++)
        {
            boolean isPrime=true;
            for(int j=2;j<=Math.sqrt(i);j++)
            {
                if(i%j==0)// 若能被整除，则说明不是素数，返回false
                {
                    isPrime=false;
                    break;
                }
            }
            if(isPrime)
            {
                prime[count++]=i;
            }
        }

        for(int i=0;i<longStr.length;i++)
        {
            multi*=(prime[longStr[i]-97]);
        }
        for(int i=0;i<shortStr.length;i++)
        {
            if(multi%(prime[shortStr[i]-97])!=0)
            {
                flag=false;
                break;
            }
            multi/=(prime[shortStr[i]-97]);
        }
        return String.valueOf(flag);
    }

    public static void main(String[] args)
    {
        BuildShortString b=new BuildShortString();
        String line1="a b";
        String line2="aa ab";
        String line3="aa aab";
        String line4="uak areuok";
        String line5="mi zhizhqpoem";

        System.out.println(b.solution2(line1));
        System.out.println(b.solution2(line2));
        System.out.println(b.solution2(line3));
        System.out.println(b.solution2(line4));
        System.out.println(b.solution2(line5));
    }
}
