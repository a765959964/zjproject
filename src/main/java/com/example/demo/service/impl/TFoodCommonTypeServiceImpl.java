package com.example.demo.service.impl;

import com.example.demo.core.constant.Constant;
import com.example.demo.dao.TFoodCommonTypeMapper;
import com.example.demo.dto.TreeDto;
import com.example.demo.model.TFoodCommonType;
import com.example.demo.service.TFoodCommonTypeService;
import com.example.demo.core.universal.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.rabbitmq.tools.json.JSONUtil;
import com.santint.core.page.PageUtils;
import com.santint.core.util.JSonUtils;
import com.santint.core.web.query.QueryFilter;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: TFoodCommonTypeService接口实现类
* @author zf
* @date 2018/11/02 13:45
*/
@Service
public class TFoodCommonTypeServiceImpl extends AbstractService<TFoodCommonType> implements TFoodCommonTypeService {

    @Resource
    private TFoodCommonTypeMapper tFoodCommonTypeMapper;

    @Resource
    private Constant constant;

    @Override
    public List<TFoodCommonType> getAll(Map map){
        return tFoodCommonTypeMapper.getAll(map);
    }

    @Override
    public List<TFoodCommonType> getFoodFiledLevel(Map map) {
        return    tFoodCommonTypeMapper.getFoodFiledLevel(map);
    }

    @Override
    public List<TreeDto> getTreeDto(Map map) {
        return  tFoodCommonTypeMapper.getTreeDto(map);

    }


    /**
     * 删除生成目录下文件
     * @param path
     */
    private void delDir(String path){
        File dir=new File(path);
        if(dir.exists()){
            File[] tmp=dir.listFiles();
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].isDirectory()){
                    delDir(path+"/"+tmp[i].getName());
                }
                else{
                    tmp[i].delete();
                }
            }
            dir.delete();
        }
    }

    /**
     * 生成根目录 0.json 文件
     *
     * @return
     */
    public void createJson() {
        Map<String,List<TreeDto>> mapJson = new HashedMap();
        Map map = new HashMap<>();
        map.put("status", 1);
        List<TreeDto> listDto = this.getTreeDto(map);
        List<TreeDto> tdList = getParenTreeDto(listDto);
        mapJson.put("菜系",tdList);

        Map map1 = new HashMap<>();
        map1.put("status", 2);
        List<TreeDto> listDto2 = this.getTreeDto(map1);
        List<TreeDto> tdList2 = getParenTreeDto(listDto2);
        mapJson.put("食材",tdList2);

        Map map2 = new HashMap<>();
        map2.put("status", 3);
        List<TreeDto> listDto3 = this.getTreeDto(map2);
        List<TreeDto> tdList3 = getParenTreeDto(listDto3);
        mapJson.put("味型",tdList3);
        String ss = JSonUtils.toJSon(mapJson);
        //System.out.println(ss);
        try {
            File file2 = new File(constant.path());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(new File(constant.path() + "/0.json"));//保存的地址路径
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(ss);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
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
    public void getFoodTypeJson() {
        Map params = null;
        List<Map> listArray = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("status", 1);
        map.put("noLevel", 3);
        //全部菜系文件 不包括3级文件
        List<TreeDto> listDto = this.getTreeDto(map);
        List<TreeDto> tdList = getParenTreeDto(listDto);
        List xl = new ArrayList();
        xl.add("评分");
        xl.add("销量");
        xl.add("综合");
        xl.add("价格");
        //生成1级目录
        for (int i = 0; i < tdList.size(); i++) {      //求出 1级名称

            for (int j = 0; j < tdList.get(i).getChildren().size(); j++) {     //求出 2级名称
                String strPath2 = constant.path() + "/" + tdList.get(i).getCode() + "/" + tdList.get(i).getChildren().get(j).getCode() + "/0.json";
                params = new HashMap();
                String table = "";
                table = tdList.get(i).getChildren().get(j).getCode();
                Map p = new HashMap();
                p.put("table", table);
                List list = this.findByCode(p);
                params.put("CPSL", list.size() > 0 ? list.size() : 0);
                params.put("CPZL", tdList.get(i).getChildren().get(j).getName());
                params.put("CPZLID", tdList.get(i).getChildren().get(j).getCode());
                params.put("PXFS", xl);
                listArray.add(params);
                //创建  "评分",""销量,"综合","价格" 文件夹
                try {
                    for (int k = 0; k < xl.size(); k++) {
                        //D:/www/01/0101/['价格'，‘评分’,'销量','综合']
                        String pxPath = constant.path() + "/" + tdList.get(i).getCode() + "/" +
                                tdList.get(i).getChildren().get(j).getCode() + "/" + xl.get(k).toString();
                        //创建 降序文件夹 1
                        String pfPathDesc = constant.path() + "/" + tdList.get(i).getCode() + "/" +
                                tdList.get(i).getChildren().get(j).getCode() + "/" + xl.get(k).toString() + "/1";
                        File file2 = new File(pfPathDesc);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        //创建 升序文件夹2
                        String pfPath = constant.path() + "/" + tdList.get(i).getCode() + "/" +
                                tdList.get(i).getChildren().get(j).getCode() + "/" + xl.get(k).toString() + "/2";
                        File file3 = new File(pfPath);
                        if (!file3.exists()) {
                            file3.mkdirs();
                        }
                    }

                    // 创建 0.json 文件
                    String jsonFile = constant.path()+"/"+tdList.get(i).getCode()+"/"+tdList.get(i).getChildren().get(j).getCode();
                    FileOutputStream fos = new FileOutputStream(new File(jsonFile+"/0.json"));//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(JSonUtils.toJSon(params));
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                commonPx(list.size(), constant.pageSize(), 1, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "1");
                commonPx(list.size(), constant.pageSize(), 2, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "1");

                commonPx(list.size(), constant.pageSize(), 1, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "2");
                commonPx(list.size(), constant.pageSize(), 2, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "2");

                commonPx(list.size(), constant.pageSize(), 1, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "3");
                commonPx(list.size(), constant.pageSize(), 2, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "3");

                commonPx(list.size(), constant.pageSize(), 1, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "4");
                commonPx(list.size(), constant.pageSize(), 2, table, tdList.get(i).getCode(),tdList.get(i).getChildren().get(j).getCode(), "4");
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
                    List priceListDesc = this.findByCode(p1);
                    PageInfo pricePage = new PageInfo(priceListDesc);
                    System.out.println("升序排，第" + q + "页：" + JSonUtils.toJSon(pricePage.getList()));
                    getCommonPxJson(code,pcode,pricePage.getList(),filed,status,q+1);
                } else {  //q*pageSize+","+(q+1)*pageSize
                    p1.put("curPage", q * pageSize);
                    p1.put("pageSize", pageSize);
                    List priceList = this.findByCode(p1);
                    System.out.println("升序排,第" + q + "页==：" + JSonUtils.toJSon(priceList));
                    getCommonPxJson(code,pcode,priceList,filed,status,q+1);
                }
            }
        } else {
            p1.put("px", desc);
            for (int q = 0; q < totolPage; q++) {       //价格排序
                if (q == 0) {       // 0, page
                    PageHelper.startPage(q, pageSize);
                    List priceListDesc = this.findByCode(p1);
                    PageInfo pricePageDesc = new PageInfo(priceListDesc);
                    System.out.println("降序排，第" + q + "页：" + JSonUtils.toJSon(pricePageDesc.getList()));
                    getCommonPxJson(code,pcode,pricePageDesc.getList(),filed,status,q);
                } else {  //q*pageSize+","+(q+1)*pageSize
                    p1.put("curPage",q * pageSize);
                    p1.put("pageSize",pageSize);
                    List priceListDesc = this.findByCode(p1);
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
                    File file = new File(constant.path()+"/"+code+"/"+pcode+"/"+"价格/"+"1/"+i+".json");
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
                    File file = new File(constant.path()+"/"+code+"/"+pcode+"/"+"价格/"+"2/"+i+".json");
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
                    File file = new File(constant.path()+"/"+code+"/"+pcode+"/"+"评分/"+"1/"+i+".json");
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
                    File file = new File(constant.path()+"/"+code+"/"+pcode+"/"+"评分/"+"2/"+i+".json");
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
                    File file = new File(constant.path()+"/"+code+"/"+pcode+"/"+"销量/"+"1/"+i+".json");
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
                    File file = new File(constant.path()+"/"+code+"/"+pcode+"/"+"销量/"+"2/"+i+".json");
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
                    File file = new File(constant.path()+"/"+code+"/"+pcode+"/"+"综合/"+"1/"+i+".json");
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
                    File file = new File(constant.path()+"/"+code+"/"+pcode+"/"+"综合/"+"2/"+i+".json");
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
     * 获得排序值
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
     * 递归循环 生成0.json 文件
     * @param foodList
     * @return
     */
    private List<TreeDto> getParenTreeDto(List<TreeDto> foodList){
        List<TreeDto> tfList = new ArrayList<>();

        for (TreeDto tf  :foodList) {
            //查找根目录
            if(tf.getPcode().equals("0")){
                tfList.add(getChildrenTreeDto(tf,foodList));
            }
        }
        return tfList;

    }
    // 递归循环
    private TreeDto getChildrenTreeDto(TreeDto tf,List<TreeDto> foodList){
        for(TreeDto  ct:foodList){
            if(ct.getPcode().equals(tf.getCode())){
                if(tf.getChildren()==null){
                    tf.setChildren(new ArrayList<TreeDto>());
                }
                tf.getChildren().add(getChildrenTreeDto(ct,foodList));
            }
        }
        return tf;
    }

    @Override
    public List findByCode(Map map){
        return tFoodCommonTypeMapper.findByCode(map);
    }

    @Override
    public Integer findByCount(Map map) {
        return tFoodCommonTypeMapper.findByCount(map);
    }

    @Override
    public void createJsonAndFile() {
        delDir(constant.path());
        this.createJson();
        this.getFoodTypeJson();
    }
}