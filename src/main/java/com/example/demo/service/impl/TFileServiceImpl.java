package com.example.demo.service.impl;

import com.example.demo.dao.TFileMapper;
import com.example.demo.model.TFile;
import com.example.demo.service.TFileService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: TFileService接口实现类
* @author zf
* @date 2019/04/22 16:09
*/
@Service
public class TFileServiceImpl extends AbstractService<TFile> implements TFileService {

    @Resource
    private TFileMapper tFileMapper;

    @Override
    public List<TFile> getAll(Map map){
        return tFileMapper.getAll(map);
    }

}