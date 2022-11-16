/**
 * 显示文本框提示语（默认值）
 */
package gragh;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
class MyFocusListener implements FocusListener {
    String info;
    JTextField jtf;
    public MyFocusListener(String info, JTextField jtf) {
        this.info = info;
        this.jtf = jtf;
    }
    @Override
    public void focusGained(FocusEvent e) {//获得焦点的时候,清空提示文字
        String temp = jtf.getText();
        if(temp.equals(info)){
            jtf.setText("");
        }
    }
    @Override
    public void focusLost(FocusEvent e) {//失去焦点的时候,判断如果为空,就显示提示文字
        String temp = jtf.getText();
        if(temp.equals("")){
            jtf.setText(info);
        }
    }
}

