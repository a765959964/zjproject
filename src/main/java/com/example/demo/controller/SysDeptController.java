package com.example.demo.controller;

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
           mv.addObject("pname","根目录");
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

       //System.out.println("list循环"+JSonUtils.toJSon(getList(sysdeptList)));
       System.out.println("递归"+JSonUtils.toJSon(getParentTree(sysdeptList)));
      // List<SysDept> list  = getParentTree(sysdeptList);
      // System.out.println(JSonUtils.toJSon("JSON:"+list));
       return layuiTreeList;
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



    /**
     * 求出父部门
     * @param sysdeptList
     * @return
     */
   /* private  List<LayuiTree> getParentTree(List<SysDept> sysdeptList){
        List<LayuiTree>  layuiTreeList = new ArrayList<>();
        LayuiTree layuiTree = null;
        for (SysDept sysdept: sysdeptList) {
            layuiTree = new LayuiTree();
            if(sysdept.getPid()==0) {
                layuiTree.setId(sysdept.getId());
                layuiTree.setName(sysdept.getName());
                layuiTree.setpId(0);
                layuiTreeList.add(layuiTree);
            }
        }
        return layuiTreeList;
    }
*/
    /**
     * 求出 父级和 子级
     * @param sysdeptList
     * @return
     */
    private  List<LayuiTree> getChildren(List<SysDept> sysdeptList){
        List<LayuiTree> list = null;
        //List<LayuiTree> parentList = getParentTree(sysdeptList);    //父级list
        JSONArray jsonArray = new JSONArray();
        JsonObject jsonObject = new JsonObject();
        /*List<LayuiTree> list = new ArrayList<>();
        List<LayuiTree> parentList = getParentTree(sysdeptList);    //父级list
        List<LayuiTree> childrenList = new ArrayList<>();
        LayuiTree layuiTree  =   null;
        for(LayuiTree parent : parentList){     //先循环父级list
            for( SysDept sysDept : sysdeptList){    //在循环全部list
                    if(sysDept.getPid()==parent.getId()){   //判断子级是否不等于父级
                        layuiTree  =   new LayuiTree();
                        layuiTree.setId(sysDept.getId());
                        layuiTree.setpId(sysDept.getPid());
                        layuiTree.setName(sysDept.getName());
                        childrenList.add(layuiTree);
                        layuiTree.setChildren(childrenList);
                    }else{
                        layuiTree  =   new LayuiTree();
                        layuiTree.setId(parent.getId());
                        layuiTree.setName(parent.getName());
                        layuiTree.setpId(parent.getpId());
                        list.add(layuiTree);
                    }

             }

        }
      */
        return list;
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