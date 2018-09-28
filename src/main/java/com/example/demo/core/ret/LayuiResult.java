package com.example.demo.core.ret;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 封装layui 表格，返回json填充页面
 * layui 返回结果集
 * Created by 张帆 on 2018/9/26.
 */
public class LayuiResult<T> implements Serializable {

    private static final long serialVersionUID = -6769400398402862201L;

    public int code;

    private String msg;

    private List<T> data;

    private PageInfo<T> pageData;

    private Long count;


    public LayuiResult<T> setCode(RetCode retCode) {
        this.code = retCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public LayuiResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public LayuiResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Long getCount() {
        return count;
    }

    public LayuiResult<T> setCount(Long count) {
        this.count = count;
        return this;
    }

    public LayuiResult<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public LayuiResult<T> setPageDate(PageInfo<T> pageData){
        this.pageData = pageData;
        return this;
    }


    public List<T> getData() {
        return data;
    }

    public PageInfo<T> getPageData() {
        return pageData;
    }
}
