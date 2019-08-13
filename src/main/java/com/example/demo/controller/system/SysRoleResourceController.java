package com.example.demo.controller.system;

import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.SysRoleResource;
import com.example.demo.service.SysRoleResourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: SysRoleResourceController类
* @author zf
* @date 2018/09/30 09:44
*/
@RestController
@RequestMapping("/sysRoleResource")
public class SysRoleResourceController {

    @Resource
    private SysRoleResourceService sysRoleResourceService;

    @PostMapping("/insert")
    public RetResult<Integer> insert(SysRoleResource sysRoleResource) throws Exception{
    // sysRoleResource.setId(ApplicationUtils.getUUID());
    	Integer state = sysRoleResourceService.insert(sysRoleResource);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysRoleResourceService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(SysRoleResource sysRoleResource) throws Exception {
        Integer state = sysRoleResourceService.update(sysRoleResource);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<SysRoleResource> selectById(@RequestParam String id) throws Exception {
        SysRoleResource sysRoleResource = sysRoleResourceService.selectById(id);
        return RetResponse.makeOKRsp(sysRoleResource);
    }

    /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<SysRoleResource>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<SysRoleResource>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SysRoleResource> list = sysRoleResourceService.selectAll();
        PageInfo<SysRoleResource> pageInfo = new PageInfo<SysRoleResource>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}