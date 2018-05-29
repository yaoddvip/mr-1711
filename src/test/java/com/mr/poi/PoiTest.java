package com.mr.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ydd on 2018/5/5.
 */

public class PoiTest {

    /**
     * 导出数据
     */
    @Test
    public void test07() throws IOException {
        //1：创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook();

        //2：创建sheet
        XSSFSheet sheet = wb.createSheet("学生信息");

        Date begin = new Date();

        for(int i = 0 ; i<1000000 ; i++){
            //3：创建行，下标从0开始
            XSSFRow row = sheet.createRow(i);
            //4：创建单元格
            XSSFCell cell = row.createCell(0);
            //5：编写单元格数据
            cell.setCellValue("张三");
        }


        //6：将数据写出去  io流
        //参数：指定写的位置。
        FileOutputStream stream = new FileOutputStream("E:\\mingrui\\学生信息1.xlsx");
        wb.write(stream);
        Date end = new Date();
        System.out.println(end.getTime()-begin.getTime());//4703
    }


    /**
     * 03导出数据
     */
    @Test
    public void test03() throws IOException {
        //1：创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        //2：创建sheet
        HSSFSheet sheet = wb.createSheet("学生信息");

        Date begin = new Date();
        for(int i = 0 ; i<65536 ; i++){
            //3：创建行，下标从0开始
            HSSFRow row = sheet.createRow(i);
            //4：创建单元格
            HSSFCell cell = row.createCell(0);
            //5：编写单元格数据
            cell.setCellValue("张三");
        }
        //6：将数据写出去  io流
        //参数：指定写的位置。
        FileOutputStream stream = new FileOutputStream("E:\\mingrui\\学生信息1.xls");
        wb.write(stream);
        Date end = new Date();
        System.out.println(end.getTime()-begin.getTime());//2678
    }

    /**
     *
     * 导出数据：
     * 查询数据库之后，
     * 通过for循环创建行和单元格
     * 然后将数据写出去
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("学生信息");
        /*创建第一行  添加表头*/
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("姓名");

        HSSFCell cell2 = row.createCell(1);
        cell2.setCellValue("年龄");

        HSSFCell cell3 = row.createCell(2);
        cell3.setCellValue("性别");

        /*创建第二行 添加内容*/
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell11 = row1.createCell(0);
        cell11.setCellValue("张三");

        HSSFCell cell12 = row1.createCell(1);
        cell12.setCellValue("18");

        HSSFCell cell13 = row1.createCell(2);
        cell13.setCellValue("男");

        FileOutputStream fileOutputStream = new FileOutputStream("E:\\mingrui\\学生信息1.xls");
        wb.write(fileOutputStream);
    }


    /**
     * 导入数据
     * 从本地获取到excel文件。读取文件中的内容，
     * 将内容复制对象中，
     * 然后保存在数据库
     */
    @Test
    public void test3() throws IOException {
        //1：通过流读取文件
        POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream("E:\\mingrui\\学生信息1.xls"));

        //读取文件中内容
        //2:获取工作簿
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        HSSFSheet sheet = wb.getSheetAt(0);

        HSSFRow row = sheet.getRow(1);

        HSSFCell cell = row.getCell(0);
        String name = cell.getStringCellValue();

        HSSFCell cell1 = row.getCell(1);
        String age = cell1.getStringCellValue();

        String sex = row.getCell(2).getStringCellValue();

        System.out.println(name+"---"+age+"---"+sex);

        //如何获取有效的行数
        int rowcount = sheet.getLastRowNum();
        System.out.println(rowcount);

        //获取行
        int rows = sheet.getPhysicalNumberOfRows();
        System.out.println(rows);

        /*for(int i = 0 ; i<10;i++){

        }*/
    }

}
