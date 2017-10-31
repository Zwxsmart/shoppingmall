package com.lanwantec.manage.web.page;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lanwantec.manage.utils.Helper;
import com.lanwantec.manage.utils.ReturnMessage;
import com.lanwantec.manage.web.user.PersonalCenterService;

/**
 * 居然之家移动版页面跳转
 */
@Controller
@RequestMapping("/mb/easyhome")
public class MbEasyHomeController {

	@Autowired
	private PageService pageService;

	@Value("${brand.imgPath}")
	private String imgPath = "";

	@Autowired
	private ReturnMessage message;

	@Autowired
	private PersonalCenterService personalCenterService;

	/** 首页 */
	@GetMapping("/index")
	public ModelAndView index(HttpSession session) {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/easyhome/index");
		mv.addObject("selectBrand", pageService.getBrandList(true, 1)); // 热门品牌
		mv.addObject("pavilions", pageService.getPavilions()); // 场馆列表
		mv.addObject("user", personalCenterService.getUserByToken(Helper.getToken(session), session));
		return mv;
	}

	/** 品牌详情 */
	@GetMapping("/brand_details")
	public ModelAndView brandDetails(@RequestParam String brandNo) {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/easyhome/brand_details");
		mv.addObject("brand", pageService.getBrandByBrandNo(brandNo));
		mv.addObject("contact", pageService.getContactByBrandNo(brandNo));
		return mv;
	}

	/** 联系我们 */
	@GetMapping("/contact_us")
	public ModelAndView contactUs() {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/easyhome/contact_us");
		mv.addObject("contactUs", pageService.getContent(13)); // 居然之家联系我们 infoType=13
		return mv;
	}

	/** 家具店铺 */
	@GetMapping("/furniture")
	public ModelAndView furniture() {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/easyhome/furniture");
		return mv;
	}

	/** 热门活动 */
	@GetMapping("/hot_activity")
	public ModelAndView hotActivity() {
		return new ModelAndView("mb/easyhome/hot_activity");
	}

	/** 热门活动详情 */
	@GetMapping("/hot_activity_details")
	public ModelAndView hotActivityDetails(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("mb/easyhome/hot_activity_details");
		mv.addObject("info", pageService.getInfoById(id));
		return mv;
	}

	/** 登录页面 */
	@GetMapping("/login")
	public ModelAndView login(HttpSession session) {
		if (Helper.getToken(session) != null) {
			return index(session); // 已登录，跳转到首页
		}
		return new ModelAndView("mb/easyhome/login");
	}

	/** 商场地图 */
	@GetMapping("/map")
	public ModelAndView map() {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/easyhome/map");
		return mv;
	}

	/** 样板间 */
	@GetMapping("/sample_rooms")
	public ModelAndView modelList() {
		ModelAndView mv = new ModelAndView("mb/easyhome/sample_rooms");
		mv.addObject("types", pageService.getTypes());
		return mv;
	}

	/** 样板间详情 */
	@GetMapping("/sample_rooms_details")
	public ModelAndView modelListDetails(@RequestParam String caseId) {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/easyhome/sample_rooms_details");
		mv.addObject("model", pageService.getModelDetailById(caseId));
		return mv;
	}

	/** 封装页面公共数据 */
	private ModelAndView getModelAndView() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("imgPath", imgPath);
		mv.addObject("banners", pageService.getModulesByModuleNo("4")); // 居然之家轮播图 moduleNo="4"
		return mv;
	}

}
