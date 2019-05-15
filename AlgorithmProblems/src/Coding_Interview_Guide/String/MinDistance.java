package Coding_Interview_Guide.String;


import java.util.HashMap;
import java.util.Map;

/**
 * 数组中两个字符串的最小距离
 * 给定一个字符串数组strs，再给定两个字符串str1和str2，返回在strs中str1与str2的最小距离，如果str1或str2的最小距离，如果str1或str2为null，或不在strs中，返回-1
 *
 * Page266
 * Created by ZingBug on 2019/4/27.
 */
public class MinDistance {

    private int minDistance1(String[] strs, String str1, String str2) {
        if (strs == null || strs.length == 0) {
            return -1;
        }
        if (str1 == null || str2 == null || str1.equals(str2)) {
            return -1;
        }

        int last1 = -1;
        int last2 = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(str1)) {
                min = Math.min(min, last2 == -1 ? min : i - last2);
                last1 = i;
            }
            if (strs[i].equals(str2)) {
                min = Math.min(min, last1 == -1 ? min : i - last1);
                last2 = i;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }


    private HashMap<String,HashMap<String,Integer>> record;
    private void record(String[] strArr)
    {
        record=new HashMap<>();
        HashMap<String,Integer> indexMap=new HashMap<>();
        for(int i=0;i!=strArr.length;i++)
        {
            String curStr=strArr[i];
            update(indexMap,curStr,i);
            indexMap.put(curStr,i);
        }

    }

    private void update(HashMap<String,Integer> indexMap,String str,int i)
    {
        if(!record.containsKey(str))
        {
            record.put(str,new HashMap<String,Integer>());
        }
        HashMap<String,Integer> strMap=record.get(str);
        for(Map.Entry<String,Integer> lastEntry:indexMap.entrySet())
        {
            String key=lastEntry.getKey();
            int index=lastEntry.getValue();
            if(!key.equals(str))
            {
                HashMap<String,Integer> lastMap=record.get(key);
                int curMin=i-index;
                if(strMap.containsKey(key))
                {
                    int preMin=strMap.get(key);
                    if(curMin<preMin)
                    {
                        strMap.put(key,curMin);
                        lastMap.put(str,curMin);
                    }
                }
                else
                {
                    strMap.put(key,curMin);
                    lastMap.put(str,curMin);
                }
            }
        }
    }

    private int minDistance2(String[] strs, String str1, String str2) {
        if (strs == null || strs.length == 0) {
            return -1;
        }
        if (str1 == null || str2 == null || str1.equals(str2)) {
            return -1;
        }
        record(strs);
        if(record.containsKey(str1)&&record.get(str1).containsKey(str2))
        {
            return record.get(str1).get(str2);
        }

        return -1;
    }


    public static void main(String[] args)
    {
        MinDistance minDistance=new MinDistance();
        String[] strs={"1","3","3","3","2","3","1"};
        String str1="1";
        String str2="2";
        System.out.println(minDistance.minDistance2(strs,str1,str2));

    }
}
