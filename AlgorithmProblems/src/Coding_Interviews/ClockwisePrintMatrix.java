package Coding_Interviews;

import java.util.ArrayList;

/**
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

        int i=0;
        int j=col-1;


        return list;
    }
    public static void main(String[] args)
    {
        int[][] matrix={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        ClockwisePrintMatrix c=new ClockwisePrintMatrix();
        c.printMatrix(matrix);
    }
}
