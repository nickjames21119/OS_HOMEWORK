/**
 * 1. SSTF算法
 * 2. shortest()函数计算移动距离及磁道号
 * 3. 及时记录调度序列
 * 4. 迭代方法赋值startpos，一次求出下一个调度的磁道
 */
package Algorithm;

import java.util.ArrayList;
public class SSTF extends DiskDispatch {
    /** * trackSequence为SSTF调度的磁道号数组 * len 为传出的磁道号数目 */
    public int[] trackSequence;
    public int startpos;                    //起始位置
    public float acrosstsstf = 0;           //跨越磁道用时
    public float start;                     //ok启动时间 单位 ms
    public float rspeed = 0;                //ok磁盘转速 单位r/s
    public int num_disk = 0;                //扇区数
    public int numbyte = 0;                 //每扇区（块）字节数
    public int byteread;                    //读取总字节数
    public float FindTrack = 0;             //寻道时间
    public float Ttrans;                    //传输时间
    public float rotatedelay;               //延迟时间
    public float alltrans;                  //总共时间
    // 构造器
    public SSTF(int start, ArrayList<Integer> track) {                    //SSTF初始化
        this.track = track;                                               //请求序列初始化
        this.movdistan = new int[track.size()];                           //计算相隔距离
        this.trackSequence = new int[track.size()];                       //计算磁头移动量
        this.startpos = start;                                            //随机生成磁头初始位置(Panel_TimeSet初始化)
    }

    /** * 调度执行函数，进行此次最短寻道时间优先磁盘调度 */
    public void run() {
        float needle = startpos;                                         //初始化磁针位置
        for (int i = 0; i < movdistan.length; i++) {
            Track tc = shortest((int) needle, track);                    //求出移动距离的磁道号以及移动距离
            this.trackSequence[i] = tc.diskName;                         //将算出的将要访问的下一磁道号、移动距离加入对应数组
            movdistan[i] = (int) tc.distance;                            //记录相邻调度磁头移动距离
            distanceSum += movdistan[i];                                 //计算磁头移动总距离
            needle = tc.diskName;                                        //更新指针位置以及磁道号列表，去除已经访问的磁道号
            track.remove(Integer.valueOf(tc.diskName));                  //此处使用包装类包装，避免当成索引
        }

        this.FindTrack = distanceSum*this.acrosstsstf+this.start;                          //计算寻道时间
        this.Ttrans = (float) this.byteread/(this.rspeed*this.numbyte);                    //计算传输时间
        this.rotatedelay = (float) (0.5/this.rspeed);                                      //计算旋转延迟时间
        this.alltrans = (float) (this.FindTrack+this.Ttrans+0.5/this.rspeed+rotatedelay);  //计算总时间
    }

    /**
     * 在给定范围内找出里磁针最近的磁道号
     * @param needle 磁针当前位置
     * @param array 访问磁道号数组,即查找范围
     * @return 查找到的要访问的磁道号
     */
    public Track shortest(int needle, ArrayList<Integer> array) {
        Track tc = new Track();                            //各变量初始化 先默认第一个是距离最近的磁道
        tc.diskName = array.get(0);
        tc.distance = distance(needle, array.get(0));
        for (int item : array) {                           //进过遍历，若发现有离得更近的就替换
            if (distance(needle, item) < tc.distance) {
                tc.diskName = item;
                tc.distance = distance(needle, item);
            }
        }
        return tc;
    }
}
