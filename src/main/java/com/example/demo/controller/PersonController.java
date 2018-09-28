package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: PersonController类
* @author zf
* @date 2018/09/26 16:23
*/
@Controller
@RequestMapping("/person")
public class PersonController {

    @Resource
    private PersonService personService;

    @PostMapping("/insert")
    @ResponseBody
    public RetResult<Integer> insert(Person person) throws Exception{
//		person.setId(Long.valueOf(ApplicationUtils.getUUID()));
    	Integer state = personService.insert(person);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = personService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    @ResponseBody
    public RetResult<Integer> update(Person person) throws Exception {
        Integer state = personService.update(person);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    @ResponseBody
    public RetResult<Person> selectById(@RequestParam String id) throws Exception {
        Person person = personService.selectById(id);
        return RetResponse.makeOKRsp(person);
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        Person person = personService.selectById(id);
        mv.setViewName("views/person/personEdit");
        mv.addObject("person",person);
        return mv;
    }



    /**
	* @Description: 分页查询
	* @param page 页码
	* @param limit 每页条数
	* @Reutrn RetResult<PageInfo<Person>>
	*/
    @PostMapping("/list")
    @ResponseBody
    public RetResult<PageInfo<Person>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer limit) throws Exception {
        PageHelper.startPage(page, limit);
        List<Person> list = personService.selectAll();
        PageInfo<Person> pageInfo = new PageInfo<Person>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }

    /**
     * lay ui 分页
     * @param page 当前页
     * @param limit 每页条数
     * @return
     */
    @RequestMapping("/listJson")
    @ResponseBody
    public LayuiResult<Person> getAll(@RequestParam(defaultValue = "0") Integer page,
                                      @RequestParam(defaultValue = "0") Integer limit){
        PageHelper.startPage(page, limit);
        List<Person> list = personService.selectAll();
        PageInfo<Person> pageInfo = new PageInfo<Person>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }
}