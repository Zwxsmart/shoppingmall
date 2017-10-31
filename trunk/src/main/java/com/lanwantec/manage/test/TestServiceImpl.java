package com.lanwantec.manage.test;


import com.lanwantec.manage.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("testService")
public class TestServiceImpl {

    @Autowired
    TestDaoImpl testDao;

    public List<Map<String,Object>> getTestUser(){
        return testDao.getTestUser();
    }

    public List<Map<String,String>> update(String name) throws NotFoundException {
        List<Map<String,String>> userList= new ArrayList<>();
        if (!name.equals("abc")){
            throw new NotFoundException();
        }
        Map<String,String> user = new HashMap<>();
        user.put(name,"123");
        userList.add(user);
        return userList;
    }

    public boolean create(String name){

        return true;
    }

}
