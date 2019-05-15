package Coding_Interview_Guide.BinaryTree;

/**
 * 判断t1树中是否有t2树拓扑结构完全相同的子树
 * 先将两棵树进行序列化，然后对字符串进行kmp匹配。如果匹配出来则证明存在。
 * Page141
 * Created by ZingBug on 2018/12/17.
 */
public class IsSubTree {
    private boolean isSubTree(Node t1,Node t2)
    {
        String s1=serialByPre(t1);
        String s2=serialByPre(t2);
        return getIndexOf(s1,s2)!=-1;
    }
    //通过先序遍历
    private String serialByPre(Node head)
    {
        if(head==null)
        {
            return "#!";
        }
        String res=head.value+"!";
        res+=serialByPre(head.left);
        res+=serialByPre(head.right);
        return res;
    }

    //kmp匹配
    private int getIndexOf(String s,String m)
    {
        if(s==null||m==null||m.length()<1||s.length()<m.length())
        {
            return -1;
        }
        char[] sc=s.toCharArray();
        char[] mc=m.toCharArray();
        int[] next=getNextArray(mc);
        int si=0;
        int mi=0;
        while (si<s.length()&&mi<m.length())
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

        return mi==m.length()?si-mi:-1;

    }

    private int[] getNextArray(char[] m)
    {
        if(m.length==1)
        {
            return new int[]{-1};
        }
        int[] next=new int[m.length];
        next[0]=-1;
        next[1]=0;
        int pos=2;
        int cn=0;
        while (pos<m.length)
        {
            if(m[pos-1]==m[cn])
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
}
