package Algorithms_4th.Graphs;

import PriorityQueues.MinPQ;
import Algorithms_4th.Base.In;

/**
 * 最小生成树的Kruskal算法
 * 相对于Prim算法，这个直接从权重最小的边开始找起，遇到能够组成环的边就跳过，最后得到最小生成树
 * 也采用了union-find来检查是否组成边，也可以不用这个方法，直接用marked[]来标记就可以，具体实现可以参考CC类（查找图中的联通分量）
 * Created by ZingBug on 2017/10/9.
 */
public class KruskalMST {
    private Queue<Edge> mst;
    public KruskalMST(EdgeWeightedGraph G)
    {
        mst=new Queue<>();
        MinPQ<Edge> pq=new MinPQ<>();
        for(Edge e:G.edges())
        {
            pq.insert(e);
        }
        UF uf=new UF(G.V());
        while (!pq.isEmpty()&&mst.size()<G.V()-1)
        {
            Edge e=pq.delMin();
            int v=e.either();
            int w=e.other(v);
            if(uf.connected(v,w))
            {
                continue;
            }
            uf.union(v,w);
            mst.enqueue(e);
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
        KruskalMST mst=new KruskalMST(graph);
        for(Edge e:mst.edges())
        {
            System.out.println(e.toString());
        }
        System.out.println("All weight: "+mst.weight());
    }
}
