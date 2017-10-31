package com.lanwantec.manage.web.login;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lanwantec.manage.entity.AccessToken;
import com.lanwantec.manage.entity.LoginUserVo;
import com.lanwantec.manage.entity.SnsUserInfo;
import com.lanwantec.manage.entity.User;
import com.lanwantec.manage.entity.WeixinOauth2Token;
import com.lanwantec.manage.utils.Constants;
import com.lanwantec.manage.utils.Helper;
import com.lanwantec.manage.utils.ReturnMessage;
import com.lanwantec.manage.utils.SMSCode;

/**
 * 用户注册或登录
 */
@SuppressWarnings("deprecation")
@Controller
public class UserLoginController {

	@Value("${server.error.path:/error}")
	private static final String ERROR_PATH ="/error/error";

	@Autowired
	private UserLoginService userLoginService;

	@Autowired
	private ReturnMessage message;

	private static Logger log = Logger.getLogger(UserLoginController.class);

	public static final String appId = "wx9c8f9ca45ad8086f";//"wx9c8f9ca45ad8086f";
	public static final String appsecret = "c0089c9ba4e424a15f0509d1279744b2";//"c0089c9ba4e424a15f0509d1279744b2";
	public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String AUTH2 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static final String SNS_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
	public static final String USER_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";
	public static final String WXGZHToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String IS_FOLLOW = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	private static final String CREATE_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

	/** 获取验证码 */
	@ResponseBody
	@GetMapping("/login/code")
	public Map<String, Object> getLogonCode(@RequestParam(required = true, value = "phone") String phone, HttpSession session) {
		if (!Helper.checkPhone(phone)) { // 手机格式校验
			return message.createMessage("1", "1", "手机号码无效！"); // 手机号码无效
		}
		try {
			if (phone.equalsIgnoreCase(Constants.TEST_PHONE)) { // 判断测试用户
				session.setAttribute(Constants.LOGON_CODE_PREFIX + phone,  "1234");
				return message.createMessage("验证码发送成功！");
			}

			String code = Helper.createCode(4); // 生成4位验证码
			log.debug("login_code: " + code);
			session.setAttribute(Constants.LOGON_CODE_PREFIX + phone, code);
			SMSCode.sendSMS(phone, Constants.SMS_CODE_TEMPLATEID, new String[] { code });
			return message.createMessage("验证码发送成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return message.createMessage("1", "1", "验证码发送失败！");
		}
	}

	/** qrCode */
	@GetMapping("/qrCodeImg")
	public ModelAndView qrCodeImg() {
		AccessToken ass = getAccessToken(appId, appsecret);
		String ticket = getTicket("xing",ass.getAccessToken());

		return new ModelAndView("redirect:https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket);
	}

	/**
	 * 生成带参数的二维码
	 * 具体请参阅@https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1443433542
	 * @param scene_str 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
	 * @param token
	 * @return
	 */
	public static String getTicket(String scene_str,String token) {
		String url = CREATE_QRCODE_URL.replace("TOKEN", token);
		String json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+scene_str+"\"}}}";
		JSONObject obj = doPostStr(url, json);
		if(obj != null){
			String ticket = obj.getString("ticket");
			log.info("获取ticket成功："+ticket);
			return ticket;
		}else {
			log.error("获取ticket失败");
			return null;
		}
	}

	/** 用户注册或登录 */
	@ResponseBody
	@PostMapping("/login")
	public Map<String, Object> userLogon(@RequestParam(required = true, value = "phone") String phone,
			@RequestParam(required = true, value = "checkCode") String checkCode, HttpSession session) {
		if (!Helper.checkPhone(phone)) { // 手机格式校验
			return message.createMessage("1", "1", "手机号码无效！"); // 手机号码无效
		}
		try {
			// 从缓存中获取登陆验证码
			String key = Constants.LOGON_CODE_PREFIX + phone;
			String code =(String)session.getAttribute(key);
			if (code != null && !"".equals(code) && code.equals(checkCode)) { // 验证码正确
				session.removeAttribute(key); // 删除缓存中的验证码
				return message.createMessage(userLoginService.logonByPhone(phone, session));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 验证码不正确或不存在验证码或异常
		return message.createMessage("1", "1", "验证码错误，请重新输入！");
	}

	/** 用户扫码登录1 */
	@ResponseBody
	@PostMapping("/login1")
	public Map<String, Object> userLogon1(@RequestParam(required = true, value = "phone") String phone,
										 @RequestParam(required = true, value = "checkCode") String checkCode,
										  @RequestParam(required = true, value = "openId") String openId,HttpSession session) {
		if (!Helper.checkPhone(phone)) { // 手机格式校验
			return message.createMessage("1", "1", "手机号码无效！"); // 手机号码无效
		}
		try {
			if(openId==null | openId.equals("")){
				return message.createMessage("1", "1", "登录失败！"); // 手机号码无效
			}
			// 从缓存中获取登陆验证码
			String key = Constants.LOGON_CODE_PREFIX + phone;
			String code =(String)session.getAttribute(key);
			if (code != null && !"".equals(code) && code.equals(checkCode)) { // 验证码正确
				session.removeAttribute(key); // 删除缓存中的验证码
				return message.createMessage(userLoginService.logonByPI(phone, openId, session));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 验证码不正确或不存在验证码或异常
		return message.createMessage("1", "1", "验证码错误，请重新输入！");
	}

	/** 用户扫码登录2 */
	@ResponseBody
	@PostMapping("/login2")
	public Map<String, Object> userLogon2(@RequestParam(required = true, value = "phone") String phone,
										  @RequestParam(required = true, value = "checkCode") String checkCode,
										  @RequestParam(required = true, value = "openId") String openId,HttpSession session) {
		if (!Helper.checkPhone(phone)) { // 手机格式校验
			return message.createMessage("1", "1", "手机号码无效！"); // 手机号码无效
		}
		try {
			if(openId==null | openId.equals("")){
				return message.createMessage("1", "1", "登录失败！"); // 手机号码无效
			}
			// 从缓存中获取登陆验证码
			String key = Constants.LOGON_CODE_PREFIX + phone;
			String code =(String)session.getAttribute(key);
			if (code != null && !"".equals(code) && code.equals(checkCode)) { // 验证码正确
				session.removeAttribute(key); // 删除缓存中的验证码
				return message.createMessage(userLoginService.logonByPI1(phone, openId, session));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 验证码不正确或不存在验证码或异常
		return message.createMessage("1", "1", "验证码错误，请重新输入！");
	}

	/** qrCode */
	@GetMapping("/qrCode")
	public ModelAndView qrCode() {
		return new ModelAndView("manage/qrCode");
	}

	/** qrCode */
	@ResponseBody
	@GetMapping("/qrCodeStr")
	public ModelMap qrCodeStr(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap md = new ModelMap();
		String uuid = request.getParameter("uuid");
		User userVo = null;
		long inTime = new Date().getTime();
		Boolean bool = true;
		while(bool) {
			try{
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			//检测登录
			userVo = LoginUserVo.getLoginUserMap().get(uuid); // 根据uuid去匹配有没有已经扫码过的账户
			if(userVo==null){
				if(new Date().getTime() - inTime > 1000 *60*3){
					bool = false;
					md.put("code", 103); // 二维码失效, 请重新打开页面
				}
			}else{
				if(userVo.getCode() == 0){
					bool = false;
					LoginUserVo.getLoginUserMap().remove(uuid);
					userLoginService.loginByOpenId(userVo.getWxOpenId(), session);
					System.out.println("登录成功");
					md.put("code", 0);
				}if(userVo.getCode() == 101){ //需要前台绑定手机号新增user用户
					bool = false;
					LoginUserVo.getLoginUserMap().remove(uuid);
					System.out.println("新增...");
					md.put("openId", userVo.getWxOpenId());
					md.put("code", 101);
				}if(userVo.getCode() == 102){ //需要前台绑定手机号修改user用户
					bool = false;
					LoginUserVo.getLoginUserMap().remove(uuid);
					System.out.println("绑定手机...");
					md.put("openId", userVo.getWxOpenId());
					md.put("code", 102);
				}
			}
		}
		return md;
	}


	/** showWechatLogin */
	@GetMapping("/showWechatLogin")
	public ModelAndView qrCodeStr1( @RequestParam(value = "code", required = true)String code,
								   @RequestParam(value = "state", required = true)String uuid) {
		ModelAndView mav = new ModelAndView();
		JSONObject userJson = doGetStr(USER_OPENID.replace("APPID", appId).replace("APPSECRET", appsecret).replace("CODE", code));
		if(userJson == null | userJson.getString("openid") == null) {
			System.out.println("openid获取失败");
			mav.setViewName(ERROR_PATH);
			return mav;
		}
		String openid = userJson.getString("openid");//拿到openid
		System.out.println(openid);
		mav.addObject("uuid", uuid);
		mav.addObject("openId", openid);
		mav.setViewName("mb/wechatLogin");
		return mav;
//		ModelAndView mav = new ModelAndView();
//		JSONObject userJson = doGetStr(USER_OPENID.replace("APPID", appId).replace("APPSECRET", appsecret).replace("CODE", code));
//		if(userJson == null | userJson.getString("openid") == null) {
//			System.out.println("openid获取失败");
//			mav.setViewName(ERROR_PATH);
//			return mav;
//		}
//		String openid = userJson.getString("openid");//拿到openid
//		System.out.println(openid);
//		//我们需要获取当前公众号通用的access_token 和用户的access_token是不一样的
//		//这里我为了让大家可以方便就没有写太复杂  因为微信他那边获取微信公众号的通用access_token每天只能取2000次 每次token有效期是7200S 所以在自己动手写最好放在缓存中 我的项目放在redis中
//		String requestUrl = WXGZHToken.replace("APPID", appId).replace("APPSECRET", appsecret);
//		JSONObject accJson = doGetStr(requestUrl);
//		if(accJson == null | accJson.getString("access_token")== null){
//			mav.setViewName(ERROR_PATH);
//			return mav;
//		}
//		String wxgzhToken =accJson.getString("access_token");
//		//这里是获取用户在我们公众里面的信息 如果没有关注公众号那么就没有办法获取详细信息 参数需要 微信公众号通用token 和 用户openid
//		String attentionUrl = IS_FOLLOW.replace("ACCESS_TOKEN", wxgzhToken).replace("OPENID", openid);
//		JSONObject attentionUrlJson = doGetStr(attentionUrl);
//		if(attentionUrlJson == null) {
//			mav.setViewName(ERROR_PATH);
//			return mav;
//		}
//		Integer subscribe = attentionUrlJson.getInt("subscribe");//是否有关注我们公众号
//		if(subscribe == 0){//没有关注我们公众号
////			AccessToken ass = getAccessToken(appId, appsecret);
////			String ticket = getTicket("xing",ass.getAccessToken());
//			mav.setViewName("mb/foloow");
//			return mav;
//		}else{//关注了
//			mav.addObject("uuid", uuid);
//			mav.addObject("openId", "aaaa");
//			mav.setViewName("mb/wechatLogin");
//			return mav;
//		}
	}

	/** 二维码失效页面 */
	@GetMapping("/showLose")
	public ModelAndView showLose() {
		return new ModelAndView("mb/wechat_lose");
	}

	/** wechatLogin */
	@ResponseBody
	@GetMapping("/wechatLogin")
	public void qianming1(@RequestParam(value = "openId", required = true)String openId,
								 @RequestParam(value = "uuid", required = true)String uuid) {
		Map<String, Object> user = userLoginService.queryByOpenId(openId);
		System.out.println(openId);
		User user1 = new User();
		if(user==null) {
			user1.setCode(101);// 101  需要前台绑定手机号新增user用户
			user1.setWxOpenId(openId);
			LoginUserVo.getLoginUserMap().put(uuid, user1);
			//return message.createMessage("1", "101", "请绑定手机号码");
			// 数据库什么都没有, 需要前台绑定手机号新增user用户
			return;
		}else{
			if (user.get("phone") .equals("")) {// 此微信没有绑定手机号时, 弹出绑定手机界面
				user1.setCode(102);// 102 请绑定手机号码
				user1.setWxOpenId(openId);
				LoginUserVo.getLoginUserMap().put(uuid, user1);
				return;
				// 数据库有openId, 但是手机号为空, 需要前台绑定手机号修改user用户
			}else { // 此微信已经绑定手机号时, 直接登录成功
				System.out.println(uuid);
				//TODO 验证登录
				boolean bool = true;
				user1.setWxOpenId(openId);
				user1.setPhone((String)user.get("phone"));
				user1.setCode(0);// 0 则登录成功
				LoginUserVo.getLoginUserMap().put(uuid, user1);
				return;
			}
		}
	}

	//	/** qianming */
//	@ResponseBody
//	@PostMapping("/qianming")
//	public ModelMap qianming(HttpServletRequest request) {
//		// 注意 URL 一定要动态获取，不能 hardcode
//		String url = request.getParameter("url");
//	    AccessToken a = getAccessToken(appId, appsecret);
//		JSONObject jsonObject = doGetStr("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+a.getAccessToken()+"&type=jsapi");
//		String jsapi_ticket = jsonObject.getString("ticket");
//		Map<String, String> ret = Sign.sign(jsapi_ticket, url);
//		for (Map.Entry entry : ret.entrySet()) {
//			System.out.println(entry.getKey() + ", " + entry.getValue());
//		}
//		ModelMap mav = new ModelMap();
//		mav.put("ret" , ret);
//		mav.put("appId", appId);
//		return mav;
//	}

	/**
	 * 微信授权换取token,openId
	 * 具体请参考@https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
	 * @param code
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String code, String appid, String appsecret){
		String url = AUTH2.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
		System.out.println(url);
		JSONObject jsonObject = doGetStr(url);
		System.out.println(jsonObject.toString());
		WeixinOauth2Token wat = null;
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:"+errorCode+" errmsg:"+errorMsg);
				log.error("Exception:" + e);
			}
		}
		return wat;
	}

	public static String getsue(String appid){
		String url = AUTH2.replace("APPID", appid);
		System.out.println(url);
		JSONObject jsonObject = doGetStr(url);
		System.out.println(jsonObject.toString());
		if (null != jsonObject) {
			try {
				return jsonObject.getString("code");
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:"+errorCode+" errmsg:"+errorMsg);
				log.error("Exception:" + e);
			}
		}
		return null;
	}

	/**
	 * 获取access_token
	 * 具体请参考@https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
	 * @return
	 */
	public static AccessToken getAccessToken(String appId, String appsecret) {
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", appId).replace("APPSECRET", appsecret);
		JSONObject jsonObject = doGetStr(url);
		if(jsonObject != null){
			token.setAccessToken(jsonObject.getString("access_token"));
		}else{
			log.info("获取access_token失败");
		}
		return token;
	}

	/**
	 * 获取用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SnsUserInfo getSnsUserInfo(String accessToken, String openId) {
		SnsUserInfo snsUserInfo = null;
		// 拼接请求地址
		String url = SNS_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		System.out.println(url);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = doGetStr(url);

		if (null != jsonObject) {
			try {
				snsUserInfo = new SnsUserInfo();
				// 用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// 昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// 性别（1是男性，2是女性，0是未知）
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				// 用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 用户特权信息
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:"+errorCode+"  errmsg:"+errorMsg);
			}
		}
		return snsUserInfo;
	}

	/**
	 * post方式
	 * @param url
	 * @param outStr
	 * @return
	 */
	@SuppressWarnings("resource")
	public static JSONObject doPostStr(String url,String outStr) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			post.setEntity(new StringEntity(outStr, "UTF-8"));
			HttpResponse response = httpClient.execute(post);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (Exception e) {
			log.error("发送POST请求失败,URL：" + url);
			log.error("发送POST请求失败,Excption：" + e);
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 *  get方式
	 * @param url
	 * @return
	 */
	@SuppressWarnings("resource")
	public static JSONObject doGetStr(String url){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		JSONObject jsonObject = null;
		try {
			HttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				String result = EntityUtils.toString(entity,"UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (Exception e) {
			log.error("发送GET请求失败,URL：" + url);
			log.error("发送GET请求失败,Excption：" + e);
			e.printStackTrace();
		}
		return jsonObject;
	}
}
