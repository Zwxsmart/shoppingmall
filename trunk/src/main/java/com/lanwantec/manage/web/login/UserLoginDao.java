package com.lanwantec.manage.web.login;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserLoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** 根据电话号码查询用户 */
	public Map<String, Object> findUserByPhone(String phone) {
		String sql = "SELECT * FROM tbl_user WHERE phone=? AND isValid=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, phone);
		return list.size() > 0 ? list.get(0) : null;
	}

	/** 根据openId查询用户 */
	public Map<String, Object> findUserByOpenId(String openId) {
		String sql = "SELECT * FROM tbl_user WHERE wxOpenId=? AND isValid=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, openId);
		return list.size() > 0 ? list.get(0) : null;
	}

	/** 注册用户 */
	public int registerUser(String userNo, String phone) {
		return jdbcTemplate.update("INSERT INTO tbl_user(userNo,phone) VALUES(?,?)", userNo, phone);
	}

	/** 注册用户 */
	public int registerUser(String userNo,String openId, String phone) {
		return jdbcTemplate.update("INSERT INTO tbl_user(userNo,phone,wxOpenId) VALUES(?,?,?)", userNo, phone,openId);
	}


	public Map<String, Object> queryByOpenId(String openId) {
		String sql = "SELECT * FROM tbl_user WHERE wxOpenId=? AND isValid=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, openId);
		return list.size() > 0 ? list.get(0) : null;
	}

	public Integer queryByIsPhone(String phone) {
		String sql = "select count(userNo) from tbl_user where phone = "+phone;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Integer queryByIsOpenId(String openId) {
		String sql = "select count(userNo) from tbl_user where wxOpenId = '"+openId+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int updateUser(String openId, String phone) {
		return jdbcTemplate.update("update tbl_user set wxOpenId=? where phone = ?", openId, phone);
	}

	public int updateUser1(String openId, String phone) {
		return jdbcTemplate.update("update tbl_user set phone=? where wxOpenId = ?", phone, openId);
	}
}
