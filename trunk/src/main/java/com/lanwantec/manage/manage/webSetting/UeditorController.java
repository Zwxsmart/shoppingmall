package com.lanwantec.manage.manage.webSetting;


import com.lanwantec.manage.ueditor.ActionEnter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/ueditor")
public class UeditorController {

    @Value("${brand.rootPath}")
    String brand_rootPath="";

    @RequestMapping("/controller")
    public void config(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
//          String rootPath = UeditorController.class.getClassLoader().getResource("files").getPath();
          //  String rootPath = configManager.getRootPath();
            System.out.println(brand_rootPath);
            ActionEnter actionEnter = new ActionEnter(request, brand_rootPath);
            response.getWriter().write(actionEnter.exec());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
