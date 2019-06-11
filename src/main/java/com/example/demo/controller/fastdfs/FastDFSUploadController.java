package com.example.demo.controller.fastdfs;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.core.fastdfs.FastDFSClient;
import com.example.demo.core.fastdfs.FastDFSFile;

/**
 * fastdfs 控制类
 * @author 张帆
 * @date 2019-4-22 15:17:01
 *
 */
@RequestMapping("/fastdfs")
@Controller
public class FastDFSUploadController {
    private static Logger logger = LoggerFactory.getLogger(FastDFSUploadController.class);

    @GetMapping("/")
    public String index() {
        return "upload";
    }
    
    
    /**
     * 下载文件
     * @param fileName 文件名称
     * @param response
     */
    @GetMapping("/download")
    public void dowload(String fileName,HttpServletResponse response) {
         InputStream inputStream = FastDFSClient.downFile("group1", "M00/00/00/"+fileName);
         // 设置输出的格式
         response.reset();
         response.setContentType("bin");
         response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
         // 循环取出流中的数据
         byte[] b = new byte[100];
         int len;
         try {
             while ((len = inputStream.read(b)) > 0)
                 response.getOutputStream().write(b, 0, len);
             	inputStream.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
    
    
    
    
    /**
     * 删除文件
     * @param fileName 文件名称
     * @return
     * @throws Exception
     */
    @GetMapping("/delFile")
    @ResponseBody
    public Map<String, String> delFile(String fileName) throws Exception{
    	Map<String,String> params = new HashMap<String, String>();
    	FastDFSClient.deleteFile("group1", "M00/00/00/"+fileName);
		params.put("code", "200");
		params.put("msg", "删除成功");
    	return params;
    }
    
    
    /**
     * 文件上传
     * @param file 
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/upload") //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/fastdfs/uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            String path=saveFile(file,redirectAttributes);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path",path);
        } catch (Exception e) {
            logger.error("upload file failed",e);
        }
        return "redirect:/fastdfs/uploadStatus";
    }
    
    
    /**
     * 上传文件 接口，返回文件路径，文件名
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadFile") 
    @ResponseBody
    public Map uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
    	Map map = new HashMap();
    	String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            logger.error("upload file failed,please upload again!");
        }
        
        String path=FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        map.put("code", 200);
        map.put("path",path );
        map.put("fileName", fileAbsolutePath[1].substring(10));
        return map;
     
    }
    
    
    

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    /**
     * 保存上传文件
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile,RedirectAttributes redirectAttributes) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            logger.error("upload file failed,please upload again!");
        }
        
        
        redirectAttributes.addFlashAttribute("fileName", fileAbsolutePath[1].substring(10));
        String path=FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return path;
    }
    
    
    
    
    
    
    
    
    
}