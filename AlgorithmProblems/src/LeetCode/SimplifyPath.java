package LeetCode;

import java.util.Stack;

/**
 * 71. Simplify Path
 *
 * Given an absolute path for a file (Unix-style), simplify it.
 *
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * path = "/a/../../b/../c//.//", => "/c"
 * path = "/a//b////c/d//././/..", => "/a/b/c"
 *
 * In a UNIX-style file system, a period ('.') refers to the current directory, so it can be ignored in a simplified path. Additionally, a double period ("..") moves up a directory, so it cancels out whatever the last directory was. For more information, look here: https://en.wikipedia.org/wiki/Path_(computing)#Unix_style
 *
 * Corner Cases:
 *
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 *
 * https://leetcode.com/problems/simplify-path/
 *
 * Created by ZingBug on 2018/11/15.
 */
public class SimplifyPath {

    public String simplifyPath(String path)
    {
        if(path==null)
        {
            return null;
        }
        Stack<String> stack=new Stack<>();
        int len=path.length();
        String[] list=path.split("/");
        for(String s:list)
        {
            if(s.equals(".")||s.length()==0)
            {
                continue;
            }
            else if(s.equals(".."))
            {
                if(!stack.isEmpty())
                {
                    stack.pop();
                }
            }
            else
            {
                stack.push(s);
            }
        }
        StringBuilder sb=new StringBuilder();
        while (!stack.isEmpty())
        {
            sb.insert(0,"/"+stack.pop());
        }
        if(sb.length()==0)
        {
            sb.append("/");
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        SimplifyPath s=new SimplifyPath();

        String path1="/home/";
        String path2="/a/./b/../../c/";
        String path3="/a/../../b/../c//.//";
        String path4="/a//b////c/d//././/..";

        System.out.println(s.simplifyPath(path1));
        System.out.println(s.simplifyPath(path2));
        System.out.println(s.simplifyPath(path3));
        System.out.println(s.simplifyPath(path4));


    }
}
