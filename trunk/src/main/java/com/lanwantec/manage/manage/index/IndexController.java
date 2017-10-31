package com.lanwantec.manage.manage.index;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lanwantec.manage.utils.ReturnMessage;

import javax.servlet.http.HttpSession;

/**
 * 后台首页
 */
@Controller
@RequestMapping("/manage")
public class IndexController {

	@Autowired
	private ReturnMessage message;

	@Autowired
	private IndexService indexService;

	/** 首页 */
	@GetMapping("/index")
	public ModelAndView index(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("operNo") == null) {
			mv.setViewName("manage/login");
		} else {
			mv.setViewName("manage/index");
		}
		return mv;
	}

	/** 欢迎页 */
	@GetMapping("/index/welcome")
	public ModelAndView indexWelcome(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("operNo") == null) {
			mv.setViewName("manage/login");
		} else {
			mv.addObject("userCount", indexService.userCount()); // 会员统计及动态比较数据
			mv.addObject("counter", indexService.counter()); // 后台主页统计信息
			mv.addObject("pageview", indexService.pageview()); // 页面流量统计
			mv.setViewName("manage/index_welcome");
		}
		return mv;
	}

	/** 默认跳转首页 */
	@GetMapping
	public ModelAndView defaultIndex(HttpSession session) {
		return index(session);
	}

	/** 指定时间段内的页面流量统计接口 */
	@ResponseBody
	@GetMapping("/index/pageview")
	public Map<String, Object> getPageCount(@RequestParam String startTime, @RequestParam String endTime) {
		return message.createMessage(indexService.getPageCount(startTime, endTime));
	}

}
