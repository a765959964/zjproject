package com.example.demo.controller.santint;

import com.example.demo.core.fastdfs.FastDFSClient;
import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.TFile;
import com.example.demo.service.TFileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.web.query.QueryFilter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @Description: TFileController类
* @author zf
* @date 2019/04/22 16:09
*/
@RestController
@RequestMapping("/sys/tFile")
public class TFileController {

    @Resource
    private TFileService tFileService;

    @RequiresPermissions("sys:tFile:tFile")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tFile/tFileList");
        return mv;
    }

    /**
     * 添加页面
     **/
    @RequiresPermissions("sys:tFile:add")
    @RequestMapping(value = "/tFileAdd",method = RequestMethod.GET)
    public ModelAndView tFileAdd() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tFile/tFileAdd");
        return mv;
    }



    @PostMapping("/insert")
    public RetResult<Integer> insert(TFile tFile) throws Exception{
    // tFile.setId(ApplicationUtils.getUUID());
    	Integer state = tFileService.insert(tFile);
        return RetResponse.makeOKRsp(state);
    }


    /**
    * 批量删除
    * @param ids [1,2,3]
    * @return
    */
    @RequiresPermissions("sys:tFile:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        Integer state = tFileService.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }

    @RequiresPermissions("sys:tFile:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(String id) throws Exception {
        Integer state = tFileService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(TFile tFile) throws Exception {
        Integer state = tFileService.update(tFile);
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/selectById")
    public RetResult<TFile> selectById(@RequestParam String id) throws Exception {
        TFile tFile = tFileService.selectById(id);
        return RetResponse.makeOKRsp(tFile);
    }


    @GetMapping("/getById")
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        TFile tFile =tFileService.selectById(id);
        mv.setViewName("views/system/tFile/tFileEdit");
        mv.addObject("tFile",tFile);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @Reutrn RetResult<PageInfo<TFile>>
	*/
    @GetMapping("/list")
    public RetResult<List<TFile>> list() throws Exception {
        List<TFile> list = tFileService.selectAll();
        return RetResponse.makeOKRsp(list);
    }


   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @GetMapping("/getAll")
    public LayuiResult<TFile> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<TFile> list = tFileService.getAll(map);
        PageInfo<TFile> pageInfo = new PageInfo<TFile>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}