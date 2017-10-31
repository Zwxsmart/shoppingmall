package com.lanwantec.manage.manage.user;

import com.lanwantec.manage.entity.User;
import com.lanwantec.manage.manage.index.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<Map<String, Object>> queryByData(Integer pageNumber, Integer pageSize, String userName, String startTime, String endTime){
        return (List<Map<String, Object>>) userDao.queryByData(pageNumber, pageSize,userName,startTime,endTime);
    }

    public Integer countByData(String userName, String startTime, String endTime) {
        return (Integer) userDao.countByData(userName,startTime,endTime);
    }

    public Map<String, Object> queryById(String userNo) {
        return userDao.queryById(userNo);
    }

    public Integer add(String userNo, String name, String nick, String gender, String phone, String birth,String address) {
        return userDao.add(userNo, name, nick, gender, phone, birth, address);
    }

    public Integer insert(String userNo, String name, String nick, String gender, String phone, String birth,String address, Date createDate) {
        return userDao.insert(userNo, name, nick, gender, phone, birth, address, createDate);
    }

    public Integer status(String status, String userNo) {
        return userDao.status(status, userNo);
    }

    public Map<String, Object> queryByBrowse(String userNo) {
        return userDao.queryByBrowse(userNo);
    }

}
