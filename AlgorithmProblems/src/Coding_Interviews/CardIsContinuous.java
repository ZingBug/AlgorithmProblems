package Coding_Interviews;

import java.util.Arrays;

/**
 * 扑克牌顺子
 * 题目描述
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
 * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,
 * 决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),
 * “So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。为了方便起见,你可以认为大小王是0。
 *
 * https://www.nowcoder.com/practice/762836f4d43d43ca9deb273b3de8e1f4?tpId=13&tqId=11198&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 首先把数组排序，再统计数组中0的个数，最后统计排序之后的数组中相邻数字之间的空缺总数。如果空缺的总数小于或者等于0的个数，那么这个数组就是连续的；反之不是连续的。
 *
 * 注意：大小王假定为0，并且是可以看成任何数字的。
 * Created by ZingBug on 2018/7/5.
 */
public class CardIsContinuous {
    public boolean isContinuous(int[] numbers)
    {
        if(numbers==null||numbers.length<1)
        {
            return false;
        }
        int len=numbers.length;
        //先进行排序
        Arrays.sort(numbers);
        int numberOfZero=0;//数组中0的个数
        int numberOfGap=0;//排序数组中相邻数字之间的空缺总数
        //统计0的个数
        for(int i=0;i<len&&numbers[i]==0;i++)
        {
            numberOfZero++;
        }
        //统计数组中的间隔数目

        int small=numberOfZero;
        int big=small+1;
        while (big<len)
        {
            if(numbers[small]==numbers[big])//如果两个数相等，肯定不是顺子了。那就有可能是对子了。
            {
                return false;
            }
            numberOfGap+=numbers[big]-numbers[small]-1;//加上间隔数目
            small=big;
            big++;
        }
        return numberOfGap<=numberOfZero;
    }

    public static void main(String[] args)
    {
        CardIsContinuous c=new CardIsContinuous();
        int[] numbers1={0,2,3,5,6};
        int[] numbers2={0,0,2,5,6};
        int[] numbers3={0,2,5,1};
        System.out.println(c.isContinuous(numbers3));
    }
}
