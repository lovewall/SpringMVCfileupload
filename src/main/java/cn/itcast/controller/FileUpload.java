package cn.itcast.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author chestnut
 * @creat 2020-03-08 10:46 下午
 */
@RequestMapping("/user")
@Controller
public class FileUpload {
    @RequestMapping("/fileupload1")
    public String fileupload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传1。。。");
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
            System.out.println("生成了文件夹");
        }
        System.out.println("跳过了文件夹");
//        解析request，获得文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
//        遍历for循环
        for (FileItem item:items){
            if (item.isFormField()){
//            普通表单项
                System.out.println("formfield");
            }else {
//            上传文件项
                String filename = item.getName();
                item.write(new File(path,filename));
                System.out.println("存放成功");
                item.delete();
            }
        }
        return "success";
    }
    @RequestMapping("/fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springmvc方式上传文件。。。");
        String path=request.getSession().getServletContext().getRealPath("/uploads/");
        File file=new File((path));
        if (file.exists()){
            file.mkdirs();
        }
        String filename=upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        filename=uuid+"_"+filename;
        upload.transferTo(new File(file,filename));
        return "success";
    }

}
