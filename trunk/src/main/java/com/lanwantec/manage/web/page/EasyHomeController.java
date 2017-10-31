package com.lanwantec.manage.web.page;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lanwantec.manage.utils.Helper;
import com.lanwantec.manage.utils.ReturnMessage;
import com.lanwantec.manage.web.user.PersonalCenterService;

/**
 * 居然之家页面跳转
 */
@Controller
@RequestMapping("/easyhome")
public class EasyHomeController {

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
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/easyhome/index");
		mv.addObject("selectBrand", pageService.getBrandList(true, 1)); // 热门品牌
		return mv;
	}

	/** 默认跳转首页 */
	@GetMapping
	public ModelAndView defaultIndex(HttpSession session) {
		return index(session);
	}

	/** 品牌详情 */
	@GetMapping("/brand_details")
	public ModelAndView brandDetails(HttpSession session, @RequestParam String brandNo) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/easyhome/brand_details");
		mv.addObject("brand", pageService.getBrandByBrandNo(brandNo));
		mv.addObject("contact", pageService.getContactByBrandNo(brandNo));
		return mv;
	}

	/** 联系我们 */
	@GetMapping("/contact_us")
	public ModelAndView contactUs(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/easyhome/contact_us");
		mv.addObject("contactUs", pageService.getContent(13)); // 居然之家联系我们 infoType=13
		return mv;
	}

	/** 页脚 */
	@GetMapping("/footer")
	public ModelAndView footer() {
		return new ModelAndView("pc/easyhome/footer");
	}

	/** 热门活动 */
	@GetMapping("/hot_activity")
	public ModelAndView hotActivity(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/easyhome/hot_activity");
		return mv;
	}

	/** 热门活动详情 */
	@GetMapping("/hot_activity_details")
	public ModelAndView hotActivityDetails(HttpSession session, @RequestParam int id) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/easyhome/hot_activity_details");
		mv.addObject("info", pageService.getInfoById(id));
		return mv;
	}

	/** 商场地图 */
	@GetMapping("/map")
	public ModelAndView map(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/easyhome/map");
		return mv;
	}

	/** 样板间 */
	@GetMapping("/model_list")
	public ModelAndView modelList(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/easyhome/model_list");
		mv.addObject("types", pageService.getTypes());
		return mv;
	}

	/** 样板间详情 */
	@GetMapping("/model_list_details")
	public ModelAndView modelListDetails(HttpSession session, @RequestParam String caseId) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/easyhome/model_list_details");
		mv.addObject("model", pageService.getModelDetailById(caseId));
		return mv;
	}

	/** 门店列表 */
	@GetMapping("/store_list")
	public ModelAndView storeList(HttpSession session) {
		ModelAndView mv = getModelAndView(session);
		mv.setViewName("pc/easyhome/store_list");
		return mv;
	}

	/** 封装页面公共数据 */
	private ModelAndView getModelAndView(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("imgPath", imgPath);
		mv.addObject("pavilions", pageService.getPavilions()); // 场馆列表
		mv.addObject("banners", pageService.getModulesByModuleNo("4")); // 居然之家轮播图 moduleNo="4"
		mv.addObject("user", personalCenterService.getUserByToken(Helper.getToken(session), session));
		return mv;
	}

	/** 样板间列表数据接口(分页) */
	@ResponseBody
	@GetMapping("/model/list")
	public Map<String, Object> getModelList(
			@RequestParam(defaultValue = "", required = false) String style,
			@RequestParam(defaultValue = "", required = false) String theme,
			@RequestParam(defaultValue = "", required = false) String area,
			@RequestParam(defaultValue = "", required = false) String part,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "8", required = false) int size) {
		return message.createMessage(pageService.getModelList(style, theme, area, part, page, size));
	}

	/** 门店列表数据接口(分页) */
	@ResponseBody
	@GetMapping("/store/list")
	public Map<String, Object> getStoreList(
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "6", required = false) int size) {
		return message.createMessage(pageService.getStoreList(1, page, size));
	}

	/** 居然之家热门活动列表数据接口(分页) */
	@ResponseBody
	@GetMapping("/activity/list")
	public Map<String, Object> getActivityList(
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "3", required = false) int size) {
		return message.createMessage(pageService.getActivityList(12, page, size)); // infoType=12
	}

}
