package Coding_Interviews;

/**
 * 替换空格
 * 题目描述
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 *
 * https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class ReplaceSpace {
    public String replaceSpace(StringBuilder str)
    {
        String s="%20";
        char[] cs=str.toString().toCharArray();
        int off=0;
        for(int i=0;i<cs.length;i++)
        {
            if(cs[i]==' ')
            {
                str.replace(i+off,i+off+1,s);
                off+=2;
            }
        }
        return str.toString();
    }
    public static void main(String[] args)
    {
        ReplaceSpace r=new ReplaceSpace();
        StringBuilder stringBuilder=new StringBuilder("We Are Happy");
        System.out.println(r.replaceSpace(stringBuilder));
    }
}
