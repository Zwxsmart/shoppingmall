package com.lanwantec.manage.ueditor.upload;

import com.lanwantec.manage.ueditor.PathFormat;
import com.lanwantec.manage.ueditor.define.AppInfo;
import com.lanwantec.manage.ueditor.define.BaseState;
import com.lanwantec.manage.ueditor.define.FileType;
import com.lanwantec.manage.ueditor.define.State;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BinaryUploader {

    private static Logger log = Logger.getLogger(BinaryUploader.class);

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

			String action = request.getParameter("action");
			if(action.equalsIgnoreCase("card")){
				pathTemp = savePath;
			}else{
				String oldUrl = request.getParameter("oldUrl");
				if(null!=oldUrl && !"".equalsIgnoreCase(oldUrl)){
					pathTemp = (String) conf.get("rootPath") + oldUrl;
				}else {
					pathTemp = (String) conf.get("rootPath") + temp ;
					File targetFile = new File(pathTemp);
					if(!targetFile.exists()){
						targetFile.mkdirs();
					}
					pathTemp = pathTemp+"/"+fileName;
				}

			}

			State storageState = StorageManager.saveFileByInputStream(multipartFile.getInputStream(),pathTemp, maxSize);
			if (storageState.isSuccess()) {
				storageState.putInfo("url",PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;

		}catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}



/*	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String savePath = (String) conf.get("savePath");
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String physicalPath = (String) conf.get("rootPath") + savePath;

			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFileByInputStream(is,
					physicalPath, maxSize);
			is.close();

			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}*/

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
