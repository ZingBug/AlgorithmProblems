package Coding_Interview_Guide.StackAndQueue;

import java.util.LinkedList;

/**
 * 生成窗口最大值数组
 * <p>
 * 输入：整形数组arr，窗口大小为w。
 * 输出：一个长度为n-w+1的数组res，res[i]表示每个窗口状态下的最大值
 * <p>
 * 解题思路：
 * 利用双端队列来说实现窗口最大值的更新。
 * Page20
 * <p>
 * Created by ZingBug on 2018/10/9.
 */
public class GetMaxWindow {
    private int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GetMaxWindow g = new GetMaxWindow();
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = g.getMaxWindow(arr, 3);
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
