package MIOJ_2018;

/**
 * 交叉队列
 * 描述
 * 给出三个队列 s1，s2，s3 ，判断 s3 是否是由 s1 和 s2 交叉得来。 如：s1 为 aabcc ， s2 为 dbbca。 当 s3 为 aadbbcbcac 时，返回 true（即将 s1 拆成三部分： aa，bc，c 分别插入 s2 对应位置） 否则返回 false。
 *
 * 输入
 * aabcc,dbbca,aadbbcbcac
 *
 * 输出
 * true
 *
 * 输入样例
 * aabcc,dbbca,aadbbcbcac
 * aabcc,dbbca,aadbbbaccc
 * a,b,ab
 * a,b,ba
 * a,b,ac
 * abc,bca,bcaabc
 * abc,bca,aabbcc
 *
 * 输出样例
 * true
 * false
 * true
 * true
 * false
 * true
 * false
 *
 * https://code.mi.com/problem/list/view?id=6
 * Created by ZingBug on 2018/9/4.
 */
public class CrossQueue {
    private static boolean result=false;
    private static String solution1(String line)
    {
        //采用递归方法
        String[] array=line.split(",");

        result=false;

        int len1=array[0].length();
        int len2=array[1].length();
        int len3=array[2].length();
        if(len3==len1+len2)
        {

            visit(array[0],0,len1,array[1],0,len2,array[2],0);
        }

        return String.valueOf(result);
    }
    private static void visit(String s1,int pos1,int len1,String s2,int pos2,int len2,String s3,int pos3)
    {
        if(result)
        {
            return;
        }
        if(pos1<len1&&s1.charAt(pos1)==s3.charAt(pos3))
        {
            visit(s1,pos1+1,len1,s2,pos2,len2,s3,pos3+1);
        }
        if(pos2<len2&&s2.charAt(pos2)==s3.charAt(pos3))
        {
            visit(s1,pos1,len1,s2,pos2+1,len2,s3,pos3+1);
        }
        if(pos1>=len1&&pos2>=len2)
        {
            result=true;
        }
    }

    private static String solution2(String line)
    {
        //采用类似于动态规划来做
        String[] array=line.split(",");
        int len1=array[0].length();
        int len2=array[1].length();
        int len3=array[2].length();
        if(len3!=len1+len2)
        {
            return String.valueOf(false);
        }
        String s1=array[0];
        String s2=array[1];
        String s3=array[2];
        boolean[][] dp=new boolean[len1+1][len2+1];
        dp[0][0]=true;
        for(int i=0;i<len1+1;i++)
        {
            for(int j=0;j<len2+1;j++)
            {
                if((i-1)>=0&&dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1))
                {
                    //dp[i-1][j]=true表示s3[1, i-1+j]由s1[1,i-1]和s2[1,j]交错组成,如果s1.charAt(i-1)与s3.charAt(i-1+j)相等，那么很显然dp[i][j]也为true
                    dp[i][j]=true;
                }
                if((j-1)>=0&&dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1))
                {
                    //dp[i][j-1]=true表示s3[1, i+j-1]由s1[1,i]和s2[1,j-1]交错组成,如果s2.charAt(j-1)与s3.charAt(i+j-1)相等，那么很显然dp[i][j]也为true
                    dp[i][j]=true;
                }
            }
        }
        return String.valueOf(dp[len1][len2]);
    }

    public static void main(String[] args)
    {
        CrossQueue c=new CrossQueue();
        String line1="aabcc,dbbca,aadbbcbcac";
        String line2="aabcc,dbbca,aadbbbaccc";
        String line3="a,b,ab";
        String line4="a,b,ba";

        System.out.println(c.solution2(line1));
        System.out.println(c.solution2(line2));
        System.out.println(c.solution2(line3));
        System.out.println(c.solution2(line4));

    }
}
