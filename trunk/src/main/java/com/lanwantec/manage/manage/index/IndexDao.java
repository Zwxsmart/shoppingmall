package com.lanwantec.manage.manage.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class IndexDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** 会员总数 */
	public int userTotal() {
		return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM tbl_user WHERE isValid=1", Integer.class);
	}

	/** 商品总数 */
	public int goodsTotal() {
		return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM tbl_goods WHERE isValid=1", Integer.class);
	}

	/** 店铺总数 */
	public int storeTotal() {
		return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM tbl_store WHERE isValid=1", Integer.class);
	}

	/** PV */
	public int pv(String date) {
		String sql = "SELECT COUNT(1) FROM record_page WHERE DATE_FORMAT(rTime,'%Y-%m-%d')=DATE_FORMAT(?,'%Y-%m-%d')";
		return jdbcTemplate.queryForObject(sql, Integer.class, date);
	}

	/** UV */
	public int uv(String date) {
		String sql = "SELECT COUNT(1) FROM (SELECT * FROM record_page WHERE DATE_FORMAT(rTime,'%Y-%m-%d')=DATE_FORMAT(?,'%Y-%m-%d') GROUP BY uuid) a";
		return jdbcTemplate.queryForObject(sql, Integer.class, date);
	}

	/** IP */
	public int ip(String date) {
		String sql = "SELECT COUNT(1) FROM (SELECT * FROM record_page WHERE DATE_FORMAT(rTime,'%Y-%m-%d')=DATE_FORMAT(?,'%Y-%m-%d') GROUP BY ip) a";
		return jdbcTemplate.queryForObject(sql, Integer.class, date);
	}

	/** 会员统计 */
	public int countUser(String beginDate, String endDate) {
		String sql = "SELECT COUNT(1) FROM tbl_user WHERE DATE_FORMAT(createDate,'%Y-%m-%d') BETWEEN ? AND ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, beginDate, endDate);
	}

	/** 会员统计 */
	public int countUser(String date) {
		String sql = "SELECT COUNT(1) FROM tbl_user WHERE DATE_FORMAT(createDate,'%Y-%m-%d')=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, date);
	}

	/** 会员统计 */
	public int countUserByMonth(String month) {
		String sql = "SELECT COUNT(1) FROM tbl_user WHERE DATE_FORMAT(createDate,'%Y-%m')=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, month);
	}

}
