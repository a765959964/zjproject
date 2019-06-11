package com.example.demo.service;

import com.example.demo.model.TFile;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: TFileService接口
* @author zf
* @date 2019/04/22 16:09
*/
public interface TFileService extends Service<TFile> {


    List<TFile> getAll(Map map);
}