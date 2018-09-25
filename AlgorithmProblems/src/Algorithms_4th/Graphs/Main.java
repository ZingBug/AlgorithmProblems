package Algorithms_4th.Graphs;

import edu.princeton.cs.algs4.In;

/**
 * 测试主函数
 * 用于测试深度优先搜索和广度优先搜索寻找路径
 * by ZingBug 2017/9/24
 */
public class Main {
    public static void main(String[] args)
    {
        Graph graph=new Graph(6);
        //此无向图的示意图见书本P340,图4.1.13
        graph.addEdge(0,5);
        graph.addEdge(2,4);
        graph.addEdge(2,3);
        graph.addEdge(1,2);
        graph.addEdge(0,1);
        graph.addEdge(3,4);
        graph.addEdge(3,5);
        graph.addEdge(0,2);

        Graph graph1=new Graph(6);
        graph1.addEdge(0,1);
        graph1.addEdge(1,2);
        graph1.addEdge(2,3);
        graph1.addEdge(3,1);
        graph1.addEdge(4,5);

        System.out.println("顶点3的度数为:"+graph.degree(3));
        System.out.println("顶点最大度数为:"+graph.maxDegree());

        System.out.println(graph.toString());

        //寻找路径
        int s=0;
        System.out.println("DepthFirstPaths:");
        DepthFirstPaths depthFirstPaths=new DepthFirstPaths(graph,s);
        for(int v=0;v<graph.V();v++)
        {
            System.out.print(s+" to "+v+": ");
            if(depthFirstPaths.hasPathTo(v))
            {
                for(int x:depthFirstPaths.pathTo(v))
                {
                    if(x==s)
                    {
                        System.out.print(x);
                    }
                    else
                    {
                        System.out.print("-"+x);
                    }
                }
            }
            System.out.println();
        }
        System.out.println("BreadthFirstPaths:");
        BreadthFirstPaths breadthFirstPaths=new BreadthFirstPaths(graph,s);
        for(int v=0;v<graph.V();v++)
        {
            System.out.print(s+" to "+v+": ");
            if(breadthFirstPaths.hasPathTo(v))
            {
                for(int x:breadthFirstPaths.pathTo(v))
                {
                    if(x==s)
                    {
                        System.out.print(x);
                    }
                    else
                    {
                        System.out.print("-"+x);
                    }
                }
            }
            System.out.println();
        }
        //测试查找图的连通分量
        System.out.println("连通分量");
        CC cc=new CC(graph1);
        int ccM=cc.count();
        Bag<Integer>[] bag= new Bag[ccM];
        for(int i=0;i<ccM;i++)
        {
            bag[i]=new Bag<Integer>();
        }
        for(int v=0;v<graph1.V();v++)
        {
            bag[cc.id(v)].add(v);
        }
        for(int i=0;i<ccM;i++)
        {
            for(int v:bag[i])
            {
                System.out.print(v+" ");
            }
            System.out.println();
        }
    }
}
