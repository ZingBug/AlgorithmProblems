package Others;

import java.util.*;

/**
 * TopN问题
 * 求10亿个数中的最大的前10个数，时时构建只有10个元素的小顶堆，如果比堆顶小，则不处理；如果比堆顶大，则替换堆顶，然后依次下沉到适当的位置。
 * 其实也可以一直维护一个n长度的数组即可，不需要堆。
 *
 * https://mp.weixin.qq.com/s?__biz=Mzg2NzA4MTkxNQ==&mid=2247485427&idx=1&sn=5eb4c507c61e08631b0f3601cb268fd3&chksm=ce404c27f937c531e4fc42688657569d10b15e118659a508e1ae1c849e9139bf982f33730bae&scene=0&xtrack=1&key=a9ac41334684e0a1e059cc126d2eb09ea7ca2ee305f2ef0ed449381e305d1e2e8c6150fd8b14070f4e0a2fa7ed88ea40916bcd33d3ffd643632b9f53ac01ab3b4263743fae2b51fb754ba3ed14bf0719&ascene=1&uin=MjQ3MzkwMTc2Mw%3D%3D&devicetype=Windows+10&version=62060739&lang=zh_CN&pass_ticket=sM3O0J9pi9FBondEermxLwR4rCjScZ%2B669TsnqQMhBxYqO7uy7GdTkStkYaWkLIv
 *
 * Created by ZingBug on 2019/5/15.
 */
public class TopN {

    //父节点
    private int parent(int n) {
        return (n - 1) / 2;
    }

    //右孩子
    private int right(int n) {
        return 2 * n + 1;
    }

    //左孩子
    private int left(int n) {
        return 2 * n + 2;
    }

    //建立小顶堆
    private void buildHeap(int n, int[] data) {

        for (int i = 1; i < n; i++) {
            int t = i;
            while (t != 0 && data[t] < data[parent(t)]) {
                int temp = data[t];
                data[t] = data[parent(t)];
                data[parent(t)] = temp;
                t = parent(t);
            }
        }
    }


    private void buildArray(int n,int[] data)
    {
        int[] temp=new int[n];
        for(int i=0;i<n;i++)
        {
            temp[i]=data[i];
        }
        Arrays.sort(temp);
        for(int i=0;i<n;i++)
        {
            data[i]=temp[i];
        }

    }

    //调整堆
    private void adjust(int i, int n, int[] data) {
        if (data[0] >= data[i]) {
            return;
        }
        //替换堆顶
        int temp = data[i];
        data[i] = data[0];
        data[0] = temp;

        //下沉操作
        int t = 0;
        while ((left(t) < n && data[left(t)] < data[t]) ||
                (right(t) < n && data[right(t)] < data[t])) {
            if (left(t) < n && data[left(t)] < data[right(t)]) {
                //左孩子更小，置换左孩子
                temp = data[t];
                data[t] = data[left(t)];
                data[left(t)] = temp;
                t = left(t);
            } else {
                //右孩子更小，置换右孩子
                temp = data[t];
                data[t] = data[right(t)];
                data[right(t)] = temp;
                t = right(t);
            }
        }
    }

    private void adjustArray(int i,int n,int[] data)
    {
        if(data[i]<=data[0])
        {
            return;
        }

        int temp=data[0];
        data[0]=data[i];
        data[i]=temp;

        int t=1;
        while (t<n&&data[t-1]>data[t])
        {
            temp=data[t-1];
            data[t-1]=data[t];
            data[t]=temp;
            t++;
        }
    }

    private void findTopN(int n, int[] data) {
        //先构建n个数的小顶堆
        buildHeap(n, data);
        //n往后的数进行调整
        for (int i = n; i < data.length; i++) {
            adjust(i, n, data);
        }
    }

    private void findTopNbyArray(int n,int [] data)
    {
        buildArray(n,data);
        for (int i = n; i < data.length; i++) {
            adjust(i, n, data);
        }
    }

    private void print(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        TopN topN = new TopN();
        int[] data = {56, 30, 71, 18, 29, 93, 44, 75, 20, 65, 68, 34};
        System.out.println("原数组：");
        topN.print(data);
        topN.findTopNbyArray(5, data);
        System.out.println("调整后数组:");
        topN.print(data);
    }


}


