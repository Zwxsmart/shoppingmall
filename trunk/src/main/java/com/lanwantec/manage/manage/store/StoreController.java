package com.lanwantec.manage.manage.store;

import com.lanwantec.manage.entity.Store;
import com.lanwantec.manage.entity.User;
import com.lanwantec.manage.utils.ExcelRead;
import com.lanwantec.manage.utils.ExcelReader;
import com.lanwantec.manage.utils.IDUtils;
import com.lanwantec.manage.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/manage/store")
public class StoreController {
    @Value("${brand.imgPath}")
    String brand_imgPath="";

    @Autowired
    private StoreService storeService;

    @Autowired
    private ReturnMessage message;

    /** 店铺页面 */
    @GetMapping("/storePage")
    public ModelAndView page() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/store");
        mv.addObject("styles", storeService.queryStyles());
        return mv;
    }

    /** 店铺list */
    @ResponseBody
    @GetMapping("/storeList")
    public Map<String, Object> userList(Integer pageNumber,
                                        Integer pageSize,
                                        @RequestParam("brandName")String brandName,
                                        @RequestParam("floor")String floor,
                                        @RequestParam("storeStyle")String storeStyle,
                                        @RequestParam("startTime")String startTime,
                                        @RequestParam("endTime")String endTime) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> sList = storeService.queryByData(pageNumber, pageSize, brandName, floor, storeStyle, startTime, endTime);
        if(sList==null){
        }else{
            for(int i =0;i < sList.size(); i++){
                sList.get(i).put("showUrl1", brand_imgPath+sList.get(i).get("showUrl"));
            }
        }
        m.put("rows",sList);
        m.put("total", storeService.countByData(brandName, floor, storeStyle, startTime, endTime));
        return m;
    }

    /** 单个店铺信息 */
    @GetMapping("/queryById")
    public ModelAndView update(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/manage/store_update");
        if(req.getParameter("storeId")!= null && !req.getParameter("storeId").equals("") && !req.getParameter("storeId").equals("undefined")){
            Map<String, Object> m = storeService.queryById(req.getParameter("storeId"));
            if(m==null){
            }else{
                if(m.get("country")==null | "".equals(m.get("country"))){
                    m.put("country","kong");
                }
                if(m.get("province")==null | "".equals(m.get("province"))){
                    m.put("province","kong");
                }
                if(m.get("city")==null | "".equals(m.get("city"))){
                    m.put("city","kong");
                }
                m.put("showUrl1",brand_imgPath+m.get("showUrl"));
            }
            mav.addObject("store", m);
        }else{
            Store s = new Store();
            s.setCountry("kong");
            s.setProvince("kong");
            s.setCity("kong");
            mav.addObject("store", s);
        }
        mav.addObject("mall", storeService.querAllMall()); // 所有场馆
        mav.addObject("brand", storeService.querAllBrand()); // 所有品牌
        return mav;
    }

    /** 新增或修改店铺 */
    @ResponseBody
    @PostMapping("saveOrUpdate")
    public ModelMap saveOrUpdate(String bsid, String smid, String storeId, String name, String brand, String principal, String contacter,
                                String contactPhone, String storePhone, String doorplate, String openingHours, String floor, String mall,
                                 String address, String detailAddress, String longitude, String latitude, String provideService,String showUrl) {
        ModelMap md = new ModelMap();
        Integer count = storeService.update(storeId, name, principal, contacter,contactPhone, storePhone,doorplate,openingHours, address,detailAddress,longitude,latitude,provideService, showUrl);
        storeService.updateMall(smid,mall, storeId, floor, doorplate); // 修改or添加商场对应店铺
        storeService.updateBrand(bsid,brand, storeId); // 修改or添加品牌对应店铺
        md.put("count", count);
        return md;
    }

    /** 修改店铺状态 */
    @ResponseBody
    @PostMapping("status")
    public Map<String, Object> status(@RequestParam(value = "status", required = true)String status,
                                      @RequestParam(value = "storeId", required = true)String storeId) {
        ModelMap md = new ModelMap();
        md.put("count", storeService.status(status, storeId));
        return md;
    }

    @ResponseBody
    @RequestMapping(value = "readExcel", method = RequestMethod.POST)
    public Map<String, Object> readExcel( MultipartFile txt_file) throws IOException {
        String name = txt_file.getOriginalFilename();
        long size = txt_file.getSize();
        if (name == null || ExcelReader.EMPTY.equals(name) && size == 0) {
            return message.createMessage("1","1","导入店铺信息失败");
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
                Integer count = storeService.insert(arr.get(0), arr.get(1), arr.get(2),arr.get(3), arr.get(4), arr.get(5),arr.get(6),arr.get(7),arr.get(8));
                if(count!=1){
                    return message.createMessage("1","1","导入店铺信息失败");
                }
            } catch (NullPointerException e) {
                return message.createMessage("1","1","导入店铺信息失败");
            }
        }

        return message.createMessage("导入店铺信息成功");
    }

    public Date dateFormat(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
