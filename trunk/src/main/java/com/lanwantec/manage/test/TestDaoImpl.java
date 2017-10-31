package com.lanwantec.manage.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("testDao")
public class TestDaoImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getTestUser() {
        String sql = "SELECT  * FROM  tbl_User limit 5";
        return jdbcTemplate.queryForList(sql);
    }
}
