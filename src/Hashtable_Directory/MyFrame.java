package Hashtable_Directory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    Hashtable ht = new Hashtable(17);                        //创建哈希表
    Font DefaultFont = new Font(null,Font.PLAIN,18);    //默认字体

    /*
    构造方法
    */
    public MyFrame(String title){
        super(title);                                   //设置标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置窗体默认关闭方式
        setSize(500,300);                   //设置窗体大小
        setBackground(Color.black);                     //设置窗体背景颜色
        setLocationRelativeTo(null);                    //设置窗体显示位置屏幕居中
        setResizable(false);                            //设置窗口不可拉伸
        setPanel();                                     //为窗口添加元件

    }
    public void setPanel(){
        JTabbedPane tabbedPane = new JTabbedPane();

        /*
        vBox1 tabbedPane1 录入数据标签页
        */
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JLabel name1 = new JLabel("姓名");
        JLabel tel1 = new JLabel("电话");
        JTextField name = new JTextField(15);
        JTextField tel = new JTextField(15);
        JButton button1 = new JButton("提交");
        //设置字体
        tabbedPane.setFont(DefaultFont);
        name1.setFont(DefaultFont);
        tel1.setFont(DefaultFont);
        name.setFont(DefaultFont);
        tel.setFont(DefaultFont);
        button1.setFont(DefaultFont);

        //设置电话文本框只允许输入数字
        tel.setDocument(new NumberTextField());
        //设置姓名长度限制
        name.setDocument(new LimitedTextField());

        //将内容加入到panel组件中
        panel1.add(name1);
        panel1.add(name);
        panel2.add(tel1);
        panel2.add(tel);
        panel3.add(button1);
        //将panel组件加入到Box组件中
        Box vBox1 = Box.createVerticalBox();
        vBox1.add(panel1);
        vBox1.add(panel2);
        vBox1.add(panel3);

        /*
        vBox2 tabbedPane2 查询电话号码标签页
        */
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JLabel name2 = new JLabel("姓名");
        JTextField key1 = new JTextField(15);
        JLabel tel2 = new JLabel("电话");
        JTextField value1 = new JTextField("",15);
        JButton button2 = new JButton("查找");

        //设置字体
        name2.setFont(DefaultFont);
        key1.setFont(DefaultFont);
        tel2.setFont(DefaultFont);
        value1.setFont(DefaultFont);
        button2.setFont(DefaultFont);

        //value1文本框为输出查找结果的文本框 设置为不可修改
        value1.setEditable(false);

        //将内容加入到panel组件中
        panel4.add(name2);
        panel4.add(key1);
        panel5.add(tel2);
        panel5.add(value1);
        panel6.add(button2);
        //将panel组件加入到Box组件中
        Box vBox2 = Box.createVerticalBox();
        vBox2.add(panel4);
        vBox2.add(panel5);
        vBox2.add(panel6);

        /*
        vBox3 tabbedPane3 查询姓名标签页
        */
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JLabel name3 = new JLabel("姓名");
        JLabel tel3 = new JLabel("电话");
        JTextField key2 = new JTextField(15);
        JTextField value2 = new JTextField("",15);
        JButton button3 = new JButton("查找");

        //设置字体
        name3.setFont(DefaultFont);
        tel3.setFont(DefaultFont);
        key2.setFont(DefaultFont);
        value2.setFont(DefaultFont);
        button3.setFont(DefaultFont);

        //value1文本框为输出查找结果的文本框 设置为不可修改
        value2.setEditable(false);

        //将内容加入到panel组件中
        panel7.add(tel3);
        panel7.add(key2);
        panel8.add(name3);
        panel8.add(value2);
        panel9.add(button3);
        //将panel组件加入到Box组件中
        Box vBox3 = Box.createVerticalBox();
        vBox3.add(panel7);
        vBox3.add(panel8);
        vBox3.add(panel9);

        /*
        vBox4 tabbedPane4 修改电话号码标签页
        */
        JPanel panel10 = new JPanel();
        JPanel panel11 = new JPanel();
        JPanel panel12 = new JPanel();
        JLabel name4 = new JLabel("给定姓名");
        JLabel tel4 = new JLabel("修改电话为");
        JTextField key3 = new JTextField(15);
        JTextField value3 = new JTextField(15);
        JButton button4 = new JButton("修改电话");

        //设置字体
        name4.setFont(DefaultFont);
        tel4.setFont(DefaultFont);
        key3.setFont(DefaultFont);
        value3.setFont(DefaultFont);
        button4.setFont(DefaultFont);

        //设置修改电话只能输入数字
        value3.setDocument(new NumberTextField());

        //将内容加入到panel组件中
        panel10.add(name4);
        panel10.add(key3);
        panel11.add(tel4);
        panel11.add(value3);
        panel12.add(button4);
        //将panel组件加入到Box组件中
        Box vBox4 = Box.createVerticalBox();
        vBox4.add(panel10);
        vBox4.add(panel11);
        vBox4.add(panel12);

        /*
        vBox5 tabbedPane5 修改姓名标签页
        */
        JPanel panel13 = new JPanel();
        JPanel panel14 = new JPanel();
        JPanel panel15 = new JPanel();
        JLabel tel5 = new JLabel("给定电话");
        JLabel name5 = new JLabel("修改姓名为");
        JTextField key4 = new JTextField(15);
        JTextField value4 = new JTextField(15);
        JButton button5 = new JButton("修改姓名");


        name5.setFont(DefaultFont);
        tel5.setFont(DefaultFont);
        key4.setFont(DefaultFont);
        value4.setFont(DefaultFont);
        button5.setFont(DefaultFont);

        //设置修改姓名限制长度
        value4.setDocument(new LimitedTextField());

        //将内容加入到panel组件中
        panel13.add(tel5);
        panel13.add(key4);
        panel14.add(name5);
        panel14.add(value4);
        panel15.add(button5);
        //将panel组件加入到Box组件中
        Box vBox5 = Box.createVerticalBox();
        vBox5.add(panel13);
        vBox5.add(panel14);
        vBox5.add(panel15);

        //将Box组件加入到标签页
        tabbedPane.addTab("录入",vBox1);
        tabbedPane.addTab("查询电话号码",vBox2);
        tabbedPane.addTab("查询姓名",vBox3);
        tabbedPane.addTab("修改电话",vBox4);
        tabbedPane.addTab("修改姓名",vBox5);

        //将选项卡面板加入到窗口中
        this.getContentPane().add(tabbedPane);

        //新增数据按钮
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c1 = name.getText();
                String c2 = tel.getText();
                switch (ht.Put(c1,c2)){
                    case 0:
                        JOptionPane.showMessageDialog(MyFrame.this,"新增成功","录入成功",JOptionPane.INFORMATION_MESSAGE);
                        name.setText("");
                        tel.setText("");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(MyFrame.this,"哈希表已满","录入失败",JOptionPane.WARNING_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(MyFrame.this,"已存入相同姓名的元素","录入失败",JOptionPane.WARNING_MESSAGE);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(MyFrame.this,"已存入相同电话号码的元素","录入失败",JOptionPane.WARNING_MESSAGE);
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(MyFrame.this,"两个哈希表不同步","录入失败",JOptionPane.WARNING_MESSAGE);
                        break;
                }

            }
        });
        //以姓名为关键字查询电话号码的按钮
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c1 = key1.getText();
                String c2 = ht.name_Search(c1);
                value1.setText(c2);
            }
        });
        //以电话号码为关键字查询姓名的按钮
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c1 = key2.getText();
                String c2 = ht.tel_Search(c1);
                value2.setText(c2);
            }
        });
        //以姓名为关键词修改对应电话号码的按钮
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c1 = key3.getText();
                if(ht.name_SearchForBoolean(c1)){
                    String c2 = value3.getText();
                    ht.name_Change(c1,c2);
                    JOptionPane.showMessageDialog(MyFrame.this,"修改成功","修改成功",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(MyFrame.this,"不存在给定电话的记录，请重新确认","修改失败",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        //以电话号码为关键字修改对应姓名的按钮
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c1 = key4.getText();
                if(ht.tel_SearchForBoolean(c1)){
                    String c2 = value4.getText();
                    ht.tel_Change(c1,c2);
                    JOptionPane.showMessageDialog(MyFrame.this,"修改成功","修改成功",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(MyFrame.this,"不存在给定姓名的记录，请重新确认","修改失败",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        //当切换选项卡时 用户应该已经完成对于当前选项卡功能的使用 则自动将文本框内容清空 方便下次使用
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                name.setText("");
                tel.setText("");
                key1.setText("");
                value1.setText("");
                key2.setText("");
                value2.setText("");
                key3.setText("");
                value3.setText("");
                key4.setText("");
                value4.setText("");
            }
        });

    }

}
