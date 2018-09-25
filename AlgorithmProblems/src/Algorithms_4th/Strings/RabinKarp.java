package Algorithms_4th.Strings;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin-Karp指纹字符串查找算法
 * Created by ZingBug on 2017/10/25.
 */
public class RabinKarp {
    private String pat;//模式字符串
    private long pathHash;//模式字符串的散列值
    private int M;//模式字符串的长度
    private long Q;//一个很大的素数
    private int R=256;//字母表的大小
    private long RM;//R^(M-1)%Q

    public RabinKarp(String pat)
    {
        this.pat=pat;
        this.M=pat.length();
        Q=longRandomPrime();//生成一个随机素数
        RM=1;
        for(int i=1;i<=M-1;i++)
        {
            RM=(R*RM)%Q;
        }
        pathHash=hash(pat,M);
    }
    //计算key[0..M-1]的散列值
    private long hash(String key,int M)
    {
        long h=0;
        for(int j=0;j<M;j++)
        {
            h=(R*h+key.charAt(j))%Q;
        }
        return h;
    }
    //验证匹配正确性
    private boolean check(String txt,int i)
    {
        for(int j=0;j<M;j++)
        {
            if(pat.charAt(j)!=txt.charAt(i+j))
            {
                return false;
            }
        }
        return true;
    }
    //在文本中查找相等的散列值
    private int search(String txt)
    {
        int N=txt.length();
        long txtHash=hash(txt,M);
        if(pathHash==txtHash&&check(txt,0))
        {
            return 0;//一开始就匹配成功
        }
        for(int i=M;i<N;i++)
        {
            //减去第一个数字，加上最后一个数字，再次检查匹配
            txtHash=(txtHash+Q-RM*txt.charAt(i-M)%Q)%Q;//不明白
            txtHash=(txtHash*R+txt.charAt(i))%Q;
            if(pathHash==txtHash)
            {
                if(check(txt,i-M+1))
                {
                    return i-M+1;//找到匹配
                }
            }
        }
        return N;//未找到匹配
    }
    private long longRandomPrime()
    {
        BigInteger prime=BigInteger.probablePrime(31,new Random());
        return prime.longValue();
    }
    public static void main(String[] args)
    {
        String pat="ADF";
        String txt="ADAFDADFEWE";
        RabinKarp rabinKarp=new RabinKarp(pat);
        System.out.println("text:   "+txt);
        int offset=rabinKarp.search(txt);
        System.out.print("pattern:");
        for(int i=0;i<offset;i++)
        {
            System.out.print(" ");
        }
        System.out.println(pat);
    }

}
