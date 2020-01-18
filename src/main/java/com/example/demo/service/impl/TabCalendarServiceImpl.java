package com.example.demo.service.impl;

import com.example.demo.core.universal.AbstractService;
import com.example.demo.dao.TabCalendarMapper;
import com.example.demo.model.TabCalendar;
import com.example.demo.service.TabCalendarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: TabCalendarService接口实现类
* @author zf
* @date 2019/12/26 13:36
*/
@Service
public class TabCalendarServiceImpl extends AbstractService<TabCalendar> implements TabCalendarService {

    @Resource
    private TabCalendarMapper tabCalendarMapper;

    @Override
    public List<TabCalendar> getAll(Map map){
        return tabCalendarMapper.getAll(map);
    }

	@Override
	public TabCalendar getCalendateByDate(Map params) {
		return tabCalendarMapper.getCalendateByDate(params);
	}
	
	
	
	
	

}