/**
 * FCFS算法
 * ***请求时刻为真实输入序列时刻
 * 1. 私有成员线程数量
 * 2. 私有成员磁盘请求队列
 * 3. 构造函数提示函数启动
 * 4. FCFS函数
 */
//FCFS调度算法完成
//FCFS相关指标计算完成
package Algorithm;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.abs;

public class FCFS {
    public int path = 0;
    static Semaphore semaphore_que;               //队列信号量
    static Semaphore semaphore_cur;               //判断是否能执行
    public int requireNum;                        //调度数量
    public float acrosstimefcfs = 1;              //跨越一个磁道所用的时间
    public float start;                           //启动时间
    public float rspeed = 0;                      //磁盘转速
    public int num_disk = 0;                      //扇区数
    public int numbyte = 0;                       //每扇区（块）字节数
    public float FindTrack = 0;                   //寻道时间
    public int byteread;                          //读取字节数
    public float Ttrans;                          //传输时间
    public float alltrans;                        //总时间
    public float rotatedelay;                     //磁臂旋转延迟时间
    public int[] haveto;                          //磁盘调度序列
    public int startpos;                          //磁头初始位置
    ArrayList<Integer> DiskList;
    public FCFS(int n){
        this.requireNum = n;                            //需要调度线程数
        //线程调度队列
        DiskList = new ArrayList<>();
        this.startpos = ThreadLocalRandom.current().nextInt(0,200);   //磁头初始位置初始化
        //算法开始时间
        this.start = System.currentTimeMillis();        //开始时间
        semaphore_que = new Semaphore(n, true);    //总共信号量
        semaphore_cur = new Semaphore(1);       //互斥信号量
        System.out.println("FCFS算法启动！");
    }

    public void FcfsAlg() throws InterruptedException {
        System.out.println("请输入请求调度的磁盘: ");
        Scanner scanner = new Scanner(System.in);
        for(int i = 0;i<this.requireNum;i++){
            int t = scanner.nextInt();
            this.DiskList.add(t);
        }
        System.out.println("磁头初始位置为："+ startpos);
        haveto = new int[this.requireNum];
        for(int i = 0;i<this.requireNum;i++){
            MyThread myThread = new MyThread(i, this.DiskList.get(i));
            myThread.start();
            myThread.join();
            haveto[i] = this.DiskList.get(i);
            if(i == 0)
                path += abs(this.startpos-haveto[0]);
            else
                path += abs(haveto[i]-haveto[i-1]);
        }
        this.FindTrack = this.path*this.acrosstimefcfs+this.start;
        this.Ttrans = (float) this.byteread/(this.rspeed*this.numbyte);
        this.rotatedelay = (float) (0.5/this.rspeed);
        this.alltrans = (float) (this.FindTrack+this.Ttrans+0.5/this.rspeed+rotatedelay);
    }
}
