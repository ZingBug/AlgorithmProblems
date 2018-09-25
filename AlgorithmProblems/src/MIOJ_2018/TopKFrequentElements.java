package MIOJ_2018;

import javafx.beans.property.StringPropertyBase;

import java.util.*;
/**
 * Created by ZingBug on 2018/9/10.
 */
public class TopKFrequentElements {

    private static String solution1(String line)
    {
        //使用优先队列，首先要做的统计每个数字出现的次数，这个统计放在一个HashMap中，然后用PriorityQueue来找出结果，
        //因为在PriorityQueue中要借助HashMap来实现排序（将key按照value升序排序），所以在PriorityQueue的构造函数中要把Map当参数传递进去。
        String[] array1=line.split(" ");
        String[] array2=array1[0].split(",");

        int k=Integer.valueOf(array1[1]);
        //先统计每个数字出现的次数，这个貌似不可避免，时间复杂度为O(n)
        HashMap<Integer,Integer> map=new HashMap<>();
        for(String str:array2)
        {
            int num=Integer.valueOf(str);
            Integer count=map.get(num);
            if(count==null)
            {
                count=0;
            }
            map.put(num,count+1);
        }
        //用优先队列
        PriorityQueue<Integer> pq=new PriorityQueue<>(k, new Comp(map));//定义队列长度为k，和优先级规则
        for(int key:map.keySet())
        {
            if(pq.size()<k)
            {
                pq.add(key);
                continue;
            }
            int small=pq.peek();//弹出队列中最小的那个
            if(map.get(small)<map.get(key))
            {
                pq.poll();
                pq.add(key);
            }
            else if(map.get(small).equals(map.get(key))&&key<small)//出现频率相同时，较小的数在前
            {
                pq.poll();
                pq.add(key);
            }
        }
        StringBuilder sb=new StringBuilder();
        int[] nums=new int[k];
        int i=k-1;
        for (int num:pq)
        {
            nums[i--]=num;
        }
        //Arrays.sort(nums);
        for(i=0;i<k;i++)
        {
            sb.append(nums[i]);
            if(i<k-1)
            {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    private static class Comp implements Comparator<Integer>
    {
        HashMap<Integer,Integer> map;
        public Comp(HashMap<Integer,Integer> map)
        {
            this.map=map;
        }
        @Override
        public int compare(Integer o1, Integer o2) {
            return map.get(o1)-map.get(o2);
        }
    }

    private static String solution2(String line)
    {
        //利用桶排序的思想，注意这里的桶排序时间复杂度为O(n),空间复杂度为O(n)，桶的下标表示出现的次数，桶的元素是一个List，表示所有出现了这些次数的元素
        //这个桶排序实际上是将统计数字出现次数的Map逆转一下
        String[] array1=line.split(" ");
        String[] array2=array1[0].split(",");

        int k=Integer.valueOf(array1[1]);
        //先统计每个数字出现的次数，这个貌似不可避免，时间复杂度为O(n)
        HashMap<Integer,Integer> map=new HashMap<>();
        for(String str:array2)
        {
            int num=Integer.valueOf(str);
            Integer count=map.get(num);
            if(count==null)
            {
                count=0;
            }
            map.put(num,count+1);
        }

        //第二步，构造一个桶，下标表示出现次数，如果nums大小为n，且这n个数相等，那么出现次数最大为n，所有桶的大小为n+1
        //这个桶实际上是将上面那个map的key value翻转了一下，因为对于同一个value可能有多个key，所以buckey的元素应该是个列表,
        List<Integer>[] bucket=new List[array2.length+1];
        for(int key:map.keySet())
        {
            int count=map.get(key);
            if(bucket[count]==null)
            {
                ArrayList<Integer> temp=new ArrayList<>();
                temp.add(key);
                bucket[count]=temp;
            }
            else
            {
                bucket[count].add(key);
            }
        }

        StringBuilder sb=new StringBuilder();
        int count=0;
        for(int i=bucket.length-1;i>=0&&count<k;i--)
        {
            if(bucket[i]!=null)
            {
                Collections.sort(bucket[i]);
                for(int j=0;j<bucket[i].size()&&count<k;j++)
                {
                    sb.append(bucket[i].get(j));
                    count++;
                    if(count<k)
                    {
                        sb.append(",");
                    }
                }
            }
        }

        return sb.toString();

    }
    public static void main(String[] args)
    {
        TopKFrequentElements t=new TopKFrequentElements();
        String line1="1,1,1,2,2,3 2";
        String line2="1,2 2";
        String line3="1,1,1,2,2,3 2";
        String line4="1,2,2,3,3, 3";
        System.out.println(t.solution1(line4));
    }
}
