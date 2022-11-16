/**
 * 修改：
 * 1. 打印序列
 */
package gragh;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Panel_OUT extends JFrame implements DocumentListener, KeyListener {
    JPanel TimeALL;                           //总时间面板
    JPanel NumMove;                           //磁臂引动量面板
    JPanel FinDiskTime;                       //寻道时间面板
    JPanel RotateDaley;                       //平均旋转延迟时间面板
    JPanel Ttrans;                            //传输时间面板
    JPanel Direction;                         //磁头初始移动方向面板
    JPanel Listt;                              //调度序列面板
    JPanel StartPos;                          //磁头初始位置面板
    JLabel TimeALLL;                          //总时间标签
    JLabel NumMoveL;                          //磁臂引动量标签
    JLabel FinDiskTimeL;                      //寻道时间面板
    JLabel RotateDaleyL;                      //平均旋转延迟时间标签
    JLabel TtransL;                           //传输时间
    JLabel ListL;                             //调度序列面板
    JLabel StartPosL;                         //初始磁头面板
    JLabel DirectionL;                        //初始移动方向面板
    JTextField TimeALLF;                      //总时间
    JTextField NumMoveF;                      //引臂移动量
    JTextField FinDiskTimeF;                  //寻道时间
    JTextField RotateDaleyF;                  //平均旋转延迟时间
    JTextField TtransF;                       //传输时间
    JTextArea ListF;                          //调度序列
    JTextField StartPosF;                     //初始位置
    JTextField DirectionF;                    //初始方向
    JScrollPane Lists;
    public Panel_OUT(String str, int startpos, String dir, int ns, float fdt, float nt, float ta, float ct, int[] lst) throws BadLocationException {
        super();
        this.setBounds(600, 200, 400, 400);    //窗口首次出现在屏幕位置

        //===============================JFrame窗口框架设计BEGIN===================================
        this.setPreferredSize(new Dimension(400, 400));     //设置窗口大小
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);             //点击关闭按钮有响应
        this.pack();                            //pack() 调整此窗口的大小，以适合其子组件的首选大小和布局
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); //设置组件竖排排列
        //=======================================================================================



        //=====================================选项实例化===================================
        this.NumMove = new JPanel();
        this.FinDiskTime = new JPanel();
        this.RotateDaley = new JPanel();
        this.Ttrans = new JPanel();
        this.TimeALL = new JPanel();
        this.Direction = new JPanel();  //磁头初始移动方向
        this.Listt = new JPanel();
        this.StartPos = new JPanel();
        this.NumMoveL = new JLabel("引臂移动量：");
        this.FinDiskTimeL = new JLabel("寻道时间：");
        this.RotateDaleyL = new JLabel("平均旋转延迟时间：");
        this.TtransL = new JLabel("传输时间：");
        this.TimeALLL = new JLabel("所有访问处理时间：");
        this.ListL = new JLabel("调度序列：");
        this.StartPosL = new JLabel("磁头初始位置");
        this.DirectionL = new JLabel("磁臂初始移动方向");
        this.NumMoveF = new JTextField(10);
        this.FinDiskTimeF = new JTextField(10);
        this.RotateDaleyF = new JTextField(10);
        this.TtransF = new JTextField(10);
        this.TimeALLF = new JTextField(10);
        this.ListF = new JTextArea(1, 20);
        this.Lists = new JScrollPane(ListF);
        this.Lists.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.Lists.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.StartPosF = new JTextField(10);
        this.DirectionF = new JTextField(10);
        //=================================================================================



        //===================================各面板加入JFrame=================================
        this.NumMove.setLayout(new FlowLayout());
        this.NumMove.add(NumMoveL);
        this.NumMove.add(NumMoveF);

        this.FinDiskTime.setLayout(new FlowLayout());
        this.FinDiskTime.add(FinDiskTimeL);
        this.FinDiskTime.add(FinDiskTimeF);
        this.RotateDaley.setLayout(new FlowLayout());
        this.RotateDaley.add(RotateDaleyL);
        this.RotateDaley.add(RotateDaleyF);

        this.Ttrans.setLayout(new FlowLayout());
        this.Ttrans.add(TtransL);
        this.Ttrans.add(TtransF);

        this.TimeALL.setLayout(new FlowLayout());
        this.TimeALL.add(TimeALLL);
        this.TimeALL.add(TimeALLF);

        this.Listt.setLayout(new FlowLayout());
        this.Listt.add(ListL);
        this.Listt.add(Lists);

        this.StartPos.setLayout(new FlowLayout());
        this.StartPos.add(StartPosL);
        this.StartPos.add(StartPosF);

        this.Direction.setLayout(new FlowLayout());
        this.Direction.add(DirectionL);
        this.Direction.add(DirectionF);
        //=====================================================================================




        //========================================监听器加入========================================
        this.getContentPane().add(StartPos);
        this.getContentPane().add(Direction);
        this.getContentPane().add(NumMove);
        this.getContentPane().add(FinDiskTime);
        this.getContentPane().add(RotateDaley);
        this.getContentPane().add(Ttrans);
        this.getContentPane().add(TimeALL);
        this.getContentPane().add(Listt);
        this.setAlwaysOnTop(true);
        this.setSize(500, 300);
        this.setVisible(true);
        this.setResizable(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //=====================================================================================


        //=======================================打印值==========================================
        this.StartPosF.setText(Integer.toString(startpos));
        this.DirectionF.setText(dir);
        this.TimeALLF.setText(Float.toString(ta));
        this.RotateDaleyF.setText(Float.toString(nt));
        this.FinDiskTimeF.setText(Float.toString(fdt));
        this.NumMoveF.setText(Integer.toString(ns));
        this.ListF.setText(Arrays.toString(lst));
        this.TtransF.setText(Float.toString(ct));
        //======================================================================================
    }


    /**
     * 实现DocumentListener接口中insertUpdate方法
     * 该方法可以跟踪文本框中输入的内容
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        Document doc = e.getDocument();
        try {
            String s = doc.getText(0, doc.getLength()); //返回文本框输入的内容
            System.out.println(s);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {}
    @Override
    public void changedUpdate(DocumentEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}

