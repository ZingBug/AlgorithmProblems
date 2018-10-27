package Algorithms_4th.Graphs;

import Algorithms_4th.PriorityQueues.IndexMinPQ;
import Algorithms_4th.Base.In;

/**
 * 最短路径的Dijkstra算法
 * Created by ZingBug on 2017/10/10.
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;//edgeTo[v]的值为树中连接v和它的父节点的边
    private double[] distTo;//distTo[v]表示从起点s到顶点v已知最短路径的权重
    private IndexMinPQ<Double> pq;//优先队列，以保存需要被放松的顶点并确认下一个被放松的顶点

    public DijkstraSP(EdgeWeightedDigraph G,int s)
    {
        edgeTo=new DirectedEdge[G.V()];
        distTo=new double[G.V()];
        pq=new IndexMinPQ<>(G.V());
        for(int v=0;v<G.V();v++)
        {
            distTo[v]=Double.POSITIVE_INFINITY;
        }
        distTo[0]=0.0;
        pq.insert(s,0.0);
        while (!pq.isEmpty())
        {
            relax(G,pq.delMin());
        }
    }
    private void relax(EdgeWeightedDigraph G,int v)
    {
        for(DirectedEdge e:G.adj(v))
        {
            int w=e.to();
            if(distTo[w]>distTo[v]+e.weight())
            {
                distTo[w]=distTo[v]+e.weight();
                edgeTo[w]=e;
                if(pq.contains(w))
                {
                    pq.change(w,distTo[w]);
                }
                else
                {
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }
    public double distTo(int v)
    {
        return distTo[v];
    }
    public boolean hasPathTo(int v)
    {
        return distTo[v]!=Double.POSITIVE_INFINITY;
    }
    public Iterable<DirectedEdge> pathTo(int v)
    {
        if(!hasPathTo(v))
        {
            return null;
        }
        Stack<DirectedEdge> stack=new Stack<>();
        for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()])
        {
            stack.push(e);
        }
        return stack;
    }
    public static void main(String[] args)
    {
        In in=new In("F:\\GitHub\\Algorithms\\Algorithms\\src\\Graphs\\tinyEWD.txt");
        EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(in);
        int s=0;
        DijkstraSP sp=new DijkstraSP(digraph,s);

        for(int t=0;t<digraph.V();t++)
        {
            System.out.print(s+" to "+t);
            System.out.printf("(%4.2f): ",sp.distTo(t));
            if(sp.hasPathTo(t))
            {
                for(DirectedEdge e:sp.pathTo(t))
                {
                    System.out.print(e+" ");
                }
            }
            System.out.println();
        }
    }
}
