package com.zhejiang.manage.controller;

import com.zhejiang.manage.util.DateUtils;
import com.zhejiang.manage.util.FileUtil;
import com.zhejiang.manage.util.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author durantjiang
 */
@RestController
@RequestMapping("/files")
public class FileUploadController {
    /**
     * 处理文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResultVo uploadImg(@RequestParam(value = "file") MultipartFile file,@RequestParam("type") String type) {
        ResultVo resultVo = new ResultVo(true);
        String fileName = file.getOriginalFilename();

        String filePath = System.getProperty("user.dir")+"/files";

        if ("经验做法".equals(type)){
            filePath = filePath+"/jingyanzuofa";
        }else if ("问题建议".equals(type)){
            filePath = filePath+"/wentijianyi";
        }else if ("认识体会".equals(type)){
            filePath = filePath+"/renshitihui";
        }else if ("人物典型".equals(type)){
            filePath = filePath+"/renwudianxing";
        }else if ("其他".equals(type)){
            filePath = filePath+"/qita";
        }
        fileName = DateUtils.dateToString(new Date(),DateUtils.DATE_TIME_FORMAT).replaceAll("-","").replaceAll(":","").replaceAll(" ","")+"-"+fileName;

        //调用文件处理类FileUtil，处理文件，将文件写入指定位置
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            resultVo.setStatus(false);
            return resultVo;
        }

        resultVo.setData(filePath+"/"+fileName);

       // 返回图片的存放路径
        return resultVo;
    }

    @GetMapping("/download")
    public void download(String url, HttpServletResponse response){
        if (!StringUtils.isEmpty(url)){
            File file = new File(url);
            String name =  url.substring(url.lastIndexOf("/")+1);
            String name1 = name.substring(name.indexOf("-")+1);

            if (file.exists()){
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment;fileName=" + name1);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;

                OutputStream os = null;
                try {
                    os = response.getOutputStream();
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    int i = bis.read(buffer);
                    while(i != -1){
                        os.write(buffer);
                        i = bis.read(buffer);
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    bis.close();
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}