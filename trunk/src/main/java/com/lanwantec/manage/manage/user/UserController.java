package com.lanwantec.manage.manage.user;

import com.lanwantec.manage.entity.User;
import com.lanwantec.manage.manage.index.IndexService;
import com.lanwantec.manage.utils.ExcelRead;
import com.lanwantec.manage.utils.ExcelReader;
import com.lanwantec.manage.utils.IDUtils;
import com.lanwantec.manage.utils.ReturnMessage;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/manage/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReturnMessage message;

    /** 会员页面 */
    @GetMapping("/userPage")
    public ModelAndView page() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/user");
        return mv;
    }

    /**  */
    @ResponseBody
    @GetMapping("/userList")
    public Map<String, Object> userList(Integer pageNumber,
                                        Integer pageSize,
                                        @RequestParam("userName")String userName,
                                        @RequestParam("startTime")String startTime,
                                        @RequestParam("endTime")String endTime) {
        Map<String, Object> m = new HashMap<>();
        m.put("rows",userService.queryByData(pageNumber, pageSize, userName, startTime, endTime));
        m.put("total", userService.countByData(userName, startTime, endTime));
        return m;
    }

    @GetMapping("/queryById")
    public ModelAndView update(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/manage/user_update");
        if(req.getParameter("userNo")!= null && !req.getParameter("userNo").equals("") && !req.getParameter("userNo").equals("undefined")){
            Map<String, Object> m = userService.queryById(req.getParameter("userNo"));
            if(m==null){
            }else {
                if(m.get("country")==null | "".equals(m.get("country"))){
                    m.put("country","kong");
                }
                if(m.get("province")==null | "".equals(m.get("province"))){
                    m.put("province","kong");
                }
                if(m.get("city")==null | "".equals(m.get("city"))){
                    m.put("city","kong");
                }
                mav.addObject("user", m);
            }
        }else{
            User u= new User();
            u.setCountry("kong");
            u.setProvince("kong");
            u.setCity("kong");
            u.setGender("M");
            mav.addObject("user",u);
        }
        return mav;
    }

    @ResponseBody
    @PostMapping("/queryId")
    public ModelMap queryId(@RequestParam(value = "userNo", required = true)String userNo) {
        ModelMap m = new ModelMap();
        m.put("user", userService.queryById(userNo));
        return m;
    }

    @GetMapping("/queryBrowse")
    public ModelAndView browse(@RequestParam(value = "userNo", required = true)String userNo) {
        ModelAndView mav = new ModelAndView("/manage/userBrowse");
        mav.addObject("user", userService.queryByBrowse(userNo));
        return mav;
    }

    @ResponseBody
    @PostMapping("saveOrUpdate")
    public ModelMap saveOrUpdate(@RequestParam(value = "userNo", required = true)String userNo,
                        @RequestParam(value = "name", required = true)String name,
                        @RequestParam(value = "nick", required = true)String nick,
                        @RequestParam(value = "gender", required = true)String gender,
                        @RequestParam(value = "phone", required = true)String phone,
                        @RequestParam(value = "birth", required = true)String birth,
                        @RequestParam(value = "address", required = true)String address) {
        ModelMap md = new ModelMap();
        Integer count = userService.add(userNo,name, nick, gender, phone, birth, address);
        md.put("count", count);
        return md;
    }

    @ResponseBody
    @PostMapping("status")
    public Map<String, Object> status(@RequestParam(value = "status", required = true)String status,
                        @RequestParam(value = "userNo", required = true)String userNo) {
        ModelMap md = new ModelMap();
        md.put("count", userService.status(status, userNo));
        return md;
    }

    @ResponseBody
    @RequestMapping(value = "readExcel", method = RequestMethod.POST)
    public Map<String, Object> readExcel( MultipartFile txt_file) throws IOException {
                String name = txt_file.getOriginalFilename();
                long size = txt_file.getSize();
                if (name == null || ExcelReader.EMPTY.equals(name) && size == 0) {
                    return message.createMessage("1","1","导入会员信息失败");
                }
                //读取Excel数据到List中
                List<ArrayList<String>> list = null;
                List<User> users = new ArrayList<>();
                    try {
                        list = new ExcelRead().readExcel(txt_file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                //list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始），如：
                for (ArrayList<String> arr : list) {
                    User u= new User();
                    try {
                        Integer count = userService.insert(IDUtils.createUserNo(), arr.get(0), arr.get(1), arr.get(2),arr.get(3), arr.get(4), arr.get(5), dateFormat(arr.get(6)));
                        if(count!=1){
                            return message.createMessage("1","1","导入会员信息失败");
                        }
                    } catch (NullPointerException e) {
                        return message.createMessage("1","1","导入会员信息失败");
                    }
                }

            return message.createMessage("导入会员信息成功");
    }

    public Date dateFormat(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
