package com.mr.controller;

import com.mr.model.User;
import com.mr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ydd on 2018/5/7.
 */
@Controller
@RequestMapping("poi")
public class PoiController {

    @Autowired
    private UserService userService;

    @RequestMapping("toPoiPage")
    public String toPoiPage(ModelMap map){
        List<User> list= userService.selectUserList();
        map.put("list",list);
        return "poi/poi";
    }


    @RequestMapping("exportPoi")
    public void exportPoi(HttpServletResponse response){
        //告诉浏览器 响应的内容，
        response.setContentType("application/x-execl");
        ServletOutputStream outputStream = null;
        try {
            //将接受到的文件，作为一个附件 弹出来 给别人下载  需要设置它的header
            //setHeader("设置响应头","默认的文件名") new String 防止乱码
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户名单.xlsx".getBytes(), "ISO-8859-1"));
            //创建输出流
            outputStream = response.getOutputStream();
            //将流传递给service
            userService.export(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    //关闭流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 导入文件
     *  1：文件按钮。
     *  2：进入方法，获取到文件。
     *  3：判断是03还是07 ， 如何判断？ 通过后缀名。
     *  4：创建输入流
     *  5：调用service 。
     *  6：获取工作簿。获取sheet，循环获取行 ，获取单元格中的值
     *  7：放在对象中，
     *  8：存在数据库中。
     *  9：重定向到查询页面
     */
    @RequestMapping("importPoi")
    public String importPoi(MultipartFile xxx){
        System.out.println(xxx);
        //获取文件名
        String filename = xxx.getOriginalFilename();
        try {
            userService.importPoi(xxx , filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/poi/toPoiPage.do";
    }

    public static void main(String[] args) {
        String filename = "用户名单.xlsx";
        //如果字符串参数作为一个子字符串在此对象中出现，
        // 则返回第一个这种子字符串的第一个字符的索引；
        // 如果它不作为一个子字符串出现，则返回 -1。
        if(filename.indexOf(".xlsx")!=-1){//07
            System.out.println("07");
        }
    }
}
