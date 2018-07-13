package Coding_Interviews;


/**
 * 序列化二叉树
 * 题目描述
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * https://www.nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84?tpId=13&tqId=11214&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 我们根据前序遍历的顺序来序列化二叉树。在遍历二叉树碰到null时，就这些null序列化为一个特殊的字符('$')，另外，节点的数值之间要用一个特殊字符(',')隔开
 *
 * Created by ZingBug on 2018/7/11.
 */
public class SerializeTree {

    public String Serialize(TreeNode root)
    {
        StringBuilder sb=new StringBuilder();
        serialize(root,sb);
        return sb.toString();
    }
    private void serialize(TreeNode root,StringBuilder sb)
    {
        if(root==null)
        {
            sb.append('$');
            sb.append(',');
            return;
        }
        sb.append(root.val);
        sb.append(',');
        serialize(root.left,sb);
        serialize(root.right,sb);
    }

    int index=0;
    public TreeNode Deserialize(String str)
    {
        String[] strings=str.split(",");

        TreeNode root=deserialize(strings);

        return root;
    }
    private TreeNode deserialize(String[] strings)
    {
        if(strings[index].equals("$"))
        {
            index++;
            return null;
        }
        int val=Integer.parseInt(strings[index]);
        TreeNode node=new TreeNode(val);
        index++;
        node.left=deserialize(strings);
        node.right=deserialize(strings);

        return node;
    }

    public static void main(String[] args)
    {
        /**
         * root1
         *      1
         *    2   3
         *  4  5 6  7
         */
        TreeNode root1=new TreeNode(1);
        root1.left=new TreeNode(2);
        root1.right=new TreeNode(3);
        root1.left.left=new TreeNode(4);
        root1.left.right=new TreeNode(5);
        root1.right.left=new TreeNode(6);
        root1.right.right=new TreeNode(7);

        SerializeTree s=new SerializeTree();
        String str=s.Serialize(root1);
        System.out.println(str);

        TreeNode node=s.Deserialize(str);
        System.out.println();
    }
}
