package com.lanwantec.manage.web.user;

import java.util.List;
import java.util.Map;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class PersonalCenterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** 根据userNo修改用户信息 */
	public int updataUserByUserNo(String userNo, String nick, String phone, String gender, String email) {
		String sql = "UPDATE tbl_user SET nick=?,phone=?,gender=?,email=? WHERE userNo=? AND isValid=1";
		return jdbcTemplate.update(sql, nick, phone, gender, email, userNo);
	}

	/** 根据userNo获取用户信息 */
	public Map<String, Object> getUserByUserNo(String userNo) {
		String sql = "SELECT * FROM tbl_user WHERE userNo=? AND isValid=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, userNo);
		return list.size() > 0 ? list.get(0) : null;
	}

	public Integer update1(String userNo, String nick, String phone, String gender,String email, String portrait) {
		if(portrait==null|"".equals(portrait)){
			String sql = "update tbl_user set nick=?, gender=?, phone=?, email=? where userNo =?";
			return jdbcTemplate.update(sql, nick, gender, phone, email, userNo);
		}else {
			String sql = "update tbl_user set nick=?, gender=?, phone=?, email=?, portrait=? where userNo =?";
			return jdbcTemplate.update(sql, nick, gender, phone, email, portrait, userNo);
		}
	}
}
