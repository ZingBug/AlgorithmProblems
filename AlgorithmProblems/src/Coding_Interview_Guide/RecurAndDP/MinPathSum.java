package Coding_Interview_Guide.RecurAndDP;

/**
 * 矩阵的最小路径
 * 经典的动态规划
 * Page187
 *
 * Created by ZingBug on 2018/12/28.
 */
public class MinPathSum {
    //空间复杂度为O(m*n)，时间复杂度为O(m*n)
    private int minPathSum1(int[][] m)
    {
        if(m==null||m.length==0||m[0]==null||m[0].length==0)
        {
            return 0;
        }
        int row=m.length;
        int col=m[0].length;
        int[][] dp=new int[row][col];
        dp[0][0]=m[0][0];
        for(int i=1;i<col;i++)//第一行
        {
            dp[0][i]=dp[0][i-1]+m[0][i];
        }
        for(int i=1;i<row;i++)//第一列
        {
            dp[i][0]=dp[i-1][0]+m[i][0];
        }
        for(int i=1;i<row;i++)
        {
            for(int j=1;j<col;j++)
            {
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+m[i][j];
            }
        }
        return dp[row-1][col-1];
    }
    //空间复杂度为O(min{m,n})，时间复杂度为O(m*n)
    private int minPathSum2(int[][] m)
    {
        if(m==null||m.length==0||m[0]==null||m[0].length==0)
        {
            return 0;
        }
        int less=Math.min(m.length,m[0].length);
        int more=Math.max(m.length,m[0].length);
        int[] dp=new int[less];
        boolean rowmore=more==m.length;
        dp[0]=m[0][0];
        for(int i=1;i<less;i++)
        {
            dp[i]=dp[i-1]+(rowmore?m[0][i]:m[i][0]);
        }
        for(int i=1;i<more;i++)
        {
            dp[0]=dp[0]+(rowmore?m[i][0]:m[0][i]);
            for(int j=1;j<less;j++)
            {
                dp[j]=Math.min(dp[j],dp[j-1])+(rowmore?m[i][j]:m[j][i]);
            }
        }
        return dp[less-1];
    }

    public static void main(String[] args)
    {
        int[][] m={{1,3,5,9},
                {8,1,3,4},
                {5,0,6,1},
                {8,8,4,0}};

        MinPathSum minPathSum=new MinPathSum();

        System.out.println(minPathSum.minPathSum1(m));
        System.out.println(minPathSum.minPathSum2(m));
    }
}
