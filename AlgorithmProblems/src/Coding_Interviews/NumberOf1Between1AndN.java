package Coding_Interviews;

/**
 * 整数中1出现的次数（从1到n整数中1出现的次数）
 * 题目描述
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数。
 *
 * https://www.nowcoder.com/practice/bd7f978302044eee894445e244c7eee6?tpId=13&tqId=11184&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 1、第一种方法简单暴力，就是遍历1-n，然后找每个数1的个数，加起来就是。
 * 2、第二种方法是考虑将n的十进制的每一位单独拿出讨论，具体思路如下链接
 * https://blog.csdn.net/yi_afly/article/details/52012593
 *
 * Created by ZingBug on 2018/6/27.
 */
public class NumberOf1Between1AndN {
    private int solution1(int n)
    {
        int count=0;
        if(n<1)
        {
            return count;
        }
        for(int i=1;i<=n;i++)
        {
            int temp=i;
            while (temp!=0)
            {
                if(temp%10==1)
                {
                    count++;
                }
                temp=temp/10;
            }
        }
        return count;
    }
    private int solution2(int n)
    {
        int count=0;
        if(n<1)
        {
            return count;
        }
        int base=1;
        int former=0;
        while (n!=0)
        {
            int bit=n%10;
            n=n/10;
            if(base==1)
            {
                //个位情况
                count+=(bit==0?n:n+1);
            }
            else
            {
                //十位及以上位数
                if(bit==0)
                {
                    count+=(n*base);
                }
                else if(bit==1)
                {
                    count+=(n*base+former+1);
                }
                else
                {
                    count+=(n*base+base);
                }
            }
            former+=bit*base;
            base*=10;

        }
        return count;
    }
    public int NumberOf1Between1AndN_Solution(int n)
    {
        return solution2(n);
    }

    public static void main(String[] args)
    {
        NumberOf1Between1AndN b=new NumberOf1Between1AndN();
        int n=534;
        System.out.println(b.NumberOf1Between1AndN_Solution(n));
        System.out.println(b.solution1(n));
    }
}
