package MIOJ_2018;
import java.util.*;

/**
 * 找出单独出现的数字
 * 描述
 * 给出N个数字。其中仅有一个数字出现过一次，其他数字均出现过两次，找出这个出现且只出现过一次的数字。要求时间和空间复杂度最小。
 *
 * 输入
 * 输入多个数字，每个数字以空格分开，回车结束
 *
 * 输出
 * 输出内容为只出现过唯一一次的数字
 *
 * 输入样例
 * 10 10 11 12 12 11 16
 *
 * 输出样例
 * 16
 * https://code.mi.com/problem/list/view?id=2&cid=0&sid=28888#codearea
 * Created by ZingBug on 2018/9/2.
 */
public class FindSingleNumber {

    private static String solution1(String line)
    {
        //第一种是传统方法
        String[] array=line.split(" ");
        HashSet<Integer> set=new HashSet<>();
        int count=0;
        for(String str:array)
        {
            int num=Integer.valueOf(str);
            if(set.contains(num))
            {
                count-=num;
            }
            else
            {
                set.add(num);
                count+=num;
            }
        }

        return String.valueOf(count);
    }

    private static String solution2(String line)
    {
        //第一种用异或，一个数字异或它自己结果为0，异或0结果为它自己即a^a=0，a^0=a，且异或满足a^b^c=a^(b^c)
        String[] array=line.split(" ");
        int count=0;
        for(String str:array)
        {
            int num=Integer.valueOf(str);
            count^=num;
        }
        return String.valueOf(count);
    }

    public static void main(String[] args)
    {
        FindSingleNumber f=new FindSingleNumber();
        String line="10 10 11 12 12 11 16";
        System.out.println(f.solution2(line));
    }
}
