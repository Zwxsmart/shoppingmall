package com.lanwantec.manage.manage.login;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** 根据operNo查找operator信息 */
	public Map<String, Object> findOperatorByOperNo(String operNo) {
		String sql = "SELECT * FROM tbl_operator WHERE operNo=? AND isValid=1";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, operNo);
		return list.size() > 0 ? list.get(0) : null;
	}

}
