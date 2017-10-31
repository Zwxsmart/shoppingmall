package com.lanwantec.manage.manage.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

	@Autowired
	private StatisticsDao statisticsDao;

	/** 类目信息 */
	@SuppressWarnings("serial")
	public List<Map<String, Object>> categoryInfo() {
		// category：0=全部，1=店铺总数，2=商品总数，3=注册用户总数
		return new ArrayList<Map<String, Object>>(){{
			add(new HashMap<String, Object>(){{
				put("categoryNo", "0"); // 类目编号
				put("categoryName", "全部"); // 类目名称
				put("tableName", ""); // 要统计类目数据的数据库名称
			}});
			add(new HashMap<String, Object>(){{
				put("categoryNo", "1");
				put("categoryName", "店铺总数");
				put("tableName", "tbl_store");
			}});
			add(new HashMap<String, Object>(){{
				put("categoryNo", "2");
				put("categoryName", "商品总数");
				put("tableName", "tbl_goods");
			}});
			add(new HashMap<String, Object>(){{
				put("categoryNo", "3");
				put("categoryName", "注册用户总数");
				put("tableName", "tbl_user");
			}});
		}};
	}

	/** 数据统计 */
	public List<Map<String, Object>> counter(String categoryNo, String startTime, String endTime) {
		List<Map<String, Object>> counter = new ArrayList<>();
		List<Map<String, Object>> categoryInfo = categoryInfo();
		for (int i = 1; i < categoryInfo.size(); i++) {
			Map<String, Object> map = categoryInfo.get(i);
			if ("0".equals(categoryNo) || map.get("categoryNo").toString().equals(categoryNo)) {
				Map<String, Object> tempMap = new HashMap<>();
				tempMap.put("categoryName", map.get("categoryName"));
				tempMap.put("total", statisticsDao.counter((String) map.get("tableName"), startTime, endTime));
				counter.add(tempMap);
			}
		}
		return counter;
	}

}
