package Coding_Interviews;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 矩阵中的路径
 * 题目描述
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 *
 * https://www.nowcoder.com/practice/c61c6999eecb4b8f88a98f66b273a3cc?tpId=13&tqId=11218&tPage=4&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 用回溯法来做，就是试探法，按选优条件向前搜索，以达到目标。但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法，
 * 而满足回溯条件的某个状态的点称为“回溯点”。
 * Created by ZingBug on 2018/7/13.
 */
public class PathInMatrix {

    public boolean hasPath(char[] matrix,int rows,int cols,char[] str)
    {
        if(str==null||str.length==0)
        {
            return false;
        }
        int len1=matrix.length;
        int len2=str.length;
        char[][] chars=new char[rows][cols];
        ArrayList<Integer> list=new ArrayList<>();

        //第一步，先做成二维矩阵，并找起点
        for(int i=0;i<len1;i++)
        {
            chars[i/cols][i%cols]=matrix[i];
            if(matrix[i]==str[0])
            {
                list.add(i);
            }
        }
        //开始搜寻
        for(int index:list)
        {
            int x=index/cols;
            int y=index%cols;
            HashSet<Integer> set=new HashSet<>();
            boolean result=path(chars,rows,cols,x,y,str,0,set);
            if(result)
            {
                return true;
            }
        }

        return false;
    }

    private boolean path(char[][] matrix,int rows,int cols,int x,int y,char[] str,int index,HashSet set)//index1表示当前搜索矩阵的点，index2表示当前改到str的点
    {
        if(!hasIndex(x*cols+y,matrix.length*cols)||!(x>=0&&x<rows&&y>=0&&y<cols))//判断位置合法性
        {
            //检查当前下标是否准确
            return false;
        }
        if(!set.contains(x*cols+y)&&matrix[x][y]==str[index])
        {
            set.add(x*cols+y);
            //检查此路径是否之前走过
            if(index==str.length-1)//路径走完
            {
                return true;
            }
            //最多有四个候选点
            boolean result=path(matrix,rows,cols,x,y-1,str,index+1,set)
                    ||path(matrix,rows,cols,x-1,y,str,index+1,set)
                    ||path(matrix,rows,cols,x,y+1,str,index+1,set)
                    ||path(matrix,rows,cols,x+1,y,str,index+1,set);
            set.remove(x*cols+y);
            return result;
        }
        return false;
    }
    private boolean hasIndex(int index,int len)
    {
        return index<len&&index>=0;
    }

    public static void main(String[] args)
    {
        char[] matrix1={'a','b','c','e','s','f','c','s','a','d','e','e'};
        char[] str1={'b','c','c','e','d'};
        char[] str2={'s','e','e'};
        char[] matrix2="ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray();
        char[] str3="SGGFIECVAASABCEHJIGQEM".toCharArray();
        PathInMatrix p=new PathInMatrix();
        System.out.println(p.hasPath(matrix2,5,8,str3));
    }
}
