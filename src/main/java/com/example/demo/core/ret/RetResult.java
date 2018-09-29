package com.example.demo.core.ret;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @Description: 返回对象实体
 * @author zf
 * @date 2018/4/19 09:43
 */
public class RetResult<T> implements Serializable {

	private static final long serialVersionUID = 3758864789222317092L;

	public int code;

	private String msg;

	private T data;

	private Integer count;

	private List<T> listData;


	public RetResult<T> setCode(RetCode retCode) {
		this.code = retCode.code;
		return this;
	}

	public int getCode() {
		return code;
	}

	public RetResult<T> setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public RetResult<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {
		return data;
	}

	public RetResult<T> setData(T data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public Integer getCount() {
		return count;
	}

	public RetResult<T> setCount(Integer count) {
		this.count = count;
		return this;
	}

	public RetResult<T> setListData(List<T> listData) {
		this.listData = listData;
		return this;
	}

	public List<T> getListData() {
		return listData;
	}
}