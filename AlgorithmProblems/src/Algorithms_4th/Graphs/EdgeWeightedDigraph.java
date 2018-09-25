package Algorithms_4th.Graphs;

import Algorithms_4th.Base.In;

/**
 * 加权有向图
 * Created by ZingBug on 2017/10/9.
 */
public class EdgeWeightedDigraph {
    private final int V;//图的顶点个数
    private int E;//图的边数
    private Bag<DirectedEdge>[] adj;//邻接表

    public EdgeWeightedDigraph(int V)
    {
        this.V=V;
        this.E=0;
        adj=(Bag<DirectedEdge>[]) new Bag[V];
        for(int v=0;v<V;v++)
        {
            adj[v]=new Bag<>();
        }
    }
    public EdgeWeightedDigraph(In in)
    {
        this(in.readInt());//先构造函数
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
            addEdge(new DirectedEdge(v,w,weight));
        }
    }
    public int V()
    {
        return V;
    }
    public int E()
    {
        return E;
    }
    public void addEdge(DirectedEdge e)
    {
        int v=e.from();
        int w=e.to();
        validateVertex(v);
        validateVertex(w);
        adj[e.from()].add(e);
        E++;
    }
    public Iterable<DirectedEdge> adj(int v)
    {
        //返回某一顶点的所有边
        return adj[v];
    }
    public Iterable<DirectedEdge> edges()
    {
        //返回图中所有边
        Bag<DirectedEdge> bag=new Bag<>();
        for(int v=0;v<V;v++)
        {
            for(DirectedEdge e:adj[v])
            {
                bag.add(e);
            }
        }
        return bag;
    }
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
