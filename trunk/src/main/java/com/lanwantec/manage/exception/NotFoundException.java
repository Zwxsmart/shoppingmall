package com.lanwantec.manage.exception;

/**
 * Created by lys on 2017/8/2.
 */
public class NotFoundException extends BaseException{
    public NotFoundException(){
        super("没有找到对应的资源！");
    }
    public NotFoundException(String message){
        super(message);
    }
}
