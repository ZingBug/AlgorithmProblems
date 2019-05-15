package Coding_Interview_Guide.RecurAndDP;

/**
 * 斐波那契数列
 * Page181
 * Created by ZingBug on 2018/12/24.
 */
public class Fibonacci {

    private int f1(int n)//用递归方法，时间复杂度为O(N*N)
    {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    private int f2(int n)//用循环来代替递归方法，时间复杂度为O(N)
    {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (; n > 2; n--) {
            tmp = res;
            res += pre;
            pre = tmp;
        }
        return res;
    }

    private int f3(int n)//用矩阵快速相乘方法，时间复杂度为O(logN)
    {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base={{1,1},{1,0}};
        int[][] res=matrixPower(base,n-2);
        return res[0][0]+res[1][0];
    }

    private int[][] matrixPower(int[][] m,int p)
    {
        //先创建一个单位矩阵
        int[][] res=new int[m.length][m[0].length];
        for(int i=0;i<m.length;i++)
        {
            res[i][i]=1;
        }
        int [][] tmp=m;
        for(;p!=0;p>>=1)
        {
            if((p&1)!=0)
            {
                res=muliMatrix(res,tmp);
            }
            tmp=muliMatrix(tmp,tmp);
        }
        return res;
    }

    private int[][] muliMatrix(int[][] m1,int[][] m2)
    {
        int[][] res=new int[m1.length][m1[0].length];
        for(int i=0;i<m1.length;i++)
        {
            for(int j=0;j<res[0].length;j++)
            {
                for(int k=0;k<m2.length;k++)
                {
                    res[i][j]+=(m1[i][k]*m2[k][j]);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();

        System.out.println("1: " + f.f1(10));
        System.out.println("2: " + f.f2(10));
        System.out.println("3: " + f.f3(10));
    }
}
