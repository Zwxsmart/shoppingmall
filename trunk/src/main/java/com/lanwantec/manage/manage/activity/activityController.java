package com.lanwantec.manage.manage.activity;

import com.lanwantec.manage.manage.webSetting.WebSettingService;
import com.lanwantec.manage.utils.Constants;
import com.lanwantec.manage.utils.MD5ByjsUtil;
import com.lanwantec.manage.utils.MD5Utils;
import com.lanwantec.manage.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/manage/activity")
public class activityController {
    @Value("${brand.imgPath}")
    String brand_imgPath="";

    @Value("${server.error.path:/error}")
    private static final String ERROR_PATH ="/error/error";

    @Autowired
    private WebSettingService webSettingService;

    @Autowired
    private ReturnMessage message;

    /** 活动登记页面 */
    @GetMapping("/queryCheckPage")
    public ModelAndView queryCheckPage(@RequestParam(value = "campaignId",required = true)String campaignId,
                                       @RequestParam(value = "releasePointId",required = true)String releasePointId,
                                       @RequestParam(value = "autograph",required = true)String autograph) throws Exception {
        ModelAndView mav = new ModelAndView("/manage/user_check");
        String str = MD5ByjsUtil.GetMD5Code("campaignId="+campaignId+"&releasePointId="+releasePointId);
        if(str.equals(autograph)){
            if(webSettingService.queryByCamAndPoint(campaignId, releasePointId)>0){ // 更新浏览
                if(webSettingService.updateByCamAndPoint(campaignId, releasePointId)==1){
                    mav.addObject("releasePointId", releasePointId);
                    mav.addObject("campaignId", campaignId);
                    return mav;
                }
            }else{// 否则新增浏览
                if(webSettingService.insertByCamAndPoint(campaignId, releasePointId)==1){
                    mav.addObject("releasePointId", releasePointId);
                    mav.addObject("campaignId", campaignId);
                    return mav;
                }
            }
        }
        mav.setViewName(ERROR_PATH);
        return mav;
    }

    /** 活动详情页面 */
    @GetMapping("/queryActivityPage")
    public ModelAndView queryActivityPage(@RequestParam(value = "campaignId",required = true)String campaignId,
                                          @RequestParam(value = "releasePointId",required = true)String releasePointId,
                                          @RequestParam(value = "autograph",required = true)String autograph) throws Exception {
        ModelAndView mav = new ModelAndView("/manage/activity_detail");
        String str = MD5ByjsUtil.GetMD5Code("campaignId="+campaignId+"&releasePointId="+releasePointId);
        if(str.equals(autograph)) {
            if(webSettingService.queryByCamAndPoint(campaignId, releasePointId)>0){ // 更新浏览
                if(webSettingService.updateByCamAndPoint(campaignId, releasePointId)==1){
                    Map<String, Object> m = webSettingService.queryCampaignById(campaignId);
                    mav.addObject("info", m);
                    mav.addObject("releasePointId", releasePointId);
                    mav.addObject("campaignId", campaignId);
                    mav.addObject("autograph", MD5ByjsUtil.GetMD5Code("campaignId="+campaignId+"&releasePointId="+releasePointId));
                    return mav;
                }
            }else{// 否则新增浏览
                if(webSettingService.insertByCamAndPoint(campaignId, releasePointId)==1){
                    Map<String, Object> m = webSettingService.queryCampaignById(campaignId);
                    mav.addObject("info", m);
                    mav.addObject("releasePointId", releasePointId);
                    mav.addObject("campaignId", campaignId);
                    mav.addObject("autograph", MD5ByjsUtil.GetMD5Code("campaignId="+campaignId+"&releasePointId="+releasePointId));
                    return mav;
                }
            }

        }
        mav.setViewName(ERROR_PATH);
        return mav;
    }

    /**
     *   活动登记详情页提交
     */
    @ResponseBody
    @PostMapping("checkComit")
    public ModelMap checkComit(@RequestParam(value = "name",required = true)String name,
                               @RequestParam(value = "phone", required = true)String phone,
                               @RequestParam(value = "address")String address,
                               @RequestParam(value = "code", required = true)String code,
                               @RequestParam(value = "releasePointId", required = true)String releasePointId,
                               @RequestParam(value = "campaignId", required = true)String campaignId, HttpSession session) {
        ModelMap md = new ModelMap();
        String key = Constants.LOGON_CODE_PREFIX + phone;
        String code1 =(String)session.getAttribute(key);
        if (code1 != null && !"".equals(code1) && code1.equals(code)) { // 验证码正确
            session.removeAttribute(key); // 删除缓存中的验证码
            Integer count = webSettingService.checkComit(name,phone,address,releasePointId,campaignId);
            md.put("count", count);
        }
        return md;
    }
}
