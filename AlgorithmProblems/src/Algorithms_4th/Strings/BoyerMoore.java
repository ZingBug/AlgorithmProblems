package Algorithms_4th.Strings;

/**
 * Boyer-Moore字符串匹配算法（启发式地处理不匹配的字符）
 * Created by ZingBug on 2017/10/25.
 */
public class BoyerMoore {
    private int[] right;
    private String pat;
    public BoyerMoore(String pat)
    {
        //计算跳跃表
        this.pat=pat;
        int M=pat.length();
        int R=256;
        right=new int[R];
        for(int c=0;c<R;c++)
        {
            right[c]=-1;//不包含在模式字符串中的字符的值为-1
        }
        for(int j=0;j<M;j++)
        {
            right[pat.charAt(j)]=j;//包含在模式字符串中的字符的值为它在其中出现的最右位置
        }
    }
    public int search(String txt)
    {
        //在txt中查找模式字符串
        int N=txt.length();
        int M=pat.length();
        int skip;
        for(int i=0;i<N-M;i+=skip)
        {
            skip=0;
            for(int j=M-1;j>=0;j--)
            {
                if(pat.charAt(j)!=txt.charAt(i+j))
                {
                    skip=j-right[txt.charAt(i+j)];//匹配失败字符不包含在模式字符串中时，相当于skip=j+1;
                    //匹配失败字符串包含在模式字符串中时，需要将模式字符串和文本对齐
                    if(skip<1)
                    {
                        skip=1;
                    }
                    break;
                }

            }
            if(skip==0)
            {
                return i;//找到匹配
            }
        }
        return N;//未找到匹配
    }
    public static void main(String[] args)
    {
        String pat="ADF";
        String txt="ADAFDADFEWE";
        BoyerMoore boyerMoore=new BoyerMoore(pat);
        System.out.println("text:   "+txt);
        int offset=boyerMoore.search(txt);
        System.out.print("pattern:");
        for(int i=0;i<offset;i++)
        {
            System.out.print(" ");
        }
        System.out.println(pat);
    }
}
