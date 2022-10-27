package Hashtable_Directory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

//    JLabel title = new JLabel("Directory",JLabel.CENTER);   //创建一个标签对象
    Hashtable ht = new Hashtable(7);

    /*
    构造方法
    */
    public MyFrame(String title){
        super(title);                                   //设置标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置窗体默认关闭方式
        setSize(500,300);                  //设置窗体大小
        setBackground(Color.black);                     //设置窗体背景颜色
        setLocationRelativeTo(null);                    //设置窗体显示位置屏幕居中
        setResizable(false);                            //设置窗口不可拉伸
//        setVisible(true);                               //设置显示或隐藏窗体

        setPanel();

    }
    public void setPanel(){
        JTabbedPane tabbedPane = new JTabbedPane();
        Font DefaultFont = new Font(null,Font.PLAIN,18);    //默认字体

        /*
        vBox1 tabbedPane1
        */
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
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

        panel1.add(name1);
        panel1.add(name);

        panel2.add(tel1);
        panel2.add(tel);

        Box vBox1 = Box.createVerticalBox();
        vBox1.add(panel1);
        vBox1.add(panel2);
        vBox1.add(button1);

        /*
        vBox2 tabbedPane2
        */
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JLabel name2 = new JLabel("姓名");
        JTextField key1 = new JTextField(15);
        JLabel tel2 = new JLabel("电话");
        JTextField value1 = new JTextField("电话号码查询结果",15);
        JButton button2 = new JButton("查找");

        value1.setEditable(false);   //value文本框为输出查找结果的文本框 设置为不可修改

        name2.setFont(DefaultFont);
        key1.setFont(DefaultFont);
        tel2.setFont(DefaultFont);
        value1.setFont(DefaultFont);
        button2.setFont(DefaultFont);

        panel3.add(name2);
        panel3.add(key1);

        panel4.add(tel2);
        panel4.add(value1);

        Box vBox2 = Box.createVerticalBox();
        vBox2.add(panel3);
        vBox2.add(panel4);
        vBox2.add(button2);

        /*
        vBox3 tabbedPane3
        */
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JLabel name3 = new JLabel("姓名");
        JLabel tel3 = new JLabel("电话");
        JTextField key2 = new JTextField(15);
        JTextField value2 = new JTextField("姓名查询结果",15);
        JButton button3 = new JButton("查找");

        value2.setEditable(false);

        name3.setFont(DefaultFont);
        tel3.setFont(DefaultFont);
        key2.setFont(DefaultFont);
        value2.setFont(DefaultFont);
        button3.setFont(DefaultFont);

        panel5.add(tel3);
        panel5.add(key2);

        panel6.add(name3);
        panel6.add(value2);

        Box vBox3 = Box.createVerticalBox();
        vBox3.add(panel5);
        vBox3.add(panel6);
        vBox3.add(button3);

        tabbedPane.addTab("录入",vBox1);
        tabbedPane.addTab("查询电话号码",vBox2);
        tabbedPane.addTab("查询姓名",vBox3);

        this.getContentPane().add(tabbedPane);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c1 = name.getText();
                String c2 = tel.getText();
                switch (ht.Put(c1,c2)){
                    case -1:
                        JOptionPane.showMessageDialog(MyFrame.this,"修改成功","录入电话号码",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(MyFrame.this,"新增成功","录入电话号码",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(MyFrame.this,"录入失败，哈希表已满","录入电话号码",JOptionPane.WARNING_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(MyFrame.this,"录入失败，哈希表已存入相同元素","录入电话号码",JOptionPane.WARNING_MESSAGE);
                        break;
                }

//                System.out.println(c1);
//                System.out.println(c2);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c1 = key1.getText();
                String c2 = ht.Search(c1);
                value1.setText(c2);

//                System.out.println(c1);
//                System.out.println(c2);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String c1 = key1.getText();

            }
        });
    }

}
