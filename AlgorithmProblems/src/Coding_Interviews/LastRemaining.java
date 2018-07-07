package Coding_Interviews;

/**
 * 孩子们的游戏（圆圈中最后剩下的数）
 * 题目描述
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
 * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,
 * 继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 *
 * https://www.nowcoder.com/practice/f78a359491e64a50bce2d89cff857eb6?tpId=13&tqId=11199&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * Created by ZingBug on 2018/7/5.
 */
public class LastRemaining {
    private int solution1(int n,int m)
    {
        //第一种方法，去模拟环形循环来做。时间复杂度为O(mn)
        if(n<1||m<1)
        {
            return -1;
        }
        int remainNum=n;
        int[] array=new int[n];
        int start=0;
        int length=array.length;
        for(int i=0;i<n;i++)
        {
            array[i]=i;
        }
        while (length>1)
        {
            int i=0;
            //找到本次报数中第m-1个孩子
            while (true)
            {
                if(i==m-1)
                {
                    break;
                }
                i++;
                start++;
                if(start==length)
                {
                    start=0;
                }
            }
            //重新排序数组
            for(int j=start;j<length-1;j++)
            {
                array[j]=array[j+1];
            }
            length--;
            if(start==length)
            {
                start=0;
            }
        }
        return array[start];
    }
    private int solution2(int n,int m)
    {
        //第二种方法，剑指offer上的方法，用递归来实现。没看懂。事件复杂度为O(n)
        if(n<1||m<1)
        {
            return -1;
        }
        int last=0;
        for(int i=2;i<=n;i++)
        {
            last=(last+m)%i;
        }
        return last;
    }
    public int LastRemaining_Solution(int n,int m)
    {
        return solution2(n,m);
    }
    public static void main(String[] args)
    {
        LastRemaining l=new LastRemaining();
        int n=4;
        int m=2;
        System.out.println(l.LastRemaining_Solution(n,m));
    }
}
