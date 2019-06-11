package ${basePackageController};

import com.example.demo.core.aop.AnnotationLog;
import com.example.demo.core.ret.LayuiResult;
import ${basePackage}.core.ret.RetResult;
import ${basePackage}.core.ret.RetResponse;
import ${basePackage}.core.utils.ApplicationUtils;
import ${basePackageModel}.${modelNameUpperCamel};
import ${basePackageService}.${modelNameUpperCamel}Service;
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
* @Description: ${modelNameUpperCamel}Controller类
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/sys/${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @AnnotationLog("进入页面")
    @RequiresPermissions("sys:${modelNameLowerCamel}:${modelNameLowerCamel}")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/${modelNameLowerCamel}/${modelNameLowerCamel}List");
        return mv;
    }

    /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequiresPermissions("sys:${modelNameLowerCamel}:${modelNameLowerCamel}")
    @GetMapping("/getAll")
    public LayuiResult<${modelNameUpperCamel}> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.getAll(map);
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


    /**
     * 添加页面
     **/
    @RequiresPermissions("sys:${modelNameLowerCamel}:add")
    @RequestMapping(value = "/${modelNameLowerCamel}Add",method = RequestMethod.GET)
    public ModelAndView ${modelNameLowerCamel}Add() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/${modelNameLowerCamel}/${modelNameLowerCamel}Add");
        return mv;
    }

    @AnnotationLog("添加操作")
    @RequiresPermissions("sys:${modelNameLowerCamel}:add")
    @PostMapping("/insert")
    public RetResult<Integer> insert(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception{
    // ${modelNameLowerCamel}.setId(ApplicationUtils.getUUID());
    	Integer state = ${modelNameLowerCamel}Service.insert(${modelNameLowerCamel});
        return RetResponse.makeOKRsp(state);
    }


    /**
    * 批量删除
    * @param ids [1,2,3]
    * @return
    */
    @AnnotationLog("进行批量删除")
    @RequiresPermissions("sys:${modelNameLowerCamel}:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        Integer state = ${modelNameLowerCamel}Service.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("进行删除操作")
    @RequiresPermissions("sys:${modelNameLowerCamel}:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @RequiresPermissions("sys:${modelNameLowerCamel}:edit")
    @GetMapping("/getById")
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        ${modelNameUpperCamel} ${modelNameLowerCamel} =${modelNameLowerCamel}Service.selectById(id);
        mv.setViewName("views/system/${modelNameLowerCamel}/${modelNameLowerCamel}Edit");
        mv.addObject("${modelNameLowerCamel}",${modelNameLowerCamel});
        return mv;
    }

    @AnnotationLog("更新操作")
    @RequiresPermissions("sys:${modelNameLowerCamel}:edit")
    @PostMapping("/update")
    public RetResult<Integer> update(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/selectById")
    public RetResult<${modelNameUpperCamel}> selectById(@RequestParam String id) throws Exception {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return RetResponse.makeOKRsp(${modelNameLowerCamel});
    }



   /**
	* @Description: 分页查询
	* @Reutrn RetResult<PageInfo<${modelNameUpperCamel}>>
	*/
    @GetMapping("/list")
    public RetResult<List<${modelNameUpperCamel}>> list() throws Exception {
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.selectAll();
        return RetResponse.makeOKRsp(list);
    }





}