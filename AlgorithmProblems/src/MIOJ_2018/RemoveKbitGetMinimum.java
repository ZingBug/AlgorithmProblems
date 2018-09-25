package MIOJ_2018;
import java.util.*;
/**
 * 移除 K 位得到最小值
 * 描述
 * 有一行由正数组成的数字字符串，移除其中的 K 个数，使剩下的数字是所有可能中最小的。
 *
 * 假设：
 *
 * 字符串的长度一定大于等于 K
 * 字符串不会以 0 开头
 * 输入
 * 一行由 N 个正整数组成的数字字符串（0 < N < 20），和一个正整数 K（K < N），两个数据由空格隔开，如：1432219 3。
 *
 * 输出
 * 移除 K 位后可能的最小的数字字符串。 如 1432219 移除 4, 3, 2 这 3 个数字后得到 1219，为所有可能中的最小值。
 *
 * 输入样例
 * 1432219 3
 * 10200 1
 * 输出样例
 * 1219
 * 200
 *
 * https://code.mi.com/problem/list/view?id=9&cid=0&sid=37938#codearea
 * Created by ZingBug on 2018/9/6.
 */
public class RemoveKbitGetMinimum {
    private static String solution1(String line)
    {
        //最优解是删除出现的第一个左边>右边的数，因为删除之后高位减小
        String[] array=line.split(" ");
        int k=Integer.valueOf(array[1]);
        if(k==array[0].length())
        {
            return "0";
        }
        char[] nums=array[0].toCharArray();
        int len=nums.length;
        for(int i=0;i<k;i++)
        {
            boolean flag=false;
            for(int j=0;j<len-1-i;j++)
            {
                if(nums[j]>nums[j+1])
                {
                    while (j<len-1)
                    {
                        nums[j]=nums[j+1];
                        j++;
                    }
                    flag=true;
                    break;
                }
            }
            if(!flag)//如果所有数字递增，则删除最后几个数字直接返回
            {
                for(int j=len-k;j<len;j++)
                {
                    nums[j]=0;
                }
                break;
            }
        }
        StringBuilder sb=new StringBuilder();
        boolean first=true;
        for(int i=0;i<len-k;i++)
        {
            if(!(first&&nums[i]=='0'))
            {
                first=false;
                sb.append(nums[i]);
            }
        }
        if(first)
        {
            sb.append('0');
        }
        return sb.toString();
    }

    private static String solution2(String line)
    {
        //贪心算法，同时用栈比较适合处理。从前向后压入栈中，贪心规则是进制位越前的数字，越要取小的，遇到后面的更小，就把前面的删了。
        // 如19546要删除两个，肯定从前向后看，删前面最大的。当9进入栈中，后面5来了，因为5<9，并且需要删两个数，就把9放弃，又遇到4，把5也放弃，以此获取最小值。
        String[] array=line.split(" ");
        int k=Integer.valueOf(array[1]);
        char[] nums=array[0].toCharArray();
        int len=nums.length;
        if(k==len)
        {
            return "0";
        }
        Stack<Character> stack=new Stack<>();
        int count=0;
        boolean flag=false;
        for(int i=0;i<len;i++)
        {
            if(stack.isEmpty())
            {
                stack.push(nums[i]);
            }
            else
            {
                int temp=stack.peek();
                while (temp>nums[i]&&count<k)
                {
                    //删除栈顶元素
                    stack.pop();
                    count++;
                    flag=true;
                    if(!stack.isEmpty())
                    {
                        temp=stack.peek();
                    }
                    else
                    {
                        continue;
                    }
                }
                stack.push(nums[i]);
            }
        }
        if(!flag)
        {
            for(int i=0;i<k;i++)
            {
                stack.pop();
            }
        }
        StringBuilder sb=new StringBuilder();
        boolean first=true;
        for(char c:stack)
        {
            if(!(first&&c=='0'))
            {
                first=false;
                sb.append(c);
            }
        }
        if(first)
        {
            sb.append('0');
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        RemoveKbitGetMinimum r=new RemoveKbitGetMinimum();
        String line1="1432219 3";
        String line2="10200 1";
        String line3="123456 2";
        String line4="1234567890 9";
        System.out.println(r.solution2(line1));
        System.out.println(r.solution2(line2));
        System.out.println(r.solution2(line3));
        System.out.println(r.solution2(line4));
    }
}
