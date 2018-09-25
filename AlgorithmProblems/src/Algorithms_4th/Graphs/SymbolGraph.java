package Algorithms_4th.Graphs;

import Searching.BST;
import edu.princeton.cs.algs4.In;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 符号图
 * 顶点名是字符串，并用指定的分隔符来隔开顶点名
 * 输入为字符串，并在内部构造起图，并实现反向索引
 * by ZingBug 2017/9/26
 */
public class SymbolGraph {
    private BST<String,Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String stream,String sp)
    {
        st=new BST<>();
        try
        {
            File file=new File(stream);
            if(file.exists())
            {
                FileInputStream fis=new FileInputStream(file);
                Scanner scanner=new Scanner(new BufferedInputStream(fis),"UTF-8");//第一次遍历，用于构造索引
                while (scanner.hasNext())
                {
                    String[] a=scanner.nextLine().split(sp);
                    for(int i=0;i<a.length;i++)
                    {
                        if(!st.contains(a[i]))
                        {
                            st.put(a[i],st.size());
                        }
                    }
                }
                keys=new String[st.size()];
                for(String name:st.keys())
                {
                    keys[st.get(name)]=name;
                }
                G=new Graph(st.size());
                fis=new FileInputStream(file);
                scanner=new Scanner(new BufferedInputStream(fis),"UTF-8");//第二次遍历，用于构造图
                while (scanner.hasNext())
                {
                    String[] a=scanner.nextLine().split(sp);
                    int v=st.get(a[0]);
                    for(int i=1;i<a.length;i++)
                    {
                        G.addEdge(v,st.get(a[i]));
                    }
                }
            }
        }
        catch (IOException io)
        {
            throw new IllegalArgumentException("Could not open " + stream, io);
        }
    }
    public boolean contains(String s)
    {
        return st.contains(s);
    }
    public int index(String s)
    {
        return st.get(s);
    }
    public String name(int v)
    {
        if(!(v<keys.length))
            throw new IllegalArgumentException("Could not exit this index");
        return keys[v];
    }

    public Graph G() {
        return G;
    }

    public static void main(String[] args)
    {
        //参照教材P353图4.1.22
        SymbolGraph sg=new SymbolGraph("F:\\GitHub\\Algorithms\\Algorithms\\src\\Graphs\\routes.txt"," ");
        Graph g=sg.G();
        System.out.println("打印各顶点所连接的所有顶点");
        for(int i=0;i<g.V();i++)
        {
            System.out.print(sg.name(i)+":");
            for(int v:g.adj(i))
            {
                System.out.print(" "+sg.name(v));
            }
            System.out.println();
        }
    }
}
