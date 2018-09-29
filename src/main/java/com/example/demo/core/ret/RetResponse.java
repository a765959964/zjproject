package com.example.demo.core.ret;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: 将结果转换为封装后的对象
 * @author zf
 * @date 2018/4/19 09:45
 */
public class RetResponse {

	private final static String SUCCESS = "success";

	public static <T> RetResult<T> makeOKRsp() {
		return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
	}

	public static <T> RetResult<T> makeOKRsp(T data) {
		return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
	}


	public static <T> RetResult<T> makeErrRsp(String message) {
		return new RetResult<T>().setCode(RetCode.FAIL).setMsg(SUCCESS);
	}

	public static <T> RetResult<T> makeRsp(int code, String msg) {
		return new RetResult<T>().setCode(code).setMsg(msg);
	}
	
	public static <T> RetResult<T> makeRsp(int code, String msg, T data) {
		return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
	}

	public static <T> LayuiResult<T> makeRsp(int code, String msg, List<T> data, Long count) {
		return new LayuiResult<T>().setCode(code).setMsg(msg).setData(data).setCount(count);
	}

	public static <T> LayuiResult<T> makeRsp(int code, String msg, PageInfo<T> data, long count) {
		return new LayuiResult<T>().setCode(code).setMsg(msg).setPageDate(data).setCount(count);
	}

}
