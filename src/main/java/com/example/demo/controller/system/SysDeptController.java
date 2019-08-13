package com.example.demo.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.utils.LayuiTree;
import com.example.demo.model.SysDept;
import com.example.demo.service.SysDeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.santint.core.util.JSonUtils;
import com.santint.core.web.query.QueryFilter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/sys/dept/")
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @RequiresPermissions("sys:dept:dept")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/dept/deptList");
        return mv;
    }

    @RequiresPermissions("sys:dept:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView deptAdd(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/dept/add");
        return mv;
    }

    @RequiresPermissions("sys:dept:add")
    @PostMapping("/insert")
    public RetResult<Integer> insert(SysDept sysDept) throws Exception{
    // sysDept.setId(ApplicationUtils.getUUID());
    	Integer state = sysDeptService.insert(sysDept);
        return RetResponse.makeOKRsp(state);
    }

    @RequiresPermissions("sys:dept:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysDeptService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }
    @RequiresPermissions("sys:dept:edit")
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

    @RequiresPermissions("sys:dept:add")
    @RequestMapping(value = "/getByIdAdd",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysDept sysDept =sysDeptService.selectById(id);
        mv.setViewName("views/system/user/deptAdd");
        mv.addObject("sysDept",sysDept);
        return mv;
    }

    @RequiresPermissions("sys:dept:edit")
    @RequestMapping(value = "/getByIdEdit",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getByIdEdit(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysDept sysDept =sysDeptService.selectById(id);
        mv.setViewName("views/system/user/deptEdit");
        mv.addObject("sysDept", sysDept);
        if(sysDept.getPid()==0){
           mv.addObject("pname","根目录");
        }else {
           SysDept sd = sysDeptService.selectById(sysDept.getPid().toString());
           mv.addObject("pname", sd.getName());
        }
        return mv;
    }



    @RequiresPermissions("sys:dept:dept")
    @RequestMapping("/treeList")
    @ResponseBody
    public List<SysDept> treeList() throws Exception {
        List<SysDept> list = sysDeptService.selectAll();
        return list;
    }

    @RequiresPermissions("sys:dept:dept")
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
    @RequiresPermissions("sys:dept:dept")
    @RequestMapping("/getTree")
    @ResponseBody
    public List<SysDept> getTree(){
       List<LayuiTree> layuiTreeList =new ArrayList<>();
       List<SysDept> sysdeptList =  sysDeptService.selectAll();
       return getParentTree(sysdeptList);
    }



    private List<SysDept> getList(List<SysDept> sysDeptList){
        List<SysDept> sdList = new ArrayList<>();
        for (SysDept sd:sysDeptList ) {
            //找到根
            if(sd.getPid()==0){
                sdList.add(sd);
            }
            for (SysDept sysDept:sysDeptList) {
                if(sysDept.getPid()==sd.getId()){
                    if(sd.getChildren()==null){
                        sd.setChildren(new ArrayList<SysDept>());
                    }
                    sd.getChildren().add(sysDept);
                }
            }
        }
     return sdList;
    }


    private  List<SysDept> getParentTree(List<SysDept> sysdeptList) {
        List<SysDept> layuiTreeList = new ArrayList<SysDept>();
        for(SysDept sysDept : sysdeptList ){
            if(sysDept.getPid()==0){
                layuiTreeList.add(findChildren(sysDept,sysdeptList));
            }
        }
        return layuiTreeList;
    }

    /**
     * 递归查找子节点
     * @param sysDept
     * @param
     * @return
     */
    private SysDept findChildren(SysDept sysDept,List<SysDept> sysdeptList){
            for(SysDept sd : sysdeptList){
                if(sysDept.getId()==sd.getPid()){
                    if(sysDept.getChildren()==null){
                        sysDept.setChildren(new ArrayList<SysDept>());
                    }
                    sysDept.getChildren().add(findChildren(sd,sysdeptList));
                }
            }
            return sysDept;
    }






    private  List<LayuiTree> getParentTree1(List<SysDept> sysdeptList){
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

   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequiresPermissions("sys:dept:dept")
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