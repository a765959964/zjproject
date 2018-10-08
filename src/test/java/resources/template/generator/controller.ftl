package ${basePackageController};

import com.example.demo.core.ret.LayuiResult;
import ${basePackage}.core.ret.RetResult;
import ${basePackage}.core.ret.RetResponse;
import ${basePackage}.core.utils.ApplicationUtils;
import ${basePackageModel}.${modelNameUpperCamel};
import ${basePackageService}.${modelNameUpperCamel}Service;
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
import java.util.HashMap;
import java.util.List;

/**
* @Description: ${modelNameUpperCamel}Controller类
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/insert")
    public RetResult<Integer> insert(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception{
    // ${modelNameLowerCamel}.setId(ApplicationUtils.getUUID());
    	Integer state = ${modelNameLowerCamel}Service.insert(${modelNameLowerCamel});
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<${modelNameUpperCamel}> selectById(@RequestParam String id) throws Exception {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return RetResponse.makeOKRsp(${modelNameLowerCamel});
    }


    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        ${modelNameUpperCamel} ${modelNameLowerCamel} =${modelNameLowerCamel}Service.selectById(id);
        mv.setViewName("views/${modelNameLowerCamel}/${modelNameLowerCamel}Edit");
        mv.addObject("${modelNameLowerCamel}",${modelNameLowerCamel});
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<${modelNameUpperCamel}>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<${modelNameUpperCamel}>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.selectAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }


   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<${modelNameUpperCamel}> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.getAll(map);
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}