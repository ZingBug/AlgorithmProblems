package Algorithms_4th.Graphs;

import PriorityQueues.MinPQ;
import Algorithms_4th.Base.In;

/**
 * 最小生成树的Prim算法的延时实现
 * Created by ZingBug on 2017/10/9.
 */
public class LazyPrimMST {
    private boolean[] marked;//最小生成树的顶点
    private Queue<Edge> mst;//最小生成树的边
    private MinPQ<Edge> pq;//横切边（包括失效的边）

    public LazyPrimMST(EdgeWeightedGraph G)
    {
        pq=new MinPQ<>();
        marked=new boolean[G.V()];
        mst=new Queue<>();

        visit(G,0);//假设G是连通的
        while (!pq.isEmpty())
        {
            Edge e=pq.delMin();//从pq中得到权重最小的边
            int v=e.either();
            int w=e.other(v);
            if(marked[v]&&marked[w])
            {
                //跳过失效的边，并没有删除，这是延时实现
                continue;
            }
            mst.enqueue(e);//将边添加到树中
            if(!marked[v])
            {
                visit(G,v);
            }
            if(!marked[w])
            {
                visit(G,w);
            }
        }
    }

    private void visit(EdgeWeightedGraph G,int v)
    {
        marked[v]=true;
        for(Edge e:G.adj(v))
        {
            if(!marked[e.other(v)])
            {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges()
    {
        return mst;
    }
    public double weight()
    {
        double weight=0;
        for(Edge e:mst)
        {
            weight+=e.weight();
        }
        return weight;
    }

    public static void main(String[] agrs)
    {
        In in=new In("F:\\GitHub\\Algorithms\\Algorithms\\src\\Graphs\\tinyEWG.txt");
        EdgeWeightedGraph graph=new EdgeWeightedGraph(in);
        LazyPrimMST mst=new LazyPrimMST(graph);
        for(Edge e:mst.edges())
        {
            System.out.println(e.toString());
        }
        System.out.println("All weight: "+mst.weight());
    }

}
