package MIOJ_2018;
import java.util.*;
/**
 * 最长连续数列
 * 描述
 * 输入一个乱序的连续数列，输出其中最长连续数列长度，要求算法复杂度为 O(n) 。
 *
 * 输入
 * 54,55,300,12,56
 *
 * 输出
 * 3
 *
 * 输入样例
 * 100,4,200,1,3,2
 * 54,55,300,12
 * 1
 * 5,4,3,2,1
 * 1,2,3,4,5,6
 *
 * 输出样例
 * 4
 * 2
 * 1
 * 5
 * 6
 *
 * https://code.mi.com/problem/list/view?id=4
 *
 * Created by ZingBug on 2018/9/4.
 */
public class ContinuousSeries {
    private static String solution(String line)
    {
        String[] array=line.split(",");
        int[] nums=new int[array.length];
        HashSet<Integer> set=new HashSet<>();
        int i=0;
        for(String str:array)
        {
            int num=Integer.valueOf(str);
            set.add(num);
            nums[i++]=num;
        }
        int maxlen=1;
        for(int num:nums)
        {
            int curr=num;
            int len=0;
            //检查上边界
            while (set.contains(curr))
            {
                set.remove(curr);
                curr++;
                len++;
            }
            curr=num-1;
            //检查下边界
            while (set.contains(curr))
            {
                set.remove(curr);
                curr--;
                len++;
            }
            maxlen=Math.max(maxlen,len);
        }
        return String.valueOf(maxlen);
    }

    public static void main(String[] args)
    {
        ContinuousSeries c=new ContinuousSeries();
        String line1="100,4,200,1,3,2";
        String line2="54,55,300,12";
        String line3="1";
        String line4="5,4,3,2,1";
        String line5="1,2,3,4,5,6";

        System.out.println(c.solution(line1));
        System.out.println(c.solution(line2));
        System.out.println(c.solution(line3));
        System.out.println(c.solution(line4));
        System.out.println(c.solution(line5));
    }
}
