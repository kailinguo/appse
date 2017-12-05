package study.we.pay.frame;

import study.we.pay.calc.CostBean;
import study.we.pay.calc.WeCalc;
import study.we.pay.util.ExcelUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guokl12792 on 2017/4/16.
 */
public class WeTabbedPane extends JPanel {
    private WeCalc wecalc;
    private CostBean precost;
    private CostBean nowcost;
    private ExcelUtil excelutil;
    private JTabbedPane tp;
    private JButton jb_calc;
    private JButton jb_save;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel jl_date;
    private JLabel jl1;
    private JLabel jl2;
    private JLabel jl3;
    private JLabel jl4;
    private JLabel jl_total_ele_cost;
    private JLabel jl_total_wat_cost;
    private JTextField jtf_date;
    private JTextField jtf1;
    private JTextField jtf2;
    private JTextField jtf3;
    private JTextField jtf4;
    private JTextField jtf_total_ele_cost;
    private JTextField jtf_total_wat_cost;

    public WeTabbedPane()
    {
        //设置布局管理器，默认的布局管理器是 BorderLayout,这里没那么复杂
        //选择GridLayout(1,1)即可，就是整个为一块
        super(new GridLayout(1,1));
        excelutil = new ExcelUtil();
        precost = new CostBean();
        nowcost = new CostBean();
        wecalc = new WeCalc();
        //创建JTabbedPane
        tp = new JTabbedPane();

        //创建标签显示的图标
        ImageIcon ii = createImageIcon("images/middle.gif");

        //第一个标签
        panel1 = createPanel();

        tp.addTab("上次缴费",ii,panel1,"do noting");
        tp.setMnemonicAt(0, KeyEvent.VK_1);

        //第二个标签
        panel2 = createPanel();

        tp.addTab("本次已缴",ii,panel2,"do noting");
        tp.setMnemonicAt(1, KeyEvent.VK_2);

        //第三个标签
        panel3 = createPanel();

        tp.addTab("缴费录入",ii,panel3,"do noting");
        tp.setMnemonicAt(2, KeyEvent.VK_3);
        //设置合适的显示尺寸，这个是必须的，因为如果所有的标签都
        //不指定适合的显示尺寸，系统无法判断初始显示尺寸大小
        //默认是使用最小化，并且对一个标签设计即可
        tp.setPreferredSize(new Dimension(500,400));
        myEvent();
        //将tabbedPanel添加到Jpanel中
        add(tp);

        //设置窗口过小时，标签的显示策略
        tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        //设置标签停放的位置，这里设置为左侧停放
        tp.setTabPlacement(JTabbedPane.LEFT);

    }

    private JPanel createPanel() {
        //创建一个JPanel，并为构造函数初始false
        //表示不适用双缓冲
        JPanel panel = new JPanel(false);
        //设置布局
        panel.setLayout(new GridLayout(9,2));
        return panel;
    }

    private void restructPanel(JPanel panel) {
        jl_date = new JLabel("录入日期");
        jl1 = new JLabel("房1电量");
        jl2 = new JLabel("房2电量");
        jl3 = new JLabel("房3电量");
        jl4 = new JLabel("房4电量");
        jl_total_ele_cost = new JLabel("电费共计");
        jl_total_wat_cost = new JLabel("水费共计");
        jtf_date = new JTextField();
        jtf1 = new JTextField();
        jtf2 = new JTextField();
        jtf3 = new JTextField();
        jtf4 = new JTextField();
        jtf_total_ele_cost = new JTextField();
        jtf_total_wat_cost = new JTextField();

        panel.add(jl_date);
        panel.add(jtf_date);
        panel.add(jl1);
        panel.add(jtf1);
        panel.add(jl2);
        panel.add(jtf2);
        panel.add(jl3);
        panel.add(jtf3);
        panel.add(jl4);
        panel.add(jtf4);
        panel.add(jl_total_ele_cost);
        panel.add(jtf_total_ele_cost);
        panel.add(jl_total_wat_cost);
        panel.add(jtf_total_wat_cost);
    }


    private ImageIcon createImageIcon(String string) {
        URL url = WeTabbedPane.class.getResource(string);
        if(url == null)
        {
            System.out.println("the image "+string+" is not exist!");
            return null;
        }
        return new ImageIcon(url);
    }

    public static void createAndShowGUI()
    {
        JFrame frame = new JFrame("水电费缴纳程序");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 500) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2;
        frame.setLocation(w, h);
        frame.add(new WeTabbedPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void myEvent(){
        tp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { //响应鼠标点击事件
                for (int i = 0; i < tp.getTabCount(); i++) {
                    Rectangle rect = tp.getBoundsAt(i); //拿到标签的边界
                    if (rect.contains(e.getX(), e.getY())) { //判断是否点在边界内
                        if (i == 0){
                            panel1.removeAll();
                            restructPanel(panel1);

                            precost = excelutil.getPreCostInfo();
                            jtf_date.setText(String.format("%d", precost.getDate()));
                            jtf1.setText(Double.toString(precost.getRoom_ele_1()));
                            jtf2.setText(Double.toString(precost.getRoom_ele_2()));
                            jtf3.setText(Double.toString(precost.getRoom_ele_3()));
                            jtf4.setText(Double.toString(precost.getRoom_ele_4()));
                            jtf_total_ele_cost.setText(Double.toString(precost.getTotal_ele_cost()));
                            jtf_total_wat_cost.setText(Double.toString(precost.getTotal_wat_cost()));

                        }else if(i == 1) {
                            panel2.removeAll();
                            restructPanel(panel2);

                            if (nowcost != null && nowcost.getDate() > 0) {
                                jtf_date.setText(String.format("%d", nowcost.getDate()));
                                jtf1.setText(Double.toString(nowcost.getRoom_ele_1()));
                                jtf2.setText(Double.toString(nowcost.getRoom_ele_2()));
                                jtf3.setText(Double.toString(nowcost.getRoom_ele_3()));
                                jtf4.setText(Double.toString(nowcost.getRoom_ele_4()));
                                jtf_total_ele_cost.setText(Double.toString(nowcost.getTotal_ele_cost()));
                                jtf_total_wat_cost.setText(Double.toString(nowcost.getTotal_wat_cost()));
                            }
                            else {
                                Date currentTime = new Date();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                                String dateString = formatter.format(currentTime);
                                jtf_date.setText(dateString);
                            }

                            jb_calc = new JButton("计算");
                            calcEvent();
                            panel2.add(new JLabel());
                            panel2.add(jb_calc);

                        }else if(i == 2)
                        {
                            panel3.removeAll();
                            restructPanel(panel3);

                            jb_save = new JButton("录入");
                            saveEvent();
                            panel3.add(new JLabel());
                            panel3.add(jb_save);

                            if (nowcost != null && nowcost.getDate() > 0) {
                                jtf_date.setText(String.format("%d", nowcost.getDate()));
                                jtf1.setText(Double.toString(nowcost.getRoom_ele_1()));
                                jtf2.setText(Double.toString(nowcost.getRoom_ele_2()));
                                jtf3.setText(Double.toString(nowcost.getRoom_ele_3()));
                                jtf4.setText(Double.toString(nowcost.getRoom_ele_4()));
                                jtf_total_ele_cost.setText(Double.toString(nowcost.getTotal_ele_cost()));
                                jtf_total_wat_cost.setText(Double.toString(nowcost.getTotal_wat_cost()));
                            }

                        }

                    }
                }
            }
        });

    }

    private void calcEvent(){

        jb_calc.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { //响应鼠标点击事件
                if (precost != null && precost.getDate() > 0 && !jtf_date.getText().trim().isEmpty()) {
                    nowcost.setDate(Integer.parseInt(jtf_date.getText()));
                    nowcost.setRoom_ele_1(Double.parseDouble(jtf1.getText()));
                    nowcost.setRoom_ele_2(Double.parseDouble(jtf2.getText()));
                    nowcost.setRoom_ele_3(Double.parseDouble(jtf3.getText()));
                    nowcost.setRoom_ele_4(Double.parseDouble(jtf4.getText()));
                    nowcost.setTotal_ele_cost(Double.parseDouble(jtf_total_ele_cost.getText()));
                    nowcost.setTotal_wat_cost(Double.parseDouble(jtf_total_wat_cost.getText()));
                    new SubFrame(wecalc.calc(precost, nowcost));
                }

            }
        });
    }

    private void saveEvent(){

        jb_save.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { //响应鼠标点击事件
                if (nowcost != null && nowcost.getDate() > 0) {
                    try {
                        excelutil.setCostInfo(nowcost);
                        JOptionPane.showMessageDialog(panel2,"Success");
                    }catch (Exception e1)
                    {

                    }

                }

            }
        });
    }

}
