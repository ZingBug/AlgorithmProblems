package Coding_Interview_Guide.StackAndQueue;

import java.util.Stack;

/**
 * 求最大子矩阵的大小
 * 给定一个整形矩阵map，其中的值只有0和1，求其中全是1的所有矩形区域中，最大的矩形区域为1的数量。
 * 例如：
 * 1 1 1 0
 * 其中，最大的矩形区域有3个1，所以返回3
 * 例如：
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0
 * 其中，最大的矩形区域有6个1，所以返回6
 * Page26
 * <p>
 * Created by ZingBug on 2018/10/18.
 */
public class GetMaxRecSize {
    private int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxArea, maxRecBottomSize(height));
        }
        return maxArea;
    }

    private int maxRecBottomSize(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, (i - k - 1) * height[j]);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int i = height.length;
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea, (i - k - 1) * height[j]);
        }
        return maxArea;
    }

    public static void main(String[] args) {

        int[][] map = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        GetMaxRecSize g = new GetMaxRecSize();
        System.out.println(g.maxRecSize(map));
    }
}
