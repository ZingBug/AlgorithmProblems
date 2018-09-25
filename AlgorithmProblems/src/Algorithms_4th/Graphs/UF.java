package Algorithms_4th.Graphs;

/**
 * union-find的实现
 * Created by ZingBug on 2017/10/9.
 */
public class UF {
    private int[] id;
    private int count;
    public UF(int N)
    {
        count=N;
        id=new int[N];
        for(int i=0;i<N;i++)
        {
            id[i]=i;
        }
    }
    public int count()
    {
        return count;
    }
    public boolean connected(int p,int q)
    {
        return find(p)==find(q);
    }
    private int find(int p)
    {
        //找到根触点
        while (p!=id[p])
        {
            p=id[p];
        }
        return p;
    }
    public void union(int p,int q)
    {
        //将p和q的根节点统一
        int pRoot=id[p];
        int qRoot=id[q];
        if(pRoot==qRoot)
        {
            return;
        }
        id[pRoot]=qRoot;
    }
}
