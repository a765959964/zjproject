package com.example.demo.controller;

import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.SysResource;
import com.example.demo.service.SysResourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: SysResourceController类
* @author zf
* @date 2018/09/30 09:42
*/
@RestController
@RequestMapping("/sysResource")
public class SysResourceController {

    @Resource
    private SysResourceService sysResourceService;

    @PostMapping("/insert")
    public RetResult<Integer> insert(SysResource sysResource) throws Exception{
		//sysResource.setId(ApplicationUtils.getUUID());
    	Integer state = sysResourceService.insert(sysResource);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysResourceService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(SysResource sysResource) throws Exception {
        Integer state = sysResourceService.update(sysResource);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<SysResource> selectById(@RequestParam String id) throws Exception {
        SysResource sysResource = sysResourceService.selectById(id);
        return RetResponse.makeOKRsp(sysResource);
    }

    /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<SysResource>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<SysResource>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SysResource> list = sysResourceService.selectAll();
        PageInfo<SysResource> pageInfo = new PageInfo<SysResource>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}