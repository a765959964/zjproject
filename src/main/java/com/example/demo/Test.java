package com.example.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Scanner;


/**
 * 生成多对多 好友关系
 * @author 张帆
 *
 */
public class Test {
	
	private static Logger log = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) {
//		System.out.println(ZJ_DateUtils.format(new Date(),"yyyy-MM-dd"));
//		MoneytoMoney();
		System.out.println(DateUtil.lastWeek());
//		oldFile();
//		String fileUrl = "10.10.2.17:7500/v1/tappkeywebsock111298/T1DahTB5dT1RCvBVdK";
//		long size = HttpUtil.downloadFile(fileUrl, FileUtil.file("e:/"));
//		System.out.println("Download size: " + size);
		HashMap<String, Object> paramMap = new HashMap<>();
		//文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
		paramMap.put("file", FileUtil.file("10.10.2.17:7500/v1/tappkeywebsock111298/T1DahTB5dT1RCvBVdK"));
		String result= HttpUtil.post("http://localhost:8023/fastdfs/uploadFile", paramMap);
		System.out.println(result);
	}
	
	private static void MoneytoMoney(){
		Scanner in = new Scanner(System.in);
		System.out.println("请输入总条数:");
		Integer length = in.nextInt();
		System.out.println("请输入开始数:");
		Integer begin = in.nextInt();
		
		int total = 0;
		for(int i=begin;i<begin+length;i++){
			for(int j=begin;j<begin+length;j++){
				if(i==j){
					continue;
				}
				total++;
				System.out.println(i+"\t"+j);
//				log.info(i+"\t"+j);
			}
		}
		System.out.println("共"+total);
		
	}
	
	/**
	 * 原始生成文件
	 */
	private static void oldFile(){
		int length = 4;
		int total = 0;
		for(int i=101;i<=101+length;i++){
			for(int j=101;j<=101+length;j++){
				if(i==j){
					continue;
				}
				total++;
				System.out.println(i+"\t"+j);
			}
		}
		System.out.println("共"+total);
		
	}
	
	private static void xinFile(){
		int total=0;
		boolean flag = true;
			while(flag){
				Scanner in = new Scanner(System.in);
				System.out.println("请输入共有多少人:");
				int length = in.nextInt();
				System.out.println("请输入开始的ID:");
				int i = in.nextInt();
				length = i + length;
				for(i=i;i<length;i++){
					for(int j=i;j<length;j++){
						total++;
						System.out.println(i+"\t"+(j+1));
					}
				}
				System.out.println("总人数:"+total);
			}
			
	}
}
