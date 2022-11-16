package Algorithm;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class Look extends Thread {
    public int startpos;                //初始位置
    public String direction;            //初始方向
    private int count;                  //线程数量
    public int nbytes = 0;              //每扇区所占字节数
    public int sum = 0;                 //磁臂移动量
    public int numsector = 0;           //扇区数
    public float acrosstimelook;        // 跨越一个磁道所用的时间 单位 ms
    public float start = 0;             //启动时间 单位 ms
    public float rspeed = 0;            //磁盘转速 单位r/s
    public int byteread;                //总共读取字节数
    public float FindTrack;             //寻道时间
    public float Ttrans;                //传输时间
    public float rotatedelay;           //延迟时间
    public float alltrans;              //总共时间
    public int[] tracks;                //请求序列
    public int[] out;                   //调度序列
    public Look(int[] track) throws InterruptedException {                         //初始化初始位置及方向
        this.tracks = track;                                                       //初始化请求序列
        this.startpos = ThreadLocalRandom.current().nextInt(0,200);   //初始化磁头初始位置
        int way = ThreadLocalRandom.current().nextInt(0,2);           //初始化磁臂初始移动方向
        if(way == 0) this.direction = "right";
        else this.direction = "left";
        out = new int[tracks.length];

        List<LookRunnable> threadList = new LinkedList<>();                        //磁盘调度序列
        for (int trackk : tracks) {                                                //初始化调度请求线程
            LookRunnable lookRunnable = new LookRunnable(trackk);
            threadList.add(lookRunnable);
        }
        threadList.sort(Comparator.comparingInt(o -> o.track));                    //调度请求线程排序
        try {
            this.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int ttt = threadList.size()-1;
        while (this.count != threadList.size()) {
            while (this.direction.equals("right")) {                              //磁臂初始移动方向向右
                for (LookRunnable m : threadList) {
                    if (m.track >= this.startpos && m.key) {
                        count++;
                        if( ttt!= threadList.size()-1){
                            int distance = abs(m.track - this.startpos);
                            sum += distance;
                        }
                        out[ttt] = m.track;                                      //输出序列赋值
                        ttt--;
                        this.startpos = m.track;                                 //迭代
                        m.key = false;
                        m.run();
                    }
                    this.direction = "left";
                }

            }
            while (this.direction.equals("left")) {                               //磁臂初始移动方向向左
                for (int i = threadList.size() - 1; i >= 0; i--) {
                    if (threadList.get(i).track > this.startpos || !threadList.get(i).key) {
                        continue;
                    }
                    //此时表示找到了比现在下标小的
                    this.count++;
                    if( ttt!= threadList.size()-1){
                        int distance = abs(threadList.get(i).track - this.startpos);
                        sum += distance;
                    }
                    out[ttt] = threadList.get(i).track;                          //输出序列赋值
                    ttt--;
                    this.startpos = threadList.get(i).track;                     //迭代
                    threadList.get(i).key = false;
                    threadList.get(i).run();
                }
                this.direction = "right";
            }
        }
    }
    public void cal(){
        this.FindTrack = this.sum*this.acrosstimelook+this.start;
        this.Ttrans = (float) this.byteread/(this.rspeed*this.nbytes);
        this.rotatedelay = (float) (0.5/this.rspeed);
        this.alltrans = (float) (this.FindTrack+this.Ttrans+0.5/this.rspeed+rotatedelay);
    }
    public void run() {}
}
