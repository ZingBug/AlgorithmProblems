package Coding_Interviews;

import java.util.HashSet;

/**
 * 机器人的运动范围
 * 题目描述
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 *
 * https://www.nowcoder.com/practice/6e5207314b5241fb83f2329e89fdecc8?tpId=13&tqId=11219&tPage=4&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 回溯法
 *
 * Created by ZingBug on 2018/7/13.
 */
public class MovingCount {
    public int movingCount(int threshold,int rows,int cols)
    {
        HashSet<Integer> set=new HashSet<>();
        moving(threshold,rows,cols,0,0,set);
        return set.size();
    }

    private void moving(int threshold, int rows, int cols, int row, int col, HashSet<Integer> set)
    {
        //先判断格子坐标是否在矩形内
        if(!(row>=0&&row<rows&&col>=0&&col<cols))
        {
            return;
        }
        //然后判断格子坐标是否超过限制
        if(!isPassable(row,col,threshold))
        {
            return;
        }
        int index=row*cols+col;
        //最后判断是否之前到达过这个格子
        if(set.contains(index))
        {
            return;
        }
        else
        {
            set.add(index);
            //上下左右四个格子
            moving(threshold,rows,cols,row-1,col,set);
            moving(threshold,rows,cols,row,col-1,set);
            moving(threshold,rows,cols,row+1,col,set);
            moving(threshold,rows,cols,row,col+1,set);
        }
    }

    private boolean isPassable(int row,int col,int threshold)
    {
        int count=0;
        while (row!=0)
        {
            count+=row%10;
            row=row/10;
        }
        while (col!=0)
        {
            count+=col%10;
            col=col/10;
        }
        return count<=threshold;
    }

    public static void main(String[] args)
    {
        MovingCount m=new MovingCount();
        System.out.println(m.isPassable(35,38,18));
    }
}
