package Algorithms_4th.Graphs;

import Algorithms_4th.Base.In;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 加权无向图的数据类型
 * 只是在邻接表中用Edge对象代替了Graph中的整数来作为链接表的节点
 * by ZingBug 2017/9/29
 */
public class EdgeWeightedGraph {
    private final int V;//顶点数目
    private int E;//边的总数
    private Bag<Edge>[] adj;//邻接表

    public EdgeWeightedGraph(int V)
    {
        this.V=V;
        this.E=0;
        adj=(Bag<Edge>[]) new Bag[V];
        for(int v=0;v<V;v++)
        {
            adj[v]=new Bag<>();
        }
    }
    public EdgeWeightedGraph(In in)
    {
        this(in.readInt());
        int E=in.readInt();
        if(E<0)
        {
            throw new IllegalArgumentException("Number of edges must be nonegative");
        }
        for(int i=0;i<E;i++)
        {
            int v=in.readInt();
            int w=in.readInt();
            validateVertex(v);
            validateVertex(w);
            double weight=in.readDouble();
            addEdge(new Edge(v,w,weight));
        }
    }
    public int V()
    {
        return this.V;
    }
    public int E()
    {
        return this.E;
    }
    public void addEdge(Edge e)
    {
        int v=e.either();
        int w=e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v)
    {
        //返回某一顶点的所有边
        return adj[v];
    }
    public Iterable<Edge> edges()
    {
        //返回加权无向图中的所有边
        Bag<Edge> b=new Bag<>();
        for(int v=0;v<V;v++)
        {
            for(Edge e:adj[v])
            {
                b.add(e);
            }
        }
        return b;
    }
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

}
