package Algorithm;

import static Algorithm.FCFS.semaphore_cur;
import static Algorithm.FCFS.semaphore_que;
/**
 * 自定义线程
 * 1. 保存线程编号
 * 2. 保存调度磁盘
 * 3. 调度过程
 *    1> 请求调度， 判断信号量是否有可用许可
 *    2> 得到调度， 占有一个信号量
 *    3> 完成调度， 释放一个信号量
 */

public class MyThread extends Thread{
    private final int index;                                      //线程索引
    private final int disk;                                       //对应磁盘号
    MyThread(int i, int d){                                       //当前线程初始化
        this.index = i;
        this.disk = d;
    }

    public void run(){
        synchronized (this){                                      //线程加锁
            try {
                System.out.println("线程 "+this.index+" 请求调度磁盘 " + this.disk);
                semaphore_cur.acquire();                          //队列信号量占用
                try{
                    this.notify();
                    semaphore_que.acquire();                      //当前互斥量占用
                    System.out.println("线程 "+this.index+" 启动");
                    sleep(100);
                    System.out.println("线程 "+this.index+" 调度完成");
                    semaphore_cur.release();                      //释放互斥量
                    semaphore_que.release();                      //释放一个队列信号量
                }
                catch (InterruptedException e){
                    System.out.println("当前存在进行中调度，调度阻塞");
                    this.wait();                                  //线程阻塞
                    throw new RuntimeException(e);
                }
            } catch (InterruptedException e) {
                System.out.println("当前调度请求过多，调度阻塞");
                try {
                    this.wait();                                  //线程阻塞
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }
        }
    }
}
