package Algorithm;

import java.util.ArrayList;

public class DiskDispatch {

    /** * start为磁道开始点 * track为将要访问的磁道号数组 * distanceSum移动距离总磁道数 * movdistan为将要计算的移动距离 */
    protected ArrayList<Integer> track;
    public int distanceSum = 0;
    protected int[] movdistan;
    /** * 计算距离函数通过三元运算符返回两数绝对值 */
    protected float distance(float a, float b) {
        return a > b ? a - b : b - a;
    }
    /** * 定义内部类 磁道类 包含磁道号 要想访问须达到的距离 */
    static class Track {
        int diskName;
        float distance;
    }
}
