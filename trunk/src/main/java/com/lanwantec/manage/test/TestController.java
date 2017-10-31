package com.lanwantec.manage.test;

import com.lanwantec.manage.utils.ReturnMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestServiceImpl testService;

    @Autowired
    private ReturnMessage message;


    @GetMapping("/user/list")
    @ResponseBody
    public ModelAndView getTestUser(){
       return new ModelAndView("/templates/manage/index");
    }

    @PutMapping("/user/update")
    @ResponseBody
    public Map<String,Object> updateUser(String name){
        return message.createMessage("更新成功！");
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "添加用户", notes = "增加用户")
    public Map<String,Object> add(
                              @ApiParam(name = "mobile",value = "手机",required = true)
                              @RequestParam(name = "mobile",required = true)String mobile) throws Exception {

        Map<String,Object> map= new HashMap<String,Object>();
        map.put("name","lys");
        if (mobile==null || mobile.length()!=11) {
            map.put("info","mobile不合法!");
           return message.createMessage("0","1","添加不成功！",map);
        }
        return message.createMessage("0","添加成功！",map);
    }

    @GetMapping("/page")
    public ModelAndView index(@RequestParam(name = "p",required = true)String page){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/manage/"+page);
        return mv;
    }

}
