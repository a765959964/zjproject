package com.example.demo.dao;

import java.util.Map;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.TabCalendar;

public interface TabCalendarMapper extends Mapper<TabCalendar> {
	
	
	/**
	 * 根据日期获取是否节假日
	 * @param params
	 * @return
	 */
	TabCalendar getCalendateByDate(Map params);
}