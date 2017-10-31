package com.lanwantec.manage.utils;

/**
 * Created by WangGenshen on 5/17/16.
 */
public class Constants {

    public static final String  DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String ADSSERVER = "adsserver";

    public static final String DEFAULT_ENCODING = "UTF-8";

    public static final String UPLOAD_IMAGES = "upload/images/";
    
    
    /** 测试号码 */
	public static String TEST_PHONE = "13901234567";

	/** 基本信息缓存失效时间（单位：秒） */
	public static int BASE_INFO_TIMEOUT = 300;

	/** 常量信息缓存失效时间 */
	public static int CONSTANTS_INFO_TIMEOUT = 3600;
	public static int QUICK_INFO_TIMEOUT = 120;
	public static int EVERYDAY_INFO_TIMEOUT = 3600 * 24;
	
	/** token缓存key的前缀 */
	public static String ACCESS_TOKEN_PREFIX = "shopping_access_token_";

	/** session中token的key */
	public static String ACCESS_TOKEN = "shopping_access_token";

	/** 登陆验证码缓存前缀 */
	public static String LOGON_CODE_PREFIX = "shopping_logon_code_";
	
	/** 手机验证码模版ID */
	public static String SMS_CODE_TEMPLATEID = "205009"; // 联盟家博城

}