package study.we.pay.calc;

/**
 * Created by guokl12792 on 2017/4/16.
 */
public class CostBean {



    private int date;
    private double room_ele_1;
    private double room_ele_2;
    private double room_ele_3;
    private double room_ele_4;
    private double total_ele_cost;
    private double total_wat_cost;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setRoom_ele_1(double room_ele_1) {
        this.room_ele_1 = room_ele_1;
    }

    public void setRoom_ele_2(double room_ele_2) {
        this.room_ele_2 = room_ele_2;
    }

    public void setRoom_ele_3(double room_ele_3) {
        this.room_ele_3 = room_ele_3;
    }

    public void setRoom_ele_4(double room_ele_4) {
        this.room_ele_4 = room_ele_4;
    }

    public void setTotal_ele_cost(double total_ele_cost) {
        this.total_ele_cost = total_ele_cost;
    }

    public void setTotal_wat_cost(double total_wat_cost) {
        this.total_wat_cost = total_wat_cost;
    }


    public double getRoom_ele_1() {
        return room_ele_1;
    }

    public double getRoom_ele_2() {
        return room_ele_2;
    }

    public double getRoom_ele_3() {
        return room_ele_3;
    }

    public double getRoom_ele_4() {
        return room_ele_4;
    }

    public double getTotal_ele_cost() {
        return total_ele_cost;
    }

    public double getTotal_wat_cost() {
        return total_wat_cost;
    }

}
