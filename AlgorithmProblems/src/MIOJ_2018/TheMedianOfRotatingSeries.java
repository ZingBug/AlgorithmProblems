package MIOJ_2018;
import java.util.*;
/**
 * 找出旋转有序数列的中间值
 * 描述
 * 给出一个有序数列随机旋转之后的数列，如原有序数列为：[0,1,2,4,5,6,7] ，旋转之后为[4,5,6,7,0,1,2]。
 * 假定数列中无重复元素，且数列长度为奇数。
 * 求出旋转数列的中间值。如数列[4,5,6,7,0,1,2]的中间值为4。
 *
 * 输入
 * 4,5,6,7,0,1,2
 *
 * 输出
 * 4
 *
 * 输入样例
 * 1
 * 1,2,3
 * 4,5,6,7,0,1,2
 * 12,13,14,5,6,7,8,9,10
 *
 * 输出样例
 * 1
 * 2
 * 4
 * 9
 *
 * https://code.mi.com/problem/list/view?id=5
 * Created by ZingBug on 2018/9/4.
 */
public class TheMedianOfRotatingSeries {

    private static String solution(String line)
    {
        //最简单的思路，先排序，然后再找
        String[] array=line.split(",");
        int[] nums=new int[array.length];
        int i=0;
        for(String str:array)
        {
            int num=Integer.valueOf(str);
            nums[i++]=num;
        }
        Arrays.sort(nums);
        int result=nums[array.length/2];
        return String.valueOf(result);
    }

    public static void main(String[] args)
    {
        TheMedianOfRotatingSeries t=new TheMedianOfRotatingSeries();
        String line1="1";
        String line2="1,2,3";
        String line3="4,5,6,7,0,1,2";
        String line4="12,13,14,5,6,7,8,9,10";

        System.out.println(t.solution(line1));
        System.out.println(t.solution(line2));
        System.out.println(t.solution(line3));
        System.out.println(t.solution(line4));
    }
}
