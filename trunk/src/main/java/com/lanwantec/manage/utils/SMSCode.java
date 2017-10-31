package com.lanwantec.manage.utils;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

/**
 * 短信验证码生成工具
 * 
 * @author lys
 * @since 2015-10-26
 * @version 3.0
 *
 */
public class SMSCode {

	private static String smsServer = "app.cloopen.com";// sandboxapp.cloopen.com";
	private static String smsServerPort = "8883";
	private static String ACCOUNT_SID = "8a48b5515147eb6d0151662643434bf7";
	private static String AUTH_TOKEN = "b36402b82c7e494bb4b674880d4c521b";
	private static String APP_ID = "8a216da85e7e4bbd015e7ed7fcd0001e";
	private static HashMap<String, Object> result = null;
	private static CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
	static {
		// ******************************注释*********************************************
		// *初始化服务器地址和端口 *
		// *沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		// *生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883"); *
		// *******************************************************************************
		restAPI.init(smsServer, smsServerPort);

		// ******************************注释*********************************************
		// *初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN *
		// *ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		// *参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。 *
		// *******************************************************************************
		restAPI.setAccount(ACCOUNT_SID, AUTH_TOKEN);
		restAPI.setAppId(APP_ID);
	}

	/**
	 * 发送手机消息
	 * 
	 * @param phone
	 *            接收的手机号码
	 * @param message
	 *            发送的消息信息
	 */
	public static String sendSMS(String phone, String templateId, String[] smsParam) {
		boolean isSend = false;
		result = restAPI.sendTemplateSMS(phone, templateId, smsParam);
		String sendStatus = (String) result.get("statusCode");
		System.out.println("SDKTestGetSubAccounts result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			isSend = true;
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			}
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
		}
		return sendStatus;
	}

	private static void initSms() {
		// 初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

		// ******************************注释*********************************************
		// *初始化服务器地址和端口 *
		// *沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		// *生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883"); *
		// *******************************************************************************
		restAPI.init(smsServer, smsServerPort);

		// ******************************注释*********************************************
		// *初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN *
		// *ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		// *参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。 *
		// *******************************************************************************
		restAPI.setAccount(ACCOUNT_SID, AUTH_TOKEN);
		restAPI.setAppId(APP_ID);
	}

}
