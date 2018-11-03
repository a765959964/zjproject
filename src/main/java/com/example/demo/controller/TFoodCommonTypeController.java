package com.example.demo.controller;

import com.example.demo.core.constant.ProjectConstant;
import com.example.demo.dto.TreeDto;
import com.example.demo.service.TFoodCommonTypeService;
import com.santint.core.util.JSonUtils;
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
* @Description: TFoodCommonTypeController类
* @author zf
* @date 2018/11/02 13:45
*/
@RestController
@RequestMapping("/food")
public class TFoodCommonTypeController {

        @Resource
        private TFoodCommonTypeService tFoodCommonTypeService;




        /**
         * 生成菜系 JSON 文件存储到本地目录
         * 结构为
         * @param req
         * @return
         */
        @PostMapping("/getTreeJson")
        public List<TreeDto> getTreeDto(HttpServletRequest req){
            Map map = new HashMap<>();
            map.put("status",1);
           // map.put("level",1);
            //全部菜系文件
            List<TreeDto> listDto =  tFoodCommonTypeService.getTreeDto(map);
            String obj = JSonUtils.toJSon(listDto);

            List levelList = getLevel(listDto);     //获得1级文件夹

            List<TreeDto>  treeDtoList = getParenTreeDto(listDto);
            System.out.println("json 1级 2级 "+JSonUtils.toJSon(treeDtoList));



            for(int i=0;i<levelList.size();i++){
                String strPath = ProjectConstant.WINDOW_PATH+"/"+levelList.get(i).toString();
                File file  = new File(strPath);
                if(!file.exists()){
                    file.mkdirs();
                }
            }

            System.out.println("JSON文件"+obj);

            List<TreeDto> dyList =  getParent(listDto,"01");

            List<TreeDto> qtList =  getParent(listDto,"02");

            List<TreeDto> qzList =  getParent(listDto,"03");

            List<TreeDto> xcList =  getParent(listDto,"04");

            List<TreeDto> zcList =  getParent(listDto,"05");

            String dyObj = JSonUtils.toJSon(dyList);
            System.out.println("东亚文件"+dyObj);

            String qtObj = JSonUtils.toJSon(qtList);
            System.out.println("其它文件"+qtObj);

            String qzObj = JSonUtils.toJSon(qzList);
            System.out.println("清真文件"+qzObj);

            String xcObj = JSonUtils.toJSon(xcList);
            System.out.println("西餐文件"+xcObj);

            String zcObj = JSonUtils.toJSon(zcList);
            System.out.println("中餐文件"+zcObj);

         /*   try {
                FileOutputStream fos = new FileOutputStream(new File("D:/www/0.json"));//保存的地址路径
                OutputStreamWriter writer = new OutputStreamWriter(fos);
                writer.write(obj);
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            return listDto;
        }


        /**
         * 用于测试  生成文件目录
         * @param req
         * @return
         */
        @PostMapping("/getTest")
        public void getTest(HttpServletRequest req){
            createJson();   //生成根目录JSON 文件
            getParentLevel2();  //生成 1级目录 和2级目录
        }


        /**
         * 生成根目录 0.json 文件
         * @return
         */
        private  void createJson(){
            Map map = new HashMap<>();
            map.put("status",1);
            List<TreeDto> listDto =  tFoodCommonTypeService.getTreeDto(map);
            List<TreeDto> tdList = getParenTreeDto(listDto);
            String toObj = JSonUtils.toJSon(tdList);
            try {
                FileOutputStream fos = new FileOutputStream(new File(ProjectConstant.WINDOW_PATH+"/0.json"));//保存的地址路径
                OutputStreamWriter writer = new OutputStreamWriter(fos);
                writer.write(toObj);
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 菜系1级2级  和 json 文件夹
         * @return
         */
        private void  getParentLevel2(){
            Map map = new HashMap<>();
            map.put("status",1);
            map.put("noLevel",3);
            //全部菜系文件 不包括3级文件
            List<TreeDto> listDto =  tFoodCommonTypeService.getTreeDto(map);
            List<TreeDto> tdList = getParenTreeDto(listDto);

            //全部菜系文件
            Map params = new HashMap();
            params.put("status",1);
            List<TreeDto> listAll =  tFoodCommonTypeService.getTreeDto(params);

            //生成1级目录
            for(int  i=0;i<tdList.size();i++){      //求出 1级名称
                //生成 1级目录
                String strPath = ProjectConstant.WINDOW_PATH+"/"+tdList.get(i).getCode();
                File file  = new File(strPath);
                if(!file.exists()){
                    file.mkdirs();
                }
              //  System.out.println("1级菜单"+tdList.get(i).getName()+"=="+tdList.get(i).getCode());
                List<TreeDto> level2List =  getParent(listAll,tdList.get(i).getCode());
                String level2Json = JSonUtils.toJSon(level2List);   //需要生成的目录 0.json 文件
                //生成2级目录
                for(int j=0;j<tdList.get(i).getChildren().size();j++){     //求出 2级名称
                    String strPath2 = ProjectConstant.WINDOW_PATH+"/"+tdList.get(i).getCode()+"/"+tdList.get(i).getChildren().get(j).getCode();
                    File file2  = new File(strPath2);
                    if(!file2.exists()){
                        file2.mkdirs();
                    }
              //      System.out.println("2级菜单"+tdList.get(i).getChildren().get(j).getName()+"=="+tdList.get(i).getChildren().get(j).getCode()+"次数"+tdList.get(i).getChildren().size());
                }

                //生成2级  0.json 保存位置
                try {
                    File  jsonLevel2 = new File(ProjectConstant.WINDOW_PATH+"/"+tdList.get(i).getCode()+"/0.json");
                    FileOutputStream fos = new FileOutputStream(jsonLevel2);//保存的地址路径
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(level2Json);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
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
         * 获得 1级菜系 只求出 1级菜系 list
         * @return
         */
        private List getLevel(List<TreeDto> listDto){
                //全部菜系文件
                List list = new ArrayList();
                for ( TreeDto td :listDto){
                    if(td.getPcode().equals("0")){
                            list.add(td.getName());
                    }
                }
                return list;
        }

        /**
         * 获得1级菜系和2级菜系
         * @param listDto
         * @return
         */
        private List getLevel2(List<TreeDto> listDto){
            List list = new ArrayList();
            return list;
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


        @PostMapping("/getFoodFiledLevel")
        public List getFoodFiledLevel(HttpServletRequest req){
            Map map = new HashMap<>();
            map.put("status",1);
            //map.put("level",1);
            List list =  tFoodCommonTypeService.getFoodFiledLevel(map);
            String obj = JSonUtils.toJSon(list);
            System.out.println("JSON文件"+obj);

            try {
                FileOutputStream fos = new FileOutputStream(new File("D:/0.json"));//保存的地址路径
                OutputStreamWriter writer = new OutputStreamWriter(fos);
                writer.write(obj);
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


    //        System.out.println(JSonUtils.toJSon(list));
            return list;
        }


        /**
         * 创建文件夹
         * @param path  上传目录
         * @param parentPath  上传父目录
         */
         private void mkdirFile(String path,String parentPath,String fileName){
             try {

                 File file = null;
                 FileOutputStream fos = null;
                 OutputStreamWriter writer = null;
                if(parentPath!=""){
                     fos = new FileOutputStream(parentPath+"/"+path+"/");//保存的地址路径
                     writer = new OutputStreamWriter(fos);
                }else{
                    fos = new FileOutputStream(path+"/");//保存的地址路径
                    writer = new OutputStreamWriter(fos);
                }

                 writer.write(fileName);
                 writer.flush();
                 writer.close();
             } catch (Exception e) {
                 e.printStackTrace();
             }

        }


}