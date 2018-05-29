package com.mr.service.impl;

import com.mr.mapper.UserMapper;
import com.mr.model.Tree;
import com.mr.model.User;
import com.mr.redis.JedisClient;
import com.mr.service.UserService;
import com.mr.util.JsonUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ydd on 2018/5/7.
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${USERS_KEY}")
    private String USERS_KEY;


    /**
     * 数据保持同步
     * @param user
     */
    //增加
    @Transactional
    public void add(User user) {
        //增加
        userMapper.insertSelective(user);
        //将对应的数据删除掉
        jedisClient.del(USERS_KEY);
    }

    /**
     * 查询数据集合
     * @return
     */
    public List<User> getList() {
        //查询redis中是否存在数据
        Boolean b = jedisClient.exists(USERS_KEY);
        //如果存在数据，则返回数据
        if(b){
            String json = jedisClient.get(USERS_KEY);
            //将数据返回去
            return JsonUtils.jsonToList(json , User.class);
        }
        //如果不存在，则查询数据库
        //查询数据库
        List<User> users = userMapper.selectByExample(null);
        //将数据存放在redis中
        jedisClient.set(USERS_KEY , JsonUtils.objectToJson(users));
        //将数据返回
        return users;
    }




    public List<User> selectUserList() {
        List<User> list = userMapper.selectByExample(null);
        return list;
    }

    /**
     * 导出数据   以出全部数据为例
     * @param outputStream
     */
    public void export(ServletOutputStream outputStream) {
        //查询全部数据，获取到数据
        List<User> list = userMapper.selectByExample(null);

        //开始poi导出的工作
        //创建07工作簿
        XSSFWorkbook wb = new XSSFWorkbook();

        //创建sheet
        XSSFSheet sheet= wb.createSheet("用户信息");

        XSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("编号");
        row1.createCell(1).setCellValue("姓名");
        row1.createCell(2).setCellValue("年龄");
        row1.createCell(3).setCellValue("性别");
        row1.createCell(4).setCellValue("生日");

        for(int i = 1; i<= list.size();i++){
            //创建行  --》对象的是数据的行数
            XSSFRow row = sheet.createRow(i);
            User user = list.get(i-1);
            //创建单元格
            //在单元格中添加内容
            row.createCell(0).setCellValue(user.getUserId());
            row.createCell(1).setCellValue(user.getUserName());
            row.createCell(2).setCellValue(user.getUserAge());
            row.createCell(3).setCellValue(user.getUserSex()==1?"男":"女");
            //创建日期的单元格
            XSSFCell dateCell = row.createCell(4);
            //给单元格赋值
            dateCell.setCellValue(user.getUserBir());

            /*-----------------规格了一个单元格的样式-----------------------*/
           /* //将该单元格设置为日期格式
            XSSFCellStyle cellStyle = wb.createCellStyle();
            //获得CreationHelper对象,这个应该是一个帮助类
            XSSFCreationHelper creationHelper = wb.getCreationHelper();
            //通过 creationHelper 获取时间格式的 short值
            short dateFormat = creationHelper.createDataFormat().getFormat("yyyy-MM-dd");
            //通过setDataFormat（short值）获取单元格的格式，具体使用什么格式，有参数决定；
            cellStyle.setDataFormat(dateFormat);*/
            /*-------------------规格了一个单元格的样式---------------------*/
            //调用封装的转换日期格式方法
            XSSFCellStyle cellStyle = setDateFormat(wb);

            //给哪一个单元格赋值样式
            dateCell.setCellStyle(cellStyle);
            //设置单元格的宽度
            sheet.setColumnWidth((short)4,(short)25 * 256);
        }
        //输出到本地
        try {
            wb.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 设置单元格的样式为日期格式
     * @param wb  传入工作簿
     * @return    返回样式
     */
    public XSSFCellStyle setDateFormat(XSSFWorkbook wb){
        //将该单元格设置为日期格式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        //获得CreationHelper对象,这个应该是一个帮助类
        XSSFCreationHelper creationHelper = wb.getCreationHelper();
        //通过 creationHelper 获取时间格式的 short值
        short dateFormat = creationHelper.createDataFormat().getFormat("yyyy-MM-dd");
        //通过setDataFormat（short值）获取单元格的格式，具体使用什么格式，有参数决定；
        cellStyle.setDataFormat(dateFormat);
        return cellStyle;
    }


    /**
     * 文件导入到数据库
     * @param xxx   文件
     * @param filename   文件名
     *
     *   3：判断是03还是07 ， 如何判断？ 通过后缀名。
     *  4：创建输入流
     *  6：获取工作簿。获取sheet，循环获取行 ，获取单元格中的值
     *  7：放在对象中，
     *  8：存在数据库中。
     */
    @Transactional
    public void importPoi(MultipartFile xxx, String filename) throws IOException {
        //如果字符串参数作为一个子字符串在此对象中出现，
        // 则返回第一个这种子字符串的第一个字符的索引；
        // 如果它不作为一个子字符串出现，则返回 -1。
        Workbook wb = null;
        if(filename.indexOf(".xlsx")!=-1){//07
            //创建工作簿时需要传递参数，输入流
            wb = new XSSFWorkbook(xxx.getInputStream());
        }else{
            wb = new HSSFWorkbook(xxx.getInputStream());
        }
        //获取第一个工作簿
        Sheet sheet = wb.getSheetAt(0);

        //定义一个存放数据的list
        List<User> list = new ArrayList<User>();

        //sheet.getPhysicalNumberOfRows()获取有效行数
        for(int i = 1 ;i < sheet.getPhysicalNumberOfRows();i++){
            User user = new User();
            //获取行
            Row row =sheet.getRow(i);
            String name = row.getCell(1).getStringCellValue();
            user.setUserName(name);

            Double age = row.getCell(2).getNumericCellValue();
            user.setUserAge(age.intValue());

            String sex = row.getCell(3).getStringCellValue();
            if("男".equals(sex)){
                user.setUserSex(1);
            }else{
                user.setUserSex(2);
            }
            Date bir = row.getCell(4).getDateCellValue();
            user.setUserBir(bir);
            list.add(user);
            //userMapper.insertSelective(user);
        }

        userMapper.insertUsers(list);
    }



}
