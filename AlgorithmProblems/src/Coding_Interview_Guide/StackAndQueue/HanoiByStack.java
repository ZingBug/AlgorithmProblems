package Coding_Interview_Guide.StackAndQueue;

import java.util.Stack;

/**
 * 通过栈来解决汉诺塔问题，用栈来模拟整个过程
 * <p>
 * 解题思路
 * Page16
 * <p>
 * Created by ZingBug on 2018/10/8.
 */
public class HanoiByStack {
    private enum Action {
        No, LToM, MToL, RToM, MToR
    }

    private int hanoiProblem(int num, String left, String mid, String right) {
        Stack<Integer> lS = new Stack<>();
        Stack<Integer> mS = new Stack<>();
        Stack<Integer> rS = new Stack<>();

        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);

        for (int i = num; i > 0; i--) {
            lS.push(i);
        }

        Action[] record = {Action.No};

        int step = 0;

        while (rS.size() != num + 1) {
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;

    }

    private int fStackTotStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack, String from, String to) {
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        HanoiByStack h = new HanoiByStack();
        int num = 2;
        int steps = h.hanoiProblem(num, "left", "mid", "right");
        System.out.println("It will move " + steps + " steps");
    }
}
