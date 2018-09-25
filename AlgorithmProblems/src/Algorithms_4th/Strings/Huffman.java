package Algorithms_4th.Strings;

import Algorithms_4th.PriorityQueues.MinPQ;
import Algorithms_4th.Base.BinaryStdIn;
import Algorithms_4th.Base.BinaryStdOut;

/**
 * 霍夫曼压缩
 * 实现很混乱，明白思路即可，不容易实现。
 * Created by ZingBug on 2017/10/26.
 */
public class Huffman {
    private static int R=256;//ASCII字母表
    private static BinaryStdOut out=new BinaryStdOut("F:\\GitHub\\Algorithms\\Algorithms\\src\\Strings\\HuffmanOut.txt");
    //霍夫曼单词查找树中的结点
    private static class Node implements Comparable<Node>
    {
        private char ch;//内部结点不会使用该变量
        private int freq;//展开过程不会使用该变量
        private final Node left,right;

        Node(char ch,int freq,Node left,Node right)
        {
            this.ch=ch;
            this.freq=freq;
            this.left=left;
            this.right=right;
        }
        public boolean isLeaf()
        {
            return left==null&&right==null;
        }
        public int compareTo(Node that)
        {
            return this.freq-that.freq;
        }
    }
    //读取输入
    public static void compress()
    {
        String s= BinaryStdIn.readString();

        char[] input=s.toCharArray();
        //统计频率
        int[] freq=new int[R];
        for(int i=0;i<input.length;i++)
        {
            freq[input[i]]++;
        }
        //构造霍夫曼编码树
        Node root=buildTrie(freq);
        //递归地构造编码表
        String[] st=new String[R];
        buildCode(st,root,"");

        //递归的打印解码用的单词查找树
        writeTrie(root);

        //打印字符总数
        out.write(input.length);

        //使用霍夫曼编码处理输入
        for(int i=0;i<input.length;i++)
        {
            String code=st[input[i]];
            for(int j=0;j<code.length();j++)
            {
                if(code.charAt(j)=='1')
                {
                    out.write(true);
                }
                else
                {
                    out.write(false);
                }
            }
        }
        out.close();
    }
    //解码
    public static void expand()
    {
        Node root=readTrie();
        int N=BinaryStdIn.readInt();
        for(int i=0;i<N;i++)
        {
            //展开第i个编码所对应的字母
            Node x=root;
            while (!x.isLeaf())
            {
                if(BinaryStdIn.readBoolean())
                {
                    x=x.right;
                }
                else
                {
                    x=x.left;
                }
            }
            out.write(x.ch);
        }
        out.close();
    }
    //构造霍夫曼编码树
    private static Node buildTrie(int[] freq)
    {
        //使用多颗单结点树初始化优先队列
        MinPQ<Node> pq=new MinPQ<>();
        for(char c=0;c<R;c++)
        {
            if(freq[c]>0)
            {
                pq.insert(new Node(c,freq[c],null,null));
            }
        }
        while (pq.size()>1)
        {
            //合并两颗频率最小的树
            Node x=pq.delMin();
            Node y=pq.delMin();
            Node parent=new Node('\0',x.freq+y.freq,x,y);
            pq.insert(parent);
        }
        return pq.delMin();
    }
    //使用单词查找树构造编译表
    private static String[] buildCode(Node root)
    {
        String[] st=new String[R];
        buildCode(st,root,"");
        return st;
    }
    //使用单词查找树构造编译表（递归）
    private static void buildCode(String[] st,Node x,String s)
    {
        if(x.isLeaf())
        {
            st[x.ch]=s;
            return;
        }
        buildCode(st,x.left,s+'0');
        buildCode(st,x.right,s+'1');
    }
    //将单词查找树写为比特字符串
    private static void writeTrie(Node x)
    {
        if(x.isLeaf())
        {
            out.write(true);
            out.write(x.ch);
            return;
        }
        out.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }
    //从比特流的前序表示中重建单词查找树
    private static Node readTrie()
    {
        if(BinaryStdIn.readBoolean())
        {
            return new Node(BinaryStdIn.readChar(),0,null,null);
        }
        return new Node('\0',0,readTrie(),readTrie());
    }

    public static void main(String[] args)
    {
        compress();
    }
}
