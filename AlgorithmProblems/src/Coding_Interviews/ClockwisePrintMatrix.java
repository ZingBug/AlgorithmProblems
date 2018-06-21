package Coding_Interviews;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 * 题目描述
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * https://www.nowcoder.com/practice/9b4c81a02cd34f76be2659fa0d54342a?tpId=13&tqId=11172&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 每次输出一圈，需要四步，第一步从左到右打印一行，第二步从上到下打印一列，第三步从右到左打印一行，第四步从下到上打印一列。
 * 每一步我们根据起始坐标和终止坐标用一个循环就能打印出一行或者一列。
 * 需要注意的是最后一圈有可能退化成只有一行、只有一列，甚至只有一个数字，因此打印这样的一圈就不再需要四步。这时候就要添加限制条件。
 *
 * Created by ZingBug on 2018/6/20.
 */
public class ClockwisePrintMatrix {
    public ArrayList<Integer> printMatrix(int[][] matrix)
    {
        ArrayList<Integer> list=new ArrayList<>();
        if(matrix==null)
        {
            return list;
        }
        int row=matrix.length;//矩阵的行
        int col=matrix[0].length;//矩阵的列

        int i=0;//起始行
        int j=0;//起始列
        while (i<row&&j<col)
        {
            for(int k=j;k<col;k++)
            {
                list.add(matrix[i][k]);
            }

            for(int k=i+1;k<row;k++)
            {
                list.add(matrix[k][col-1]);
            }
            if(i>=row-1)
            {
                break;
            }
            for(int k=col-2;k>=j;k--)
            {
                list.add(matrix[row-1][k]);
            }
            if(j>=col-1)
            {
                break;
            }
            for(int k=row-2;k>i;k--)
            {
                list.add(matrix[k][j]);
            }
            j++;
            i++;
            col--;
            row--;
        }

        return list;
    }
    public static void main(String[] args)
    {
        //测试样例
        int[][] matrix1={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[][] matrix2={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[][] matrix3={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16},{17,18,19,20}};
        int[][] matrix4={{1},{2},{3},{4},{5},{6}};
        int[][] matrix5={{1,2,3,4,5}};
        int[][] matrix6={{1,2},{3,4},{5,6},{7,8},{9,10}};
        ClockwisePrintMatrix c=new ClockwisePrintMatrix();
        ArrayList<Integer> list=c.printMatrix(matrix2);
        System.out.println("Size: "+list.size());
        for(int val:list)
        {
            System.out.print(val+" ");
        }
    }
}
