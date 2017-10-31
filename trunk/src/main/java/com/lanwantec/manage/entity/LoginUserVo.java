package com.lanwantec.manage.entity;

import java.util.HashMap;

public class LoginUserVo {
    private static HashMap<String, User> loginUserMap = new HashMap<String, User>();
    private static LoginUserVo loginUserVo;
    public static LoginUserVo getVo(){
        if(loginUserVo == null){
            loginUserVo = new LoginUserVo();
        }
        return loginUserVo;
    }
    public static HashMap<String, User> getLoginUserMap() {
        return loginUserMap;
    }
}
