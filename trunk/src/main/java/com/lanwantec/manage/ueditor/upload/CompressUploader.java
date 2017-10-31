package com.lanwantec.manage.ueditor.upload;

import com.lanwantec.manage.ueditor.PathFormat;
import com.lanwantec.manage.ueditor.define.AppInfo;
import com.lanwantec.manage.ueditor.define.BaseState;
import com.lanwantec.manage.ueditor.define.FileType;
import com.lanwantec.manage.ueditor.define.State;
import com.lanwantec.manage.utils.ImageUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Rainy on 2016/12/8/0008.
 */
public class CompressUploader {

    public static final State save(HttpServletRequest request,
                                   Map<String, Object> conf) {

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());

            String savePath = (String) conf.get("savePath");
            String originFileName = multipartFile.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0,originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            savePath = PathFormat.parse(savePath, originFileName);

            String [] savePathBySplit_temp = savePath.split("/");
            String temp = "";
            String fileName = savePathBySplit_temp[savePathBySplit_temp.length-1];
            for(int i = 1;i < savePathBySplit_temp.length-1; i++){
                if(i!=savePathBySplit_temp.length-2){
                    temp+=savePathBySplit_temp[i]+"/";
                }else{
                    temp+=savePathBySplit_temp[i];
                }
            }
            String pathTemp;
            String oldUrl = request.getParameter("oldUrl");
            if(oldUrl!=null&&!oldUrl.equalsIgnoreCase("")){
                pathTemp = (String) conf.get("rootPath") + oldUrl;
            }else {
                pathTemp = (String) conf.get("rootPath") + temp ;
                File targetFile = new File(pathTemp);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                pathTemp = pathTemp+"/"+fileName;
            }

            InputStream stream = multipartFile.getInputStream();
            File file = new File(pathTemp);
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            stream.close();
            int srcHeight = Integer.parseInt(request.getParameter("height"));
            int srcWidth = Integer.parseInt(request.getParameter("height"));
            return ImageUtils.scaleImageWithWidthHeight(multipartFile.getInputStream(),srcHeight,srcWidth,pathTemp,savePath,800,600);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
