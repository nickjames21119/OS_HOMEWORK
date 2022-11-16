/**
 * Panel_TimeSet
 * 选择算法，设置算法运行各个参数
 * 加入监听器，将参数传入各个算法
 */
package gragh;

import Algorithm.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class Panel_TimeSet extends JFrame implements DocumentListener, KeyListener, ActionListener {
    JPanel Across;                                //跨越时间标签
    JPanel StarTime;                              //开始时间标签
    JPanel DiskSpeed;                             //转速标签
    JPanel NumSectors;                            //每磁道扇区数
    JPanel SectorBytes;                           //每扇区字节数
    JPanel ByteRead;                              //读取字节数面板
    JPanel AlgChoose;                             //算法选择面板
    JLabel Choose;                                //算法选择标签
    JCheckBox FCFSChoose;                         //FCFS选择
    JCheckBox SSTFChoose;                         //SSTF选择
    JCheckBox SCANChoose;                         //SCAN选择
    JCheckBox LOOKChoose;                         //LOOK选择
    JLabel AcrossL;                               //跨越磁道用时
    JLabel StarTimeL;                             //启动用时
    JLabel DiskSpeedL;                            //磁盘转速
    JLabel NumSectorsL;                           //每磁道扇区
    JLabel SectorBytesL;                          //每扇区字节
    JLabel ByteReadL;                             //字节读取标签
    JTextField ACrossF;                           //跨越磁道时间文本框
    JTextField StarTimeF;                         //启动时间文本框
    JTextField DiskSpeedF;                        //磁盘转速文本框
    JTextField NumSectorsF;                       //每磁道扇区数
    JTextField SectorBytesF;                      //每扇区字节数
    JTextField ByteReadF;                         //字节读取文本框
    public Panel_TimeSet(String str) {
        super(str);
        this.setBounds(600, 200, 400, 400);          //窗口首次出现在屏幕位置
        //===============================JFrame窗口框架设计BEGIN===================================
        this.setPreferredSize(new Dimension(400, 400));     //设置窗口大小
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);             //点击关闭按钮有响应
        this.pack();                            //pack() 调整此窗口的大小，以适合其子组件的首选大小和布局
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); //设置组件竖排排列
        //***********************************JFrame窗口框架设计END***********************************


        //======================================按钮及文本框实例化======================================
        this.setAlwaysOnTop(true);                             //始终在桌面最顶层显示窗体
        this.setSize(500, 300);
        this.setVisible(true);
        this.setResizable(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.AlgChoose = new JPanel();
        this.Across = new JPanel();
        this.StarTime = new JPanel();
        this.DiskSpeed = new JPanel();
        this.NumSectors = new JPanel();
        this.SectorBytes = new JPanel();
        this.ByteRead = new JPanel();

        this.Choose = new JLabel("算法选择");
        this.AcrossL = new JLabel("跨越磁道用时（毫秒）");
        this.StarTimeL = new JLabel("启动时间（毫秒）");
        this.DiskSpeedL = new JLabel("磁盘转速r(转/分钟)");
        this.NumSectorsL = new JLabel("每磁道扇区（块）数：");
        this.SectorBytesL = new JLabel("每扇区（块）字节数：");
        this.ByteReadL = new JLabel("此轮调度需要字节数：");

        this.FCFSChoose = new JCheckBox("FCFS");
        this.SSTFChoose = new JCheckBox("SSTF");
        this.LOOKChoose = new JCheckBox("LOOK");
        this.SCANChoose = new JCheckBox("SCAN");

        this.ACrossF = new JTextField(10);
        this.StarTimeF = new JTextField(10);
        this.DiskSpeedF = new JTextField(10);
        this.NumSectorsF = new JTextField(10);
        this.SectorBytesF = new JTextField(10);
        this.ByteReadF = new JTextField(10);
        this.ACrossF.setText("输入值：");
        this.StarTimeF.setText("输入值：");
        this.DiskSpeedF.setText("输入值：");
        this.NumSectorsF.setText("输入值：");
        this.SectorBytesF.setText("输入值：");
        this.ByteReadF.setText("输入值：");
        this.ACrossF.addFocusListener(new MyFocusListener("输入值：", this.ACrossF));
        this.StarTimeF.addFocusListener(new MyFocusListener("输入值：", this.StarTimeF));
        this.DiskSpeedF.addFocusListener(new MyFocusListener("输入值：", this.DiskSpeedF));
        this.NumSectorsF.addFocusListener(new MyFocusListener("输入值：", this.NumSectorsF));
        this.SectorBytesF.addFocusListener(new MyFocusListener("输入值：", this.SectorBytesF));
        this.ByteReadF.addFocusListener(new MyFocusListener("输入值：", this.ByteReadF));
        //==========================================================================================



        //===========================================面板添加==============================================
        this.AlgChoose.add(Choose);
        this.AlgChoose.add(FCFSChoose);
        this.AlgChoose.add(SSTFChoose);
        this.AlgChoose.add(SCANChoose);
        this.AlgChoose.add(LOOKChoose);
        this.add(AlgChoose,BorderLayout.CENTER);

        this.Across.setLayout(new FlowLayout());
        this.Across.add(AcrossL);
        this.Across.add(ACrossF);

        this.StarTime.setLayout(new FlowLayout());
        this.StarTime.add(StarTimeL);
        this.StarTime.add(StarTimeF);

        this.DiskSpeed.setLayout(new FlowLayout());
        this.DiskSpeed.add(DiskSpeedL);
        this.DiskSpeed.add(DiskSpeedF);

        this.NumSectors.setLayout(new FlowLayout());
        this.NumSectors.add(NumSectorsL);
        this.NumSectors.add(NumSectorsF);

        this.SectorBytes.setLayout(new FlowLayout());
        this.SectorBytes.add(SectorBytesL);
        this.SectorBytes.add(SectorBytesF);

        this.ByteRead.setLayout(new FlowLayout());
        this.ByteRead.add(ByteReadL);
        this.ByteRead.add(ByteReadF);

        this.getContentPane().add(Across);
        this.getContentPane().add(StarTime);
        this.getContentPane().add(DiskSpeed);
        this.getContentPane().add(NumSectors);
        this.getContentPane().add(SectorBytes);
        this.getContentPane().add(ByteRead);
        //================================================================================================



        //==========================================文本输入框监听器===========================================
        this.ACrossF.getDocument().addDocumentListener(this);
        this.StarTimeF.getDocument().addDocumentListener(this);
        this.DiskSpeedF.getDocument().addDocumentListener(this);
        this.NumSectorsF.getDocument().addDocumentListener(this);
        this.SectorBytesF.getDocument().addDocumentListener(this);
        this.ByteReadF.getDocument().addDocumentListener(this);

        this.ACrossF.addKeyListener(this);
        this.StarTimeF.addKeyListener(this);
        this.DiskSpeedF.addKeyListener(this);
        this.NumSectorsF.addKeyListener(this);
        this.SectorBytesF.addKeyListener(this);
        this.ByteReadF.addKeyListener(this);
        //===================================================================================================



        //==========================================窗口其他设置================================================
        this.setAlwaysOnTop(true);
        this.setSize(500, 300);
        this.setVisible(true);
        this.setResizable(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //=====================================================================================================



        //=============================================算法选择及参数传递====================================
        FCFSChoose.addActionListener(e -> {
            System.out.println("算法FCFS");
            System.out.println("请输入请求个数：");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            FCFS fcfs = new FCFS(num);
            try {                                       //启动时间
                fcfs.start = (float)Integer.parseInt(StarTimeF.getDocument().getText(0, StarTimeF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {                                       //磁盘转速
                fcfs.rspeed = (float)Integer.parseInt(DiskSpeedF.getDocument().getText(0, DiskSpeedF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {                                       //磁盘跨越时间
                fcfs.acrosstimefcfs = (float)Integer.parseInt(ACrossF.getDocument().getText(0, ACrossF.getDocument().getLength()));           //传参函数
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {                                       //每扇区块数
                fcfs.num_disk = Integer.parseInt(NumSectorsF.getDocument().getText(0, NumSectorsF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {                                       //每扇区块字节数
                fcfs.numbyte = Integer.parseInt(SectorBytesF.getDocument().getText(0, SectorBytesF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                fcfs.byteread = Integer.parseInt(ByteReadF.getDocument().getText(0, SectorBytesF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                fcfs.FcfsAlg();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            try {
                Panel_OUT panel_out = new Panel_OUT("输出面板", fcfs.startpos, "NULL", fcfs.path, fcfs.FindTrack, fcfs.rotatedelay, fcfs.alltrans, fcfs.Ttrans, fcfs.haveto);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        // 设置 "SSTF" 子菜单被点击的监听器
        SSTFChoose.addActionListener(e -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("算法SSTF");
            System.out.println("请输入磁盘请求个数：");
            int num_reqsstf = scanner.nextInt();
            System.out.println("请输入磁盘请求序列：");
            ArrayList<Integer> ta = new ArrayList<>();
            for (int i = 0; i < num_reqsstf; i++)
                ta.add(scanner.nextInt());
            int startpos = ThreadLocalRandom.current().nextInt(0, 200);
            System.out.println("磁头初始位置为：" + startpos);
            SSTF st = new SSTF(startpos, ta);
            try {
                st.acrosstsstf = Integer.parseInt(ACrossF.getDocument().getText(0, ACrossF.getDocument().getLength()));           //传参函数
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                st.start = Integer.parseInt(StarTimeF.getDocument().getText(0, StarTimeF.getDocument().getLength()));     //传参函数
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                st.rspeed = Integer.parseInt(DiskSpeedF.getDocument().getText(0, DiskSpeedF.getDocument().getLength()));  //传参函数
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                st.num_disk = Integer.parseInt(NumSectorsF.getDocument().getText(0, NumSectorsF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                st.numbyte = Integer.parseInt(SectorBytesF.getDocument().getText(0, SectorBytesF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                st.byteread = Integer.parseInt(ByteReadF.getDocument().getText(0, SectorBytesF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            st.run();
            try {
                Panel_OUT panel_out = new Panel_OUT("输出面板", st.startpos, "NULL", st.distanceSum, st.FindTrack, st.rotatedelay, st.alltrans, st.Ttrans, st.trackSequence);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        // 设置 "SCAN" 子菜单被点击的监听器
        SCANChoose.addActionListener(e -> {
            System.out.println("算法SCAN");
            System.out.println("请输入请求个数：");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            System.out.println("请输入磁盘请求队列：");
            int[] tracks = new int[num];
            for (int i = 0; i < num; i++)
                tracks[i] = scanner.nextInt();
            Scan_Final scan_final = new Scan_Final(tracks, num);
            try {
                scan_final.acrosstimelook = Integer.parseInt(ACrossF.getDocument().getText(0, ACrossF.getDocument().getLength()));           //传参函数
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                scan_final.start = Integer.parseInt(StarTimeF.getDocument().getText(0, StarTimeF.getDocument().getLength()));     //传参函数
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                scan_final.rspeed = Integer.parseInt(DiskSpeedF.getDocument().getText(0, DiskSpeedF.getDocument().getLength()));  //传参函数
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                scan_final.num_disk = Integer.parseInt(NumSectorsF.getDocument().getText(0, NumSectorsF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                scan_final.numbyte = Integer.parseInt(SectorBytesF.getDocument().getText(0, SectorBytesF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            try {
                scan_final.byteread = Integer.parseInt(ByteReadF.getDocument().getText(0, SectorBytesF.getDocument().getLength()));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            scan_final.cal();
            try {
                Panel_OUT panel_out = new Panel_OUT("输出面板", scan_final.startposf, scan_final.Direction, scan_final.sum, scan_final.num_disk, scan_final.rotatedelay, scan_final.alltrans, scan_final.Ttrans, scan_final.out);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        // 设置 "LOOK" 子菜单被点击的监听器
        LOOKChoose.addActionListener(e -> {
            System.out.println("算法LOOK");
            System.out.println("请输入请求个数：");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            System.out.println("请输入磁盘请求队列：");
            int[] tracks = new int[num];
            for (int i = 0; i < num; i++)
                tracks[i] = scanner.nextInt();
            try {
                Look look = new Look(tracks);
                try {
                    look.acrosstimelook = Integer.parseInt(ACrossF.getDocument().getText(0, ACrossF.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    look.start = Float.parseFloat(StarTimeF.getDocument().getText(0, StarTimeF.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    look.rspeed = Float.parseFloat(DiskSpeedF.getDocument().getText(0, DiskSpeedF.getDocument().getLength()));  //传参函数
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    look.nbytes = Integer.parseInt(SectorBytesF.getDocument().getText(0, SectorBytesF.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    look.numsector = Integer.parseInt(NumSectorsF.getDocument().getText(0, NumSectorsF.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    look.byteread = Integer.parseInt(ByteReadF.getDocument().getText(0, NumSectorsF.getDocument().getLength()));
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                look.cal();
                try {
                    Panel_OUT panel_out = new Panel_OUT("输出面板", look.startpos, look.direction, look.sum, look.numsector, look.rotatedelay, look.alltrans, look.Ttrans, look.out);
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        //*****************************************各个磁盘调度算法监听器******************************************
    }
    @Override
    public void actionPerformed (ActionEvent e){

    }
    @Override
    public void keyTyped (KeyEvent e){

    }
    @Override
    public void keyPressed (KeyEvent e){

    }
    @Override
    public void keyReleased (KeyEvent e){

    }
    @Override
    public void insertUpdate (DocumentEvent e){

    }
    @Override
    public void removeUpdate (DocumentEvent e){

    }
    @Override
    public void changedUpdate (DocumentEvent e){
    }
    public static void main (String[]args) {
        new Panel_TimeSet("参数设置界面");
    }
}
