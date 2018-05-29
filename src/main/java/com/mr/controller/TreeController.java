package com.mr.controller;

import com.mr.model.Tree;
import com.mr.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ydd on 2018/5/4.
 */
@Controller
@RequestMapping("tree")
public class TreeController {

    public TreeController() {
        System.out.println("------TreeController");
    }

    @Autowired
    private TreeService treeService;

    @RequestMapping("tree")
    public String toTreePage(){
        return "tree/list";
    }

    @ResponseBody
    @RequestMapping("list")
    public List<Tree> list(@RequestParam(defaultValue = "0") Integer id){
        //通过pid查询数据
        List<Tree> list = treeService.getTreeListByPid(id);
        return list;
    }


    @RequestMapping("test")
    public String test(ModelMap map){
        map.put("key","value");
        //返回json数据
        return "user/list";
    }

}
