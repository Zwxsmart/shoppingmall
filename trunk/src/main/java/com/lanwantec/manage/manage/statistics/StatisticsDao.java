package com.lanwantec.manage.manage.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatisticsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** 数据统计 */
	public int counter(String tableName, String startTime, String endTime) {
		String sql = "SELECT COUNT(1) FROM " + tableName + " WHERE isValid=1";
		if (startTime != null && !"".equals(startTime)) {
			sql = sql + " AND DATE_FORMAT(createDate,'%Y-%m-%d')>='" + startTime + "' ";
		}
		if (endTime != null && !"".equals(endTime)) {
			sql = sql + " AND DATE_FORMAT(createDate,'%Y-%m-%d')<='" + endTime + "' ";
		}
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
