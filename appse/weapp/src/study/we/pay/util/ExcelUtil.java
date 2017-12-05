package study.we.pay.util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import study.we.pay.calc.CostBean;
import study.we.pay.calc.RoomBean;

import java.io.*;
import java.io.File;

/**
 * Created by guokl12792 on 2017/4/16.
 */
public  class ExcelUtil {
    public CostBean getPreCostInfo(){
        Workbook readwb = null;
        CostBean cost = new CostBean();
        try
        {
            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook
            InputStream instream = new FileInputStream("resources/weinfo.xls");

            readwb = Workbook.getWorkbook(instream);
            //Sheet的下标是从0开始

            //获取第一张Sheet表

            Sheet readsheet = readwb.getSheet("costinfo");

            //获取Sheet表中所包含的总列数
            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数
            int rsRows = readsheet.getRows();

            //获取指定单元格的对象引用
            cost.setDate(Integer.parseInt(readsheet.getCell(0, rsRows - 1).getContents().toString()));
            cost.setRoom_ele_1(Double.parseDouble(readsheet.getCell(1, rsRows - 1).getContents().toString()));
            cost.setRoom_ele_2(Double.parseDouble(readsheet.getCell(2, rsRows - 1).getContents().toString()));
            cost.setRoom_ele_3(Double.parseDouble(readsheet.getCell(3, rsRows - 1).getContents().toString()));
            cost.setRoom_ele_4(Double.parseDouble(readsheet.getCell(4, rsRows - 1).getContents().toString()));
            cost.setTotal_ele_cost(Double.parseDouble(readsheet.getCell(5, rsRows - 1).getContents().toString()));
            cost.setTotal_wat_cost(Double.parseDouble(readsheet.getCell(6, rsRows - 1).getContents().toString()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readwb.close();
        }
        return cost;

    }

    public RoomBean getRoomInfo(){
        Workbook readroom = null;
        RoomBean room = new RoomBean();
        try
        {
            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook
            InputStream instream = new FileInputStream("resources/weinfo.xls");

            readroom = Workbook.getWorkbook(instream);
            //Sheet的下标是从0开始

            //获取第一张Sheet表

            Sheet readsheet = readroom.getSheet("roominfo");

            //获取Sheet表中所包含的总列数
            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数
            int rsRows = readsheet.getRows();

            //获取指定单元格的对象引用
            room.setRoom_people_1(Integer.parseInt(readsheet.getCell(rsColumns - 1, 1).getContents().toString()));
            room.setRoom_people_2(Integer.parseInt(readsheet.getCell(rsColumns - 1, 2).getContents().toString()));
            room.setRoom_people_3(Integer.parseInt(readsheet.getCell(rsColumns - 1, 3).getContents().toString()));
            room.setRoom_people_4(Integer.parseInt(readsheet.getCell(rsColumns - 1, 4).getContents().toString()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readroom.close();
        }
        return room;
    }

    public void setCostInfo(CostBean cost) throws BiffException,IOException, WriteException {

        Workbook book = null;
        File file = new File("resources/weinfo.xls");
        book = Workbook.getWorkbook(file);
        Sheet sheet = book.getSheet("costinfo");

        // 获取行
        int length = sheet.getRows();
        WritableWorkbook wbook = Workbook.createWorkbook(file, book); // 根据book创建一个操作对象
        WritableSheet sh = wbook.getSheet("costinfo");// 得到一个工作对象
        // 从最后一行开始加
        Label labe0 = new Label(0, length, Integer.toString(cost.getDate()));
        Label labe1 = new Label(1, length, Double.toString(cost.getRoom_ele_1()));
        Label labe2 = new Label(2, length, Double.toString(cost.getRoom_ele_2()));
        Label labe3 = new Label(3, length, Double.toString(cost.getRoom_ele_3()));
        Label labe4 = new Label(4, length, Double.toString(cost.getRoom_ele_4()));
        Label labe5 = new Label(5, length, Double.toString(cost.getTotal_ele_cost()));
        Label labe6 = new Label(6, length, Double.toString(cost.getTotal_wat_cost()));
        sh.addCell(labe0);
        sh.addCell(labe1);
        sh.addCell(labe2);
        sh.addCell(labe3);
        sh.addCell(labe4);
        sh.addCell(labe5);
        sh.addCell(labe6);

        wbook.write();
        wbook.close();
    }
}
