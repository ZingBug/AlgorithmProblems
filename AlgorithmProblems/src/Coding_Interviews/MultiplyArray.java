package Coding_Interviews;

/**
 * 构建乘积数组
 * 题目描述
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 *
 * https://www.nowcoder.com/practice/94a4d381a68b47b7a8bed86f2975db46?tpId=13&tqId=11204&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * Created by ZingBug on 2018/7/7.
 */
public class MultiplyArray {

    public int[] multiply(int[] A)
    {
        //最简单的方法就是每一次计算都要将A中除第i个元素外的所有元素相乘，这种时间复杂度为O(n)，不推荐
        //而现在方法是使用一个数组D，用以保存保存元素组某位置，例如i,保存i前的所以元素的乘积
        //另外一个数组C，保存i后所有元素的乘积
        //那么最后，B=D*C
        if(A==null||A.length<1)
        {
            return null;
        }
        int len=A.length;
        int[] B=new int[len];
        int[] C=new int[len];
        int[] D=new int[len];
        //计算D
        D[0]=1;
        for(int i=1;i<len;i++)
        {
            D[i]=D[i-1]*A[i-1];
        }
        //计算C  C和D的计算可以放在一个循环中
        C[len-1]=1;
        for(int i=len-1;i>0;i--)
        {
            C[i-1]=C[i]*A[i];
        }
        //计算B
        for(int i=0;i<len;i++)
        {
            B[i]=D[i]*C[i];
        }
        return B;
    }

    public static void main(String[] args)
    {
        MultiplyArray m=new MultiplyArray();
        int[] A={1,2,3,4};
        int[] B=m.multiply(A);
        for(int val:B)
        {
            System.out.print(val+" ");
        }
    }
}
