package com.lanwantec.manage.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by lys on 2017/8/2.
 */
@Component
public class ReturnMessage {

    @Autowired
    private MessageSource messageSource;


    /**
     * 创建返回对象(业务正常返回数据)
     * @param data
     * @return
     */
    public Map<String,Object> createMessage(Map<String,Object> data){
        return createMessage("0","0","成功！",data);
    }


    /**
     * 创建返回对象(从资源文件中读取消息）
     * @param statusCode
     * @param code
     * @param data
     * @return
     */
    public Map<String,Object> createMessage(String statusCode,String code,Map<String,Object> data){
        return createMessage(statusCode,code,"",data);

    }

    /**
     * 创建返回对象(返回自定义信息及详细数据）
     * @param statusCode
     * @param code
     * @param data
     * @return
     */
    public Map<String,Object> createMessage(String statusCode,String code,String info,Map<String,Object> data){
        Map<String, Object> returnObj = new LinkedHashMap<String, Object>();
        returnObj.put("returnStatus", createStatus(statusCode,code,info));
        returnObj.put("data",data);
        return returnObj;
    }

    /**
     * 创建返回对象（返回自定义信息无详细数据）
     * @param statusCode
     * @param code
     * @return
     */
    public Map<String,Object> createMessage(String statusCode,String code,String info){
        return createMessage(statusCode,code,info,null);
    }

    /**
     * 创建返回对象(从资源文件中读取信息无详细数据）
     * @param statusCode
     * @param code
     * @return
     */
    public Map<String,Object> createMessage(String statusCode,String code){
        return createMessage(statusCode,code,"",null);
    }

    /**
     * 创建返回对象(业务正常自定义信息无详细数据）
     * @param info
     * @return
     */
    public Map<String,Object> createMessage(String info){
        return createMessage("0","0",info,null);

    }



    /**
     * 从消息资源文件中得到国际化消息
     * @param messageTag
     * @return
     */
    private String getError(String messageTag){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageTag,null,locale);
    }


    /**
     * 创建返回对象状态
     * @param statusCode
     * @param code
     * @return
     */
    private  Map<String,Object> createStatus(String statusCode, String code,String info){

        Map<String,Object> status=new LinkedHashMap<String,Object>();
        status.put("status",statusCode);
        status.put("code", code);
        if (info==null || info.equalsIgnoreCase("")){
            String msg = getError(code);
            status.put("info",msg);
        }else {
            status.put("info", info);
        }
        return status;
    }
}
