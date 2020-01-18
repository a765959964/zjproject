package com.example.demo.service;

import com.example.demo.core.universal.Service;
import com.example.demo.model.TabCalendar;

import java.util.List;
import java.util.Map;

/**
* @Description: TabCalendarService接口
* @author zf
* @date 2019/12/26 13:36
*/
public interface TabCalendarService extends Service<TabCalendar> {


    List<TabCalendar> getAll(Map map);
    
    
	/**
	 * 根据日期获取是否节假日
	 * @param params
	 * @return
	 */
	TabCalendar getCalendateByDate(Map params);
}