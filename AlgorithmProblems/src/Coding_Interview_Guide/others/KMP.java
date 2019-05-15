package Coding_Interview_Guide.others;

/**
 * KMP算法
 * 给定两个字符串str和match，长度分别为N和M。实现一个算法，如果字符串str中含有子串match，则返回match在str中的开始位置，不含有则返回-1。
 * Page 491
 * Created by ZingBug on 2018/12/17.
 */
public class KMP {
    //创建next数组
    private int[] getNextArray(char[] ms)
    {
        if(ms.length==1)
        {
            return new int[]{-1};
        }
        int[] next=new int[ms.length];
        next[0]=-1;
        next[1]=0;
        int pos=2;
        int cn=0;
        while (pos<next.length)
        {
            if(ms[pos-1]==ms[cn])
            {
                next[pos++]=++cn;
            }
            else if(cn>0)
            {
                cn=next[cn];
            }
            else
            {
                next[pos++]=0;
            }
        }
        return next;
    }

    private int getIndexOf(String s,String m)
    {
        if(s==null||m==null||m.length()<1||m.length()>s.length())
        {
            return -1;
        }
        char[] sc=s.toCharArray();
        char[] mc=m.toCharArray();
        int[] next=getNextArray(m.toCharArray());
        int si=0;
        int mi=0;
        while (si<sc.length&&mi<mc.length)
        {
            if(sc[si]==mc[mi])
            {
                si++;
                mi++;
            }
            else if(next[mi]==-1)
            {
                si++;
            }
            else
            {
                mi=next[mi];
            }
        }
        return mi==mc.length?si-mi:-1;
    }

    public static void main(String[] args)
    {
        String str="abcabcbacdfsafsda";
        String match="bcbac";

        KMP k=new KMP();
        int[] r=k.getNextArray(match.toCharArray());
        for(int i:r)
        {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println(k.getIndexOf(str,match));
    }
}
