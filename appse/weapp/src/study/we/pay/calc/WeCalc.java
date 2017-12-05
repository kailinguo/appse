package study.we.pay.calc;

import study.we.pay.util.ExcelUtil;

/**
 * Created by guokl12792 on 2017/4/16.
 */
public class WeCalc {
    public static double RoomPer = 0.7;
    public static double PubPer = 0.3;
    private ExcelUtil excelutil;
    private RoomBean room;
    private CostBean cost;

    public CostBean calc(CostBean precost,CostBean nowcost){
        excelutil = new ExcelUtil();
        room = new RoomBean();
        cost = new CostBean();
        room = excelutil.getRoomInfo();
        int sum_people = room.getRoom_people_1() + room.getRoom_people_2() + room.getRoom_people_3() + room.getRoom_people_4();
        double sub_ele_1 = nowcost.getRoom_ele_1() - precost.getRoom_ele_1();
        double sub_ele_2 = nowcost.getRoom_ele_2() - precost.getRoom_ele_2();
        double sub_ele_3 = nowcost.getRoom_ele_3() - precost.getRoom_ele_3();
        double sub_ele_4 = nowcost.getRoom_ele_4() - precost.getRoom_ele_4();
        double sum_sub_ele = sub_ele_1 + sub_ele_2 + sub_ele_3 + sub_ele_4;
        double ave_ele_cost = nowcost.getTotal_ele_cost() * PubPer / sum_people;
        double ave_wat_cost = nowcost.getTotal_wat_cost() / sum_people;

        cost.setRoom_ele_1(sub_ele_1/sum_sub_ele * nowcost.getTotal_ele_cost() * RoomPer + ave_ele_cost * room.getRoom_people_1() + ave_wat_cost * room.getRoom_people_1());
        cost.setRoom_ele_2(sub_ele_2/sum_sub_ele * nowcost.getTotal_ele_cost() * RoomPer + ave_ele_cost * room.getRoom_people_2() + ave_wat_cost * room.getRoom_people_2());
        cost.setRoom_ele_3(sub_ele_3/sum_sub_ele * nowcost.getTotal_ele_cost() * RoomPer + ave_ele_cost * room.getRoom_people_3() + ave_wat_cost * room.getRoom_people_3());
        cost.setRoom_ele_4(sub_ele_4/sum_sub_ele * nowcost.getTotal_ele_cost() * RoomPer + ave_ele_cost * room.getRoom_people_4() + ave_wat_cost * room.getRoom_people_4());

        return cost;
    }
}
