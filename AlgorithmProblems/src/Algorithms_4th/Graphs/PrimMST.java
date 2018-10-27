package Algorithms_4th.Graphs;

import Algorithms_4th.PriorityQueues.IndexMinPQ;
import Algorithms_4th.Base.In;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小生成树的Prim算法的即时实现版本
 * Created by ZingBug on 2017/10/9.
 */
public class PrimMST {
    private Edge[] edgeTo;//距离树最近的边
    private double[] distTo;//距离树最近的边的权重
    private boolean[] marked;//如果v在树中则为true
    private IndexMinPQ<Double> pq;//有效的横切边

    public PrimMST(EdgeWeightedGraph G)
    {
        edgeTo=new Edge[G.V()];
        distTo=new double[G.V()];
        marked=new boolean[G.V()];
        for(int v=0;v<G.V();v++)
        {
            distTo[v]=Double.POSITIVE_INFINITY;//无穷大
        }
        pq=new IndexMinPQ<>(G.V());

        distTo[0]=0.0;
        pq.insert(0,0.0);
        while (!pq.isEmpty())
        {
            visit(G,pq.delMin());
        }
    }
    private void visit(EdgeWeightedGraph G,int v)
    {
        //将顶点v添加到树中，更新数据
        marked[v]=true;
        for(Edge e:G.adj(v))
        {
            int w=e.other(v);
            if(marked[w])
            {
                continue;//v-w失效
            }
            if(e.weight()<distTo[w])
            {
                //连接w和树的最佳边Edge变为e
                edgeTo[w]=e;
                distTo[w]=e.weight();
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
    public Iterable<Edge> edges()
    {
        List<Edge> list=new ArrayList<>();
        for(int i=1;i<edgeTo.length;i++)
        {
            list.add(edgeTo[i]);
        }
        return list;
    }
    public double weight()
    {
        double weight=0;
        for(int i=1;i<distTo.length;i++)
        {
            weight+=distTo[i];
        }
        return weight;
    }
    public static void main(String[] agrs)
    {
        In in=new In("F:\\GitHub\\Algorithms\\Algorithms\\src\\Graphs\\tinyEWG.txt");
        EdgeWeightedGraph graph=new EdgeWeightedGraph(in);
        PrimMST mst=new PrimMST(graph);
        for(Edge e:mst.edges())
        {
            System.out.println(e.toString());
        }
        System.out.println("All weight: "+mst.weight());
    }
}
