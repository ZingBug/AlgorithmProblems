package Coding_Interviews;

/**
 * 二维数组的查找
 * 题目描述
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class FindingInTwoDimensionalArray {
    public boolean Find(int target,int[][] array)
    {
        if(array.length==0)
        {
            return false;
        }
        int row=array.length;
        int col=array[0].length;
        int i=0;
        int j=col-1;
        while (i<row&&j>=0)
        {
            if(array[i][j]==target)
            {
                return true;
            }
            else if(array[i][j]>target)
            {
                j--;
            }
            else if(array[i][j]<target)
            {
                i++;
            }
        }
        return false;
    }
    public static void main(String[] args)
    {
        int[][] array={{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int target=7;
        FindingInTwoDimensionalArray f=new FindingInTwoDimensionalArray();
        System.out.println(f.Find(target,array));
    }
}
