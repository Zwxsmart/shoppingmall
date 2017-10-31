package com.lanwantec.manage.manage.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据统计
 */
@Controller
@RequestMapping("/manage")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;

	/** 数据统计页 */
	@GetMapping("/statistics")
	public ModelAndView statistics() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manage/statistics");
		mv.addObject("category", statisticsService.categoryInfo());
		return mv;
	}

	/** 数据统计 */
	@ResponseBody
	@GetMapping("/statistics/data")
	public ModelMap statistics(@RequestParam(value = "categoryNo", defaultValue = "0") String categoryNo,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime) {
		ModelMap mm = new ModelMap();
		mm.put("rows", statisticsService.counter(categoryNo, startTime, endTime));
		return mm;
	}

}
