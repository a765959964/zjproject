package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.core.utils.LayuiTree;
import com.example.demo.model.SysDept;
import com.example.demo.service.SysDeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.web.query.QueryFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @Description: SysDeptController类
* @author zf
* @date 2018/10/11 09:36
*/
@RestController
@RequestMapping("/sysdept")
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @RequestMapping(value = "/listView",method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/user/dept/deptList");
        return mv;
    }


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView deptAdd(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/user/dept/add");
        return mv;
    }


    @PostMapping("/insert")
    public RetResult<Integer> insert(SysDept sysDept) throws Exception{
    // sysDept.setId(ApplicationUtils.getUUID());
    	Integer state = sysDeptService.insert(sysDept);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysDeptService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(SysDept sysDept) throws Exception {
        Integer state = sysDeptService.update(sysDept);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<SysDept> selectById(@RequestParam String id) throws Exception {
        SysDept sysDept = sysDeptService.selectById(id);
        return RetResponse.makeOKRsp(sysDept);
    }


    @RequestMapping(value = "/getByIdAdd",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysDept sysDept =sysDeptService.selectById(id);
        mv.setViewName("views/user/user/deptAdd");
        mv.addObject("sysDept",sysDept);
        return mv;
    }


    @RequestMapping(value = "/getByIdEdit",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getByIdEdit(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysDept sysDept =sysDeptService.selectById(id);
        mv.setViewName("views/user/user/deptEdit");
        mv.addObject("sysDept", sysDept);
        if(sysDept.getPid()==0){
           mv.addObject("pname","无");
        }else {
           SysDept sd = sysDeptService.selectById(sysDept.getPid().toString());
           mv.addObject("pname", sd.getName());
        }
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<SysDept>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<SysDept>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SysDept> list = sysDeptService.selectAll();
        PageInfo<SysDept> pageInfo = new PageInfo<SysDept>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }

    @RequestMapping("/treeList")
    @ResponseBody
    public List<SysDept> treeList() throws Exception {
        List<SysDept> list = sysDeptService.selectAll();
        return list;
    }


    @RequestMapping("/getTreeData")
    @ResponseBody
    public LayuiResult<SysDept> getTreeData(HttpServletRequest request){

        QueryFilter filter = new QueryFilter(request);
        List<SysDept> list = sysDeptService.getTreeData();
        PageInfo<SysDept> pageInfo = new PageInfo<SysDept>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }

    /**
     * layui demo
     * @return
     */
    @RequestMapping("/getTree")
    @ResponseBody
    public List<LayuiTree> getTree(){
       List<LayuiTree> layuiTreeList =new ArrayList<>();
       List<SysDept> sysdeptList =  sysDeptService.selectAll();
       layuiTreeList = getParentTree(sysdeptList);
       return layuiTreeList;
    }


    private  List<LayuiTree> getParentTree(List<SysDept> sysdeptList){
        List<LayuiTree>  layuiTreeList = new ArrayList<>();
        List<LayuiTree>  ltList = new ArrayList<>();
        LayuiTree layuiTree  = new LayuiTree();
        for (SysDept sysdept: sysdeptList) {
            if(sysdept.getPid()==0){
                layuiTree.setId(sysdept.getId());
                layuiTree.setName(sysdept.getName());
                layuiTree.setpId(0);
                layuiTree.setChildren(layuiTreeList);
            }else if(sysdept.getPid().equals(layuiTree.getId())){
                LayuiTree lt = new LayuiTree();
                lt.setId(sysdept.getId());
                lt.setName(sysdept.getName());
                lt.setpId(sysdept.getPid());
                ltList.add(lt);
                layuiTree.setChildren(ltList);
            }
        }
        layuiTreeList.add(layuiTree);
        return layuiTreeList;
    }

    /*
    private  List<LayuiTree> getParentTree(List<SysDept> sysdeptList){
        List<LayuiTree>  layuiTreeList = new ArrayList<>();
        List<LayuiTree>  ltList = new ArrayList<>();
        LayuiTree layuiTree  = new LayuiTree();
        for (SysDept sysdept: sysdeptList) {
            if(sysdept.getPid()==0){
                layuiTree.setId(sysdept.getId());
                layuiTree.setName(sysdept.getName());
                layuiTreeList.add(layuiTree);
                layuiTree.setChildren(layuiTreeList);
            }else if(sysdept.getPid().equals(layuiTree.getId())){
                getParentTree(sysdeptList);
            }
        }
        return layuiTreeList;
    }*/



   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<SysDept> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<SysDept> list = sysDeptService.getAll(map);
        PageInfo<SysDept> pageInfo = new PageInfo<SysDept>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}