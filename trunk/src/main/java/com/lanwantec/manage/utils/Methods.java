package com.lanwantec.manage.utils;

import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Methods {

    /**
     * 上传图片的路径
     * @param
     * @return
     */
    public static String uploadPath(HttpSession session, String type) {
        String folder = createNewFolder();
        String rootPath = "target/classes/static/";
        String path = rootPath;
        if (type.equals("img")) {
            path += Constants.UPLOAD_IMAGES + folder;
        }
        path += "/";
        File file = new File(path);
        if (file.mkdirs()) {
            file.mkdirs();
        }
        return "/"+Constants.UPLOAD_IMAGES + folder+file.getName();
    }

    /**
     * 根据当前日期自动创建存放文件的文件夹
     * @return
     */
    public static String createNewFolder() {
        return new  SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 截取文件后缀
     * @param fileName
     * @return
     */
    public static String interceptFileSuffix(String fileName) {
        return "." + fileName.substring(fileName.lastIndexOf(".")+1);
    }

    /**
     * 给文件取新名字
     * @return
     */
    public static String createName(String fileName) {
        return new Date().getTime() + interceptFileSuffix(fileName);
    }



}
