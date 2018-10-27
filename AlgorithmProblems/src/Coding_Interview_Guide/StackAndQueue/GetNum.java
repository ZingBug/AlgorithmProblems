package Coding_Interview_Guide.StackAndQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * 最大值减去最小值小于或等于num的子数组数量
 * Page31
 * Created by ZingBug on 2018/10/19.
 */
public class GetNum {
    private int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                while (!qmax.isEmpty() && arr[j] >= arr[qmax.peekLast()]) {
                    qmax.pollLast();
                }
                qmax.addLast(j);
                while (!qmin.isEmpty() && arr[j] <= arr[qmin.peekLast()]) {
                    qmin.pollLast();
                }
                qmin.addLast(j);
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                j++;
            }
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            res += j - i;
            i++;
        }
        return res;
    }

    public static void main(String args[]) {
        GetNum g = new GetNum();
        int[] arr = {5, 3, 4, 6, 1, 8};
        System.out.println(g.getNum(arr, 3));
    }

}
