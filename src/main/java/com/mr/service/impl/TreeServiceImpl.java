package com.mr.service.impl;

import com.mr.controller.TreeController;
import com.mr.mapper.TreeMapper;
import com.mr.model.Tree;
import com.mr.model.TreeExample;
import com.mr.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ydd on 2018/5/4.
 */
@Service
@Transactional(readOnly = true)
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeMapper treeMapper;


    public List<Tree> getTreeListByPid(Integer id) {
        TreeExample example = new TreeExample();
        TreeExample.Criteria criteria = example.createCriteria();
        /*父节点等于id*/
        criteria.andPidEqualTo(id);
        List<Tree> list = treeMapper.selectByExample(example);
        return list;
    }



}
