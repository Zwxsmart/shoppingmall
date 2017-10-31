package com.lanwantec.manage.manage.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanwantec.manage.utils.DateUtil;

@Service
public class IndexService {

	@Autowired
	private IndexDao indexDao;

	/** 后台主页统计信息 */
	@SuppressWarnings("serial")
	public Map<String, Object> counter() {
		// 统计会员、商品、店铺、订单总数
		return new HashMap<String, Object>() {
			{
				put("user", indexDao.userTotal());
				put("goods", indexDao.goodsTotal());
				put("store", indexDao.storeTotal());
				put("order", 0); // 暂时无订单表
			}
		};
	}

	/** 页面流量统计 */
	@SuppressWarnings("serial")
	public Map<String, Object> pageview() {
		// 今日PV、UV、IP
		return new HashMap<String, Object>() {
			{
				put("pv", indexDao.pv(DateUtil.getCurrentDate()));
				put("uv", indexDao.uv(DateUtil.getCurrentDate()));
				put("ip", indexDao.ip(DateUtil.getCurrentDate()));
			}
		};
	}

	/** 会员统计及动态比较数据 */
	public Map<String, Object> userCount() {
		Map<String, Object> userCount = new HashMap<>();
		// '今年','本月','本周','今天'的数据统计
		int total[] = new int[4];
		total[0] = indexDao.countUser(DateUtil.getFirstDateOfCurrentYear(), DateUtil.getLastDateOfCurrentYear()); // 今年
		total[1] = indexDao.countUser(DateUtil.getFirstDateOfCurrentMoth(), DateUtil.getLastDateOfCurrentMonth()); // 本月
		total[2] = indexDao.countUser(DateUtil.getFirstDateOfCurrentWeek(), DateUtil.getLastDateOfCurrentWeek()); // 本周
		total[3] = indexDao.countUser(DateUtil.getCurrentDate(), DateUtil.getCurrentDate()); // 今天
		userCount.put("total", total);

		// 本周动态数据
		int w[] = new int[7];
		for (int i = 0; i < 7; i++) {
			w[i] = indexDao.countUser(DateUtil.getDateOfCurrentWeek(i + 1));
		}
		userCount.put("w", w);

		// 本月动态数据
		int maxDays = DateUtil.getActualMaximumOfCurrentMoth();
		String[] m_x = new String[maxDays];
		int[] m_y = new int[maxDays];
		for (int i = 0; i < maxDays; i++) {
			m_x[i] = (i + 1) + "日";
			m_y[i] = indexDao.countUser(DateUtil.getDateOfCurrentMoth(i + 1));
		}
		userCount.put("m_x", m_x);// 当月动态日期
		userCount.put("m_y", m_y);// 当月动态数据

		// 今年动态数据
		int y[] = new int[12];
		for (int i = 0; i < 12; i++) {
			y[i] = indexDao.countUserByMonth(DateUtil.getMonthOfCurrentYear(i + 1));
		}
		userCount.put("y", y);
		return userCount;
	}

	/** 指定时间段内的页面流量统计接口 */
	public Map<String, Object> getPageCount(String startTime, String endTime) {
		List<String> x_data = new ArrayList<>();
		List<Integer> pv = new ArrayList<>();
		List<Integer> uv = new ArrayList<>();
		List<Integer> ip = new ArrayList<>();
		int days = DateUtil.dateDiff(startTime, endTime);
		for (int i = 0; i <= days; i++) {
			String currentDate = DateUtil.getDateAddDays(startTime, i);
			x_data.add(currentDate);
			pv.add(indexDao.pv(currentDate));
			uv.add(indexDao.uv(currentDate));
			ip.add(indexDao.ip(currentDate));
		}
		Map<String, Object> pageCount = new HashMap<>();
		pageCount.put("x_data", x_data);
		pageCount.put("pv", pv);
		pageCount.put("uv", uv);
		pageCount.put("ip", ip);
		return pageCount;
	}

}
