package com.example.demo.controller;

import com.example.demo.core.constant.ProjectConstant;
import com.example.demo.dto.TreeDto;
import com.example.demo.service.PersonService;
import com.example.demo.service.TFoodCommonTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.page.PageUtils;
import com.santint.core.util.JSonUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: JSON 文件生成类
* @author zf
* @date 2018/11/02 13:45
*/
@RestController
@RequestMapping("/food")
public class TFoodCommonTypeController {

    @Resource
    private TFoodCommonTypeService tFoodCommonTypeService;


    @PostMapping("/demo")
    public void Demo() {

      /*  Map params = new HashMap();
        createJson();
        Integer page = PageUtils.getTotalPage(31, 10);*/
        String desc = "desc";
        String asc = "asc";
        Map p1 = new HashMap();
        p1.put("table", "0101");
        p1.put("code", "0101");
        p1.put("field", "price");
        Integer count =  tFoodCommonTypeService.findByCount(p1);
        System.out.println("总数量:"+count);
        Map p = new HashMap();
        p.put("table", "0101");
        p.put("field", "price");
        p.put("px", "desc");
        Integer pageSize = 2 ;

        Integer totalPage = PageUtils.getTotalPage(count, pageSize);
        for (int q = 0; q <totalPage; q++) {
            if(q==0){
                PageHelper.startPage((q-1)*pageSize,pageSize);
                List listAsc = tFoodCommonTypeService.findByCode(p);
                PageInfo page = new PageInfo(listAsc);
                System.out.println("第"+q+"次"+JSonUtils.toJSon(page.getList()));
            }else{
                p.put("curPage",q * pageSize);
                p.put("pageSize",pageSize);
                List listAsc = tFoodCommonTypeService.findByCode(p);
                System.out.println("第"+ q + "次"+JSonUtils.toJSon(listAsc));
            }

        }
    }

    /**
     * 用于测试  生成文件目录
     *
     * @param req
     * @return
     */
    @PostMapping("/getTest")
    public void getTest(HttpServletRequest req) {
         createJson();   //生成根目录JSON 文件
         getFoodTypeJson();  //生成json文件


    }


    /**
     * 生成根目录 0.json 文件
     *
     * @return
     */
    private void createJson() {
        Map<String,List<TreeDto>> mapJson = new HashedMap();
        Map map = new HashMap<>();
        map.put("status", 1);
        List<TreeDto> listDto = tFoodCommonTypeService.getTreeDto(map);
        List<TreeDto> tdList = getParenTreeDto(listDto);
        mapJson.put("菜系",tdList);

        Map map1 = new HashMap<>();
        map1.put("status", 2);
        List<TreeDto> listDto2 = tFoodCommonTypeService.getTreeDto(map1);
        List<TreeDto> tdList2 = getParenTreeDto(listDto2);
        mapJson.put("食材",tdList2);

        Map map2 = new HashMap<>();
        map2.put("status", 3);
        List<TreeDto> listDto3 = tFoodCommonTypeService.getTreeDto(map2);
        List<TreeDto> tdList3 = getParenTreeDto(listDto3);
        mapJson.put("味型",tdList3);
        String ss = JSonUtils.toJSon(mapJson);
        System.out.println(ss);

        try {
            FileOutputStream fos = new FileOutputStream(new File(ProjectConstant.WINDOW_PATH + "/0.json"));//保存的地址路径
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(ss);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 菜系1级2级文件夹
     *
     * @return
     */
    private void getParentLevel2() {
        Map map = new HashMap<>();
        map.put("status", 1);
        map.put("noLevel", 3);
        //全部菜系文件 不包括3级文件
        List<TreeDto> listDto = tFoodCommonTypeService.getTreeDto(map);
        List<TreeDto> tdList = getParenTreeDto(listDto);

        //生成1级目录
        for (int i = 0; i < tdList.size(); i++) {      //求出 1级名称
            //生成 1级目录
            String strPath = ProjectConstant.WINDOW_PATH + "/" + tdList.get(i).getCode();
            File file = new File(strPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //生成2级目录
            for (int j = 0; j < tdList.get(i).getChildren().size(); j++) {     //求出 2级名称
                String strPath2 = ProjectConstant.WINDOW_PATH + "/" + tdList.get(i).getCode() + "/" + tdList.get(i).getChildren().get(j).getCode();
                File file2 = new File(strPath2);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
            }
        }
    }


    /**
     * 生成菜品下 0.json 文件
     * 获取每个分类下   分类排序方式
     * CPSL=菜品数量
     * CPZL=分类名称
     * CPZLID = 分类编号
     * PXFS = ["评分",""销量,"综合","价格"]
     */
    private void getFoodTypeJson() {

        Map params = null;
        List<Map> listArray = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("status", 1);
        map.put("noLevel", 3);
        //全部菜系文件 不包括3级文件
        List<TreeDto> listDto = tFoodCommonTypeService.getTreeDto(map);
        List<TreeDto> tdList = getParenTreeDto(listDto);
        List xl = new ArrayList();
        xl.add("评分");
        xl.add("销量");
        xl.add("综合");
        xl.add("价格");
        //生成1级目录
        for (int i = 0; i < tdList.size(); i++) {      //求出 1级名称

            for (int j = 0; j < tdList.get(i).getChildren().size(); j++) {     //求出 2级名称
                String strPath2 = ProjectConstant.WINDOW_PATH + "/" + tdList.get(i).getCode() + "/" + tdList.get(i).getChildren().get(j).getCode() + "/0.json";
                params = new HashMap();
                String table = "";
                table = tdList.get(i).getChildren().get(j).getCode();
                Map p = new HashMap();
                p.put("table", table);
                List list = tFoodCommonTypeService.findByCode(p);
                params.put("CPSL", list.size() > 0 ? list.size() : 0);
                params.put("CPZL", tdList.get(i).getChildren().get(j).getName());
                params.put("CPZLID", tdList.get(i).getChildren().get(j).getCode());
                params.put("PXFS", xl);
                listArray.add(params);
                //创建  "评分",""销量,"综合","价格" 文件夹
                try {
                    for (int k = 0; k < xl.size(); k++) {
                        //D:/www/01/0101/['价格'，‘评分’,'销量','综合']
                        String pxPath = ProjectConstant.WINDOW_PATH + "/" + tdList.get(i).getCode() + "/" +
                                tdList.get(i).getChildren().get(j).getCode() + "/" + xl.get(k).toString();
                        //创建 降序文件夹 1
                        String pfPathDesc = ProjectConstant.WINDOW_PATH + "/" + tdList.get(i).getCode() + "/" +
                                tdList.get(i).getChildren().get(j).getCode() + "/" + xl.get(k).toString() + "/1";
                        File file2 = new File(pfPathDesc);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        //创建 升序文件夹2
                        String pfPath = ProjectConstant.WINDOW_PATH + "/" + tdList.get(i).getCode() + "/" +
                                tdList.get(i).getChildren().get(j).getCode() + "/" + xl.get(k).toString() + "/2";
                        File file3 = new File(pfPath);
                        if (!file3.exists()) {
                            file3.mkdirs();
                        }
                    }

                    // 创建 0.json 文件
                    String jsonFile = ProjectConstant.WINDOW_PATH+"/"+tdList.get(i).getCode()+"/"+tdList.get(i).getChildren().get(j).getCode();
                    FileOutputStream fos = new FileOutputStream(new File(jsonFile+"/0.json"));//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(JSonUtils.toJSon(params));
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                commonPx(list.size(), 2, 1, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "1");
                commonPx(list.size(), 2, 2, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "1");

                commonPx(list.size(), 2, 1, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "2");
                commonPx(list.size(), 2, 2, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "2");

                commonPx(list.size(), 2, 1, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "3");
                commonPx(list.size(), 2, 2, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "3");

                commonPx(list.size(), 2, 1, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "4");
                commonPx(list.size(), 2, 2, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "4");
            }
        }

    }


    /**
     * 通用排序方法
     *
     * @param totalPage 总记录数
     * @param pageSize  每页条数
     * @param status    1升序 2 降序
     * @param table     菜系表
     * @param code 编码
     * @param pcode  父编码
     * @param filed     排序字段
     *
     */
    private void commonPx(int totalPage, Integer pageSize, Integer status, String table,String code,String pcode,String filed) {
        String desc = "desc";
        String asc = "asc";
        Map p1 = new HashMap();
        p1.put("table", table);
        p1.put("code", table);
        p1.put("field", getFieldValue(filed));
        Integer totolPage = PageUtils.getTotalPage(totalPage, pageSize);
        if (status == 1) {
            for (int q = 0; q < totolPage; q++) {       //价格排序
                if (q == 0) {       // 0, page
                    PageHelper.startPage(q, pageSize);
                    List priceListDesc = tFoodCommonTypeService.findByCode(p1);
                    PageInfo pricePage = new PageInfo(priceListDesc);
                    System.out.println("升序排，第" + q + "页：" + JSonUtils.toJSon(pricePage.getList()));
                    getCommonPxJson(code,pcode,pricePage.getList(),filed,status,q+1);
                } else {  //q*pageSize+","+(q+1)*pageSize
                    p1.put("curPage", q * pageSize);
                    p1.put("pageSize", pageSize);
                    List priceList = tFoodCommonTypeService.findByCode(p1);
                    System.out.println("升序排,第" + q + "页==：" + JSonUtils.toJSon(priceList));
                    getCommonPxJson(code,pcode,priceList,filed,status,q+1);
                }
            }
        } else {
            p1.put("px", desc);
            for (int q = 0; q < totolPage; q++) {       //价格排序
                if (q == 0) {       // 0, page
                    PageHelper.startPage(q, pageSize);
                    List priceListDesc = tFoodCommonTypeService.findByCode(p1);
                    PageInfo pricePageDesc = new PageInfo(priceListDesc);
                    System.out.println("降序排，第" + q + "页：" + JSonUtils.toJSon(pricePageDesc.getList()));
                    getCommonPxJson(code,pcode,pricePageDesc.getList(),filed,status,q);
                } else {  //q*pageSize+","+(q+1)*pageSize
                    p1.put("curPage",q * pageSize);
                    p1.put("pageSize",pageSize);
                    List priceListDesc = tFoodCommonTypeService.findByCode(p1);
                    System.out.println("降序排,第" + q + "页==：" + JSonUtils.toJSon(priceListDesc));
                    getCommonPxJson(code,pcode,priceListDesc,filed,status,q);
                }
            }
        }
    }


    /**
     * 通用生成 分页 json 文件
     * @param code 编码
     * @param pcode 父编码
     * @param list  list 值
     * @param filed 代表 1  2  3 4
     * @param i  多少页
     * @param  status 排序类型 1 升序 2 倒序
     */
    private void getCommonPxJson(String code,String pcode,List list,String filed,Integer status,int i){
        String obj = JSonUtils.toJSon(list);    //json 文件

        if(filed.equals("1")){  //价格
            if(status==1){
                try {
                    File file = new File(ProjectConstant.WINDOW_PATH+"/"+code+"/"+pcode+"/"+"价格/"+"1/"+i+".json");
                    FileOutputStream fos = new FileOutputStream(file);//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(obj);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    File file = new File(ProjectConstant.WINDOW_PATH+"/"+code+"/"+pcode+"/"+"价格/"+"2/"+i+".json");
                    FileOutputStream fos = new FileOutputStream(file);//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(obj);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }else if (filed.equals("2")){
            if(status==1){
                try {
                    File file = new File(ProjectConstant.WINDOW_PATH+"/"+code+"/"+pcode+"/"+"评分/"+"1/"+i+".json");
                    FileOutputStream fos = new FileOutputStream(file);//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(obj);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    File file = new File(ProjectConstant.WINDOW_PATH+"/"+code+"/"+pcode+"/"+"评分/"+"2/"+i+".json");
                    FileOutputStream fos = new FileOutputStream(file);//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(obj);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }else if(filed.equals("3")){
            if(status==1){
                try {
                    File file = new File(ProjectConstant.WINDOW_PATH+"/"+code+"/"+pcode+"/"+"销量/"+"1/"+i+".json");
                    FileOutputStream fos = new FileOutputStream(file);//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(obj);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    File file = new File(ProjectConstant.WINDOW_PATH+"/"+code+"/"+pcode+"/"+"销量/"+"2/"+i+".json");
                    FileOutputStream fos = new FileOutputStream(file);//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(obj);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }else if(filed.equals("4")){
            if(status==1){
                try {
                    File file = new File(ProjectConstant.WINDOW_PATH+"/"+code+"/"+pcode+"/"+"综合/"+"1/"+i+".json");
                    FileOutputStream fos = new FileOutputStream(file);//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(obj);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    File file = new File(ProjectConstant.WINDOW_PATH+"/"+code+"/"+pcode+"/"+"综合/"+"2/"+i+".json");
                    FileOutputStream fos = new FileOutputStream(file);//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(obj);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

    /**
     *
     * @param px
     * @return
     */
    private  String getFieldValue(String px){
        String field = "";
        switch (px){
            case "1":
                field = "price";
                break;
            case "2":
                field = "score";
                break;
            case "3":
                field = "sales";
                break;
            case "4":
                field = "global";
                break;
        }
        return field;
    }





        /**
         * 递归循环
         * @param foodList
         * @return
         */
        private List<TreeDto> getParenTreeDto(List<TreeDto> foodList){
                List<TreeDto> tfList = new ArrayList<>();

                for (TreeDto tf  :foodList) {
                    //查找根目录
                    if(tf.getPcode().equals("0")){
                        tfList.add(getChildrenDto(tf,foodList));
                    }
                }
                return tfList;

        }
        // 递归循环
        private TreeDto getChildrenDto(TreeDto tf,List<TreeDto> foodList){
            for(TreeDto  ct:foodList){
                if(ct.getPcode().equals(tf.getCode())){
                    if(tf.getChildren()==null){
                        tf.setChildren(new ArrayList<TreeDto>());
                    }
                    tf.getChildren().add(getChildrenDto(ct,foodList));
                }
            }
            return tf;
        }

        /**
         * 根据指定编号生成 .json文件
         * @param listDto
         * @return
         */
        private List<TreeDto> getParent(List<TreeDto> listDto,String code){
            List<TreeDto> treeDtoList = new ArrayList<>();
            for(TreeDto td :listDto){
                if(td.getPcode().equals(code)){
                    treeDtoList.add(getChildren(td,listDto));
                }
            }
            return treeDtoList;
        }

        private TreeDto getChildren(TreeDto td, List<TreeDto> listDto) {
            for(TreeDto dto:listDto){
                if(dto.getPcode().equals(td.getCode())){
                    if(td.getChildren()==null){
                        td.setChildren(new ArrayList<>());
                    }
                    td.getChildren().add(getChildren(dto,listDto));
                }
            }
            return td;
        }

}