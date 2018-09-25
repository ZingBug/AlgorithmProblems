package Algorithms_4th.PriorityQueues;

/**
 * 优先队列类执行程序
 * by ZingBug 2017/8/31
 */
public class Main {
    public static void main(String[] args)
    {
        String[] a= new String[] {"S","O","R","T","E","X","A","M","P","L","E"};
        int N=a.length;
        IndexMinPQ<String> pq=new IndexMinPQ<>(N);
        for(int i=0;i<N;i++)
        {
            pq.insert(i,a[i]);
        }
        String min=pq.min();
        int minIndex=pq.minIndex();
        int minIndex2=pq.delMin();
        String min2=pq.min();
        System.out.println(min);
        System.out.println(minIndex);
        System.out.println(minIndex2);
        System.out.println(min2);
        System.out.println(pq.size());
    }
}
