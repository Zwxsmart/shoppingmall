package com.lanwantec.manage.web.page;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lanwantec.manage.utils.Helper;
import com.lanwantec.manage.utils.ReturnMessage;
import com.lanwantec.manage.web.user.PersonalCenterService;

/**
 * 联盟国际页面跳转
 */
@Controller
public class PageController {

	@Autowired
	private PageService pageService;

	@Autowired
	private ReturnMessage message;

	@Value("${brand.imgPath}")
	private String imgPath = "";

	@Autowired
	private PersonalCenterService personalCenterService;

	/** 首页 */
	@GetMapping("/index")
	public ModelAndView index(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/index");
		mv.addObject("modules", pageService.getModulesByModuleNo("2")); // 首页模块列表 moduleNo="2"
		return mv;
	}

	/** 关于我们 */
	@GetMapping("/about_us")
	public ModelAndView aboutUs(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/about_us");
		mv.addObject("groupProfile", pageService.getContent(0)); // 集团简介 infoType=0
		mv.addObject("corporateCulture", pageService.getContent(3)); // 企业文化 infoType=3
		mv.addObject("companyHonor", pageService.queryHonor()); // 企业荣誉
		return mv;
	}

	/** 品牌联盟 */
	@GetMapping("/brand_list")
	public ModelAndView brandList(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/brand_list");
		// isSelection=true(精选品牌),fasle(其它品牌) mallId=0(联盟国际),1(居然之家)
		mv.addObject("selectBrand", pageService.getBrandList(true, 0));
		mv.addObject("otherBrand", pageService.getBrandList(false, 0));
		return mv;
	}

	/** 品牌详情 */
	@GetMapping("/brand_list_details")
	public ModelAndView brandListDetails(HttpSession session, @RequestParam String brandNo) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/brand_list_details");
		mv.addObject("brand", pageService.getBrandByBrandNo(brandNo));
		return mv;
	}

	/** 商务合作 */
	@GetMapping("/business_cooperation")
	public ModelAndView businessCooperation(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/business_cooperation");
		mv.addObject("businessQuestions", pageService.getContent(5)); // 商务百问 infoType=5
		mv.addObject("formatDivision", pageService.getContent(6)); // 业态划分 infoType=6
		mv.addObject("businessConsulting", pageService.getContent(7)); // 商务咨询 infoType=7
		return mv;
	}

	/** 联系我们 */
	@GetMapping("/contact_us")
	public ModelAndView contactUs(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/contact_us");
		mv.addObject("contactUs", pageService.getContent(10)); // 联系我们 infoType=10
		return mv;
	}

	/** 火热销售 */
	@GetMapping("/fiery_market")
	public ModelAndView fieryMarket(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/fiery_market");
		mv.addObject("content", pageService.getContent(11)); // 火热销售 infoType=11
		return mv;
	}

	/** 家博城 */
	@GetMapping("/home_win_city")
	public ModelAndView homeWinCity(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/home_win_city");
		mv.addObject("modules", pageService.getModulesByModuleNo("6")); // 家博城PC端轮播图 moduleNo="6"
		mv.addObject("title", pageService.getModulesByModuleNo("2").get(0).get("title")); // 标题
		return mv;
	}

	/** 人才招聘 */
	@GetMapping("/talent_recruitment")
	public ModelAndView talentRecruitment(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/talent_recruitment");
		mv.addObject("inviteJob", pageService.getContent(9)); // 招聘职位 infoType=9
		return mv;
	}

	/** 页脚 */
	@GetMapping("/footer")
	public ModelAndView footer() {
		ModelAndView mv = new ModelAndView("pc/footer");
		mv.addObject("qrCode", pageService.getQrCode());
		return mv;
	}

	/** 集团动态详情 */
	@GetMapping("/hot_activity")
	public ModelAndView hotActivity(HttpSession session, @RequestParam int id) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/hot_activity");
		mv.addObject("info", pageService.getInfoById(id));
		return mv;
	}

	/** 集团动态接口(分页) */
	@ResponseBody
	@GetMapping("/group/dynamics")
	public Map<String, Object> groupDynamics(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "3") int size) {
		return message.createMessage(pageService.groupDynamics(page, size));
	}

	/** 封装页面公共数据 */
	private ModelAndView getModelAndView(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("banners", pageService.getModulesByModuleNo("1")); // 轮播图 moduleNo="1"
		mv.addObject("imgPath", imgPath);
		mv.addObject("pavilions", pageService.getPavilions()); // 场馆列表
		mv.addObject("user", personalCenterService.getUserByToken(Helper.getToken(session), session));
		return mv;
	}

	/** 营销活动列表数据接口(分页) */
	@ResponseBody
	@GetMapping("/activity/list")
	public Map<String, Object> getActivityList(@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "3", required = false) int size) {
		return message.createMessage(pageService.getCampaignList(page, size));
	}

}
