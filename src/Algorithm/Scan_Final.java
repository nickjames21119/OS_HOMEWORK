package Algorithm;

import java.util.concurrent.ThreadLocalRandom;
public class Scan_Final {
    public int startpos;                  //初始位置
    public int startposf;                 //真正初始位置
    public String Direction;              //初始方向
    public float rspeed = 0;              //磁盘转速
    public int sum;                       //磁臂移动量
    public int num_disk = 0;              //扇区数
    public float acrosstimelook;          //跨越一个磁道所用的时间 单位 ms
    public float start = 0;               //启动时间 单位 ms
    public int byteread;                  //总共读取字节数
    public float FindTrack;               //寻道时间
    public float Ttrans;                  //传输时间
    public float rotatedelay;             //延迟时间
    public float alltrans;                //总共时间
    public int numbyte = 0;               //每扇区/块 字节数
    public int[] out;                     //磁盘调度队列
    public Scan_Final(int[] array, int m){
        this.startpos = ThreadLocalRandom.current().nextInt(0, 200);  //随机生成磁头初始位置
        this.startposf = this.startpos;
        int tt = ThreadLocalRandom.current().nextInt(0, 2);           //随机生成初始移动方向
        if(tt == 0) this.Direction = "left";
        else this.Direction = "right";
        int i,j;
        int temp;
        this.sum=0;
        for ( j = 0; j < array.length; j++) {                                      //对请求序列进行插入排序
            for(i=j+1;i<array.length;i++){
                if(array[j]>array[i]){
                    temp=array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }
        //三种情况磁臂移动量及调度序列讨论
        if(this.startpos<array[0]){                                   //随机初始坐标小于最小值
            if(this.Direction.equals("right")){                       //向右
                sum = 199-this.startpos;
            }
            else{                                                     //向左
                sum = array[array.length-1]+this.startpos;
            }
        }
        else if(this.startpos>array[array.length-1]){                 //随机初始坐标大于坐标值
            if(this.Direction.equals("left")){                        //向左
                sum = this.startpos-array[0];
            }
            else{                                                     //向右
                sum = 398-this.startpos-array[0];
            }
        }
        else{                                                         //随机初始坐标在中间
            if(this.Direction.equals("left")){                        //向左
                sum = this.startpos+array[array.length-1];
            }
            else{                                                     //向右
                sum = 398-array[0]-this.startpos;
            }
        }
        out = new int[array.length+1];
        if(array[array.length-1]<this.startpos){                     //如果当前磁道号大于所有磁道号
            int t = 0;
            for(i=m-1;i>=0;i--){
                out[t] = array[i];
                t++;
                System.out.print(array[i]+" ");
                this.startpos=array[i];
            }
        }
        else{
            if(array[0]>this.startpos) {                            //小于所有磁道号
                for(i=0;i<m;i++){
                    out[i] = array[i];
                    System.out.print(array[i]+" ");
                    this.startpos=array[i];
                }
            }
            else{                                                   //当前磁道号在磁道号之间
                int count=0;
                int temp1;
                int[] array1 =new int[m+1];                         //创建新的数组来储存包括当前磁道号的值
                if (m >= 0) System.arraycopy(array, 0, array1, 0, m);
                array1[m]=this.startpos;
                for (int n = 0; n < array1.length-1; n++) {         //冒泡排序所有磁道号
                    for (int n2 =n+1; n2 < array1.length; n2++) {
                        if(array1[n]>array1[n2]){
                            temp1=array1[n];
                            array1[n]=array1[n2];
                            array1[n2]=temp1;
                        }
                    }
                }
                for(int t=0;t<array1.length;t++){
                    if(array1[t]==this.startpos){                 //查找排序好的当前磁道号所在位置
                        count=t;
                    }
                }
                int ttt;
                switch (tt) {//选择顺序0为往小的走，1为大的走
                    case 0:
                        ttt = 0;
                        for(i=count-1;i>=0;i--){
                            out[ttt] = array[i];
                            ttt++;
                            System.out.print(array1[i]+" ");      //遍历比当前磁道号小的的磁道
                            this.startpos=array1[i];
                        }
                        for(i=count+1;i<array1.length;i++){       //遍历当前比磁道号大的磁道
                            out[ttt] = array1[i];
                            ttt++;
                            System.out.print(array1[i]+" ");
                            this.startpos=array1[i];
                        }
                        break;
                    case 1:
                        ttt = 0;
                        for(i=count+1;i<array1.length;i++){       //比磁道号大的磁道
                            out[ttt] = array1[i];
                            ttt++;
                            System.out.print(array1[i]+" ");
                            this.startpos=array1[i];
                        }
                        for(i=count-1;i>=0;i--){                 //磁道号小的磁道
                            out[ttt] = array1[i];
                            ttt++;
                            System.out.print(array1[i]+" ");
                            this.startpos=array1[i];
                        }
                        break;
                }
                out = array1;
            }}
    }
    public void cal(){
        this.FindTrack = this.sum*this.acrosstimelook+this.start;
        this.Ttrans = (float) this.byteread/(this.rspeed*this.numbyte);
        this.rotatedelay = (float) (0.5/this.rspeed);
        this.alltrans = (float) (this.FindTrack+this.Ttrans+0.5/this.rspeed+rotatedelay);
    }
}
