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
import com.lanwantec.manage.web.user.PersonalCenterService;

import java.util.Map;

/**
 * 联盟国际移动版页面跳转
 */
@Controller
@RequestMapping("/mb")
public class MbPageController {
	@Value("${brand.imgPath}")
	String brand_imgPath="";

	@Autowired
	private PersonalCenterService personalCenterService;

	@Autowired
	private PageService pageService;

	@Value("${brand.imgPath}")
	private String imgPath = "";

	/** 首页 */
	@GetMapping("/index")
	public ModelAndView index(HttpSession session) {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/index");
		mv.addObject("pavilions", pageService.getPavilions()); // 场馆列表
		mv.addObject("user", personalCenterService.getUserByToken(Helper.getToken(session), session));
		return mv;
	}

	/** 关于我们 */
	@GetMapping("/about_us")
	public ModelAndView aboutUs() {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/about_us");
		mv.addObject("groupProfile", pageService.getContent(0)); // 集团简介 infoType=0
		mv.addObject("corporateCulture", pageService.getContent(3)); // 企业文化 infoType=3
		mv.addObject("companyHonor", pageService.companyHonor()); // 企业荣誉
		return mv;
	}

	/** 品牌联盟 */
	@GetMapping("/brand_list")
	public ModelAndView brandList() {
		ModelAndView mv = new ModelAndView("mb/brand_list");
		// isSelection=true(精选品牌),fasle(其它品牌) mallId=0(联盟国际),1(居然之家)
		mv.addObject("selectBrand", pageService.getBrandList(true, 0));
		mv.addObject("otherBrand", pageService.getBrandList(false, 0));
		mv.addObject("imgPath", imgPath);
		return mv;
	}

	/** 品牌详情 */
	@GetMapping("/brand_details")
	public ModelAndView brandDetails(@RequestParam String brandNo) {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/brand_details");
		mv.addObject("brand", pageService.getBrandByBrandNo(brandNo));
		mv.addObject("contact", pageService.getContactByBrandNo(brandNo));
		return mv;
	}

	/** 商务合作 */
	@GetMapping("/business_cooperation")
	public ModelAndView businessCooperation() {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/business_cooperation");
		mv.addObject("businessQuestions", pageService.getContent(5)); // 商务百问 infoType=5
		mv.addObject("formatDivision", pageService.getContent(6)); // 业态划分 infoType=6
		mv.addObject("businessConsulting", pageService.getContent(7)); // 商务咨询 infoType=7
		return mv;
	}

	/** 联系我们 */
	@GetMapping("/contact_us")
	public ModelAndView contactUs() {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/contact_us");
		mv.addObject("contactUs", pageService.getContent(10)); // 联系我们 infoType=10
		return mv;
	}

	/** 火热销售 */
	@GetMapping("/fiery_market")
	public ModelAndView fieryMarket() {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/fiery_market");
		mv.addObject("content", pageService.getContent(11)); // 火热销售 infoType=11
		return mv;
	}

	/** 家具工厂 */
	@GetMapping("/furniture_plant")
	public ModelAndView furniturePlant() {
		ModelAndView mv = new ModelAndView("mb/furniture_plant");
		mv.addObject("modules", pageService.getModulesByModuleNo("5")); // 家具工厂移动端moduleNo="5"
		mv.addObject("imgPath", imgPath);
		return mv;
	}

	/** 集团动态详情 */
	@GetMapping("/group_dynamics")
	public ModelAndView groupDynamics(@RequestParam int id) {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/group_dynamics");
		mv.addObject("info", pageService.getInfoById(id));
		return mv;
	}

	/** 家博城 */
	@GetMapping("/home_win_city")
	public ModelAndView homeWinCity() {
		ModelAndView mv = new ModelAndView("mb/home_win_city");
		mv.addObject("modules", pageService.getModulesByModuleNo("7")); // 家博城移动端moduleNo="7"
		mv.addObject("imgPath", imgPath);
		return mv;
	}

	/** 热门活动 */
	@GetMapping("/hot_activity")
	public ModelAndView hotActivity() {
		return new ModelAndView("mb/hot_activity");
	}

	/** 热门活动详情 */
	@GetMapping("/hot_activity_details")
	public ModelAndView hotActivityDetails(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("mb/hot_activity_details");
		mv.addObject("info", pageService.getCampaignById(id));
		return mv;
	}

	/** 登录页面 */
	@GetMapping("/login")
	public ModelAndView login(HttpSession session) {
		if (Helper.getToken(session) != null) {
			return index(session); // 已登录，跳转到首页
		}
		return new ModelAndView("mb/login"); // 未登录，跳转到登录页
	}

	/** 个人中心 */
	@GetMapping("/personal_center")
	public ModelAndView personalCenter(HttpSession session) {
		String token = Helper.getToken(session);
		if (token == null) {
			return login(session); // 未登录，跳转到登录页
		}
		ModelAndView mv = new ModelAndView("mb/personal_center");
		Map<String, Object> m = personalCenterService.getUserByToken(token, session);
		mv.addObject("user", m);
		mv.addObject("showPortrait", brand_imgPath+m.get("portrait"));
		return mv;
	}

	/** 人才招聘 */
	@GetMapping("/talent_recruitment")
	public ModelAndView talentRecruitment() {
		ModelAndView mv = getModelAndView();
		mv.setViewName("mb/talent_recruitment");
		mv.addObject("inviteJob", pageService.getContent(9)); // 招聘职位 infoType=9
		return mv;
	}

	/** 封装页面公共数据 */
	private ModelAndView getModelAndView() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("banners", pageService.getModulesByModuleNo("1")); // 轮播图 moduleNo="1"
		mv.addObject("imgPath", imgPath);
		return mv;
	}

}
