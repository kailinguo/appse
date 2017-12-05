package study.we.pay.frame;

import study.we.pay.calc.CostBean;

import javax.swing.*;
import java.awt.*;

/**
 * Created by guokl12792 on 2017/4/17.
 */
public class SubFrame extends JFrame {
    public SubFrame(CostBean cost)
    {
        JLabel jl11 = new JLabel("房1应缴");
        JLabel jl22= new JLabel("房2应缴");
        JLabel jl33 = new JLabel("房3应缴");
        JLabel jl44 = new JLabel("房4应缴");

        JTextField jtf11 = new JTextField();
        JTextField jtf22 = new JTextField();
        JTextField jtf33 = new JTextField();
        JTextField jtf44 = new JTextField();

        jtf11.setText(Double.toString(cost.getRoom_ele_1()));
        jtf22.setText(Double.toString(cost.getRoom_ele_2()));
        jtf33.setText(Double.toString(cost.getRoom_ele_3()));
        jtf44.setText(Double.toString(cost.getRoom_ele_4()));

        this.add(jl11);
        this.add(jtf11);
        this.add(jl22);
        this.add(jtf22);
        this.add(jl33);
        this.add(jtf33);
        this.add(jl44);
        this.add(jtf44);

        setTitle("计算结果");
        setSize(300, 200);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setLayout(new GridLayout(4,2));
        setVisible(true);
    }
}
