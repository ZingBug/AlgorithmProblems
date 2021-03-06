package CodeM_2018.Qualification;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 4、分数
 * 题目描述
 * 小胖参加了人生中最重要的比赛——MedoC资格赛。MedoC的资格赛由m轮构成，使用常见的“加权标准分”的规则。每位选手需要参加所有的m轮的比赛。在一轮中，能取得的分数为自己的成绩除掉最高分的成绩。每个选手的总分为每一轮获得的分数乘上这一轮比赛占得比重。如果在某一轮比赛中所有人获得了零分，那么所有选手在这一轮获得的分数都为0分。
 * 比如说，资格赛一共3轮，三轮的权重分别为30%, 30%, 40%。在第一轮中，小胖获得了300分，最高分也为300分。在第二轮中，小胖获得了0分，最高分也为0分。在第三轮中，小胖获得了150分，最高分为300分，那么小胖的总分为(300/300)*30%+0*30%+(150/300)*40%=0.5。
 * 一共有n位选手参加了比赛，其中成绩最高的k位选手能够晋级到初赛。如果有多人在分数线上同分，那么系统会随机在这些同分的人中选取，选满k个晋级为止。
 * 小胖现在知道了每个选手每场比赛的成绩，但是由于他的疏忽，其中的某个人某场比赛的成绩消失了。所以更多人出线的情况变得未知起来。现在只知道成绩一定是0到C之间的一个整数（包含0和C）。
 * 小胖想知道对于每个人的出线情况是怎么样的，也就是一定能出线，一定不能出线还是有可能出线。
 *
 * 输入描述:
 * 第一行四个正整数n,m,k,C (m <= 6, k <= n <= 500, C <= 500)。
 * 接下来一行m个整数w1, w2, ..., wm，表示每场比赛的权重，第i场比赛的权重为wi/(w1+w2+...+wm)，保证wi >= 0且1 <= w1 + w2 + ... + wm <= 1000。
 * 接下来n行每行m个整数，第i个整数表示这个选手在第i场比赛中获得的成绩。如果这个数字为-1表示这个数据丢失，保证恰好有一个-1。
 *
 * 输出描述:
 * n行每行输出一个1到3之间的整数。1表示一定出线，2表示一定不出线，3表示可能出线。
 *
 * 示例1
 * 输入
 * 4 2 2 100
 * 1 1
 * 100 99
 * 70 70
 * 40 -1
 * 100 39
 *
 * 输出
 * 1
 * 3
 * 3
 * 2
 */
public class Score {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int k=in.nextInt();
        int C=in.nextInt();

        int[] w=new int[m];//每轮权重
        double w_sum=0;
        for(int i=0;i<m;i++)
        {
            w[i]=in.nextInt();
            w_sum+=w[i];
        }
        double[][] scores=new double[n][m];//选手成绩

        int sn=0;//缺失成绩的选手
        int sm=0;//缺失成绩的轮

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                scores[i][j]=in.nextInt();
                if(scores[i][j]==-1)
                {
                    sn=i;
                    sm=j;
                }
            }
        }

        int[] c1=new int[n];//如果为0代表肯定不能出线
        int[] c2=new int[n];//如果为0代表肯定能出线

        for(int x=0;x<=C;x++)//枚举每种可能的成绩情况
        {
            scores[sn][sm]=x;
            double[][] scores_weight=new double[n][m];//每个人除以最高分后的成绩
            double[] scores_max=new double[m];//每轮最高成绩
            for(int i=0;i<m;i++)
            {
                for(int j=0;j<n;j++)
                {
                    scores_max[i]=Math.max(scores_max[i],scores[j][i]);
                }
                for(int j=0;j<n;j++)
                {
                    if(scores_max[i]!=0)
                    {
                        scores_weight[j][i]=scores[j][i]/scores_max[i];
                    }
                    else
                    {
                        scores_weight[j][i]=0;
                    }
                }
            }

            double[] scores_end=new double[n];//每个人的最终成绩
            Double[] scores_end_copy=new Double[n];//最终成绩备份
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    scores_end[i]+=scores_weight[i][j]*w[j]/w_sum;
                }
                scores_end_copy[i]=scores_end[i];
            }

            Arrays.sort(scores_end_copy,(o1,o2)->-Double.compare(o1,o2));//Lambda表达式，降序排序

            for(int i=0;i<n;i++)//重点在这
            {
                if(scores_end[i]>=scores_end_copy[k-1])
                {
                    c1[i]++;
                }
                if((k!=n)&&(scores_end[i]<=scores_end_copy[k]))
                {
                    c2[i]++;
                }
            }
        }

        for(int i=0;i<n;i++)
        {
            if(c1[i]==0)
            {
                System.out.println(2);
            }
            else if(c2[i]==0)
            {
                System.out.println(1);
            }
            else
            {
                System.out.println(3);
            }
        }

    }

}
