package com.example.demo.service.impl;

import com.example.demo.dao.SysDeptMapper;
import com.example.demo.model.SysDept;
import com.example.demo.service.SysDeptService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: SysDeptService接口实现类
* @author zf
* @date 2018/10/11 09:36
*/
@Service
public class SysDeptServiceImpl extends AbstractService<SysDept> implements SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> getAll(Map map){
        return sysDeptMapper.getAll(map);
    }

    @Override
    public List<SysDept> getTreeData() {
        return sysDeptMapper.getTreeData();
    }

    @Override
    public SysDept getByPid(Integer pid) {
        return sysDeptMapper.getByPid(pid);
    }


}