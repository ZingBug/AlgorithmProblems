package Coding_Interviews;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 * 题目描述
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * https://www.nowcoder.com/practice/9be0172896bd43948f8a32fb954e1be1?tpId=13&tqId=11216&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 1、使用大顶堆+小顶堆的容器。
 * 2、两个堆中的数据数目差不能超过1，这样可以使中位数只会出现在两个堆的交接处。
 * 3、大顶堆的所有数据都小于小顶堆，这样就满足了排序要求。平均数就在两个堆顶的数之中。
 *
 * Created by ZingBug on 2018/7/11.
 */
public class MedianInStream {
    private int count=0;
    private PriorityQueue<Integer> minHeap=new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap=new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });

    public void Insert(Integer num)
    {
        if(count%2==0)//偶数放入最小堆
        {
            //为了保证最小堆的数值都大于最大堆
            maxHeap.offer(num);
            int maxNum=maxHeap.poll();
            minHeap.offer(maxNum);
        }
        else if(count%2==1)//奇数放入最大堆
        {
            //为了保证最大堆的数值都小于最小堆
            minHeap.offer(num);
            int minMum=minHeap.poll();
            maxHeap.offer(minMum);
        }
        count++;
    }

    public Double GetMedian()
    {
        if(count%2==0)
        {
            return (maxHeap.peek()+minHeap.peek())/2.0;
        }
        else
        {
            return (double)minHeap.peek();
        }
    }

    public static void main(String[] args)
    {
        MedianInStream m=new MedianInStream();
        m.Insert(1);
        m.Insert(3);
        m.Insert(2);
        m.Insert(4);
        m.Insert(5);
        System.out.println(m.GetMedian());
    }
}
