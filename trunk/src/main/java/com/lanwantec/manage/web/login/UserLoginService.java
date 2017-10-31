package com.lanwantec.manage.web.login;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanwantec.manage.utils.Constants;
import com.lanwantec.manage.utils.Helper;
import com.lanwantec.manage.utils.IDUtils;

@Service
public class UserLoginService {

	@Autowired
	private UserLoginDao userLoginDao;

	/** 登陆并获取登陆对象 */
	public Map<String, Object> logonByPhone(String phone, HttpSession session) {
		// 根据电话号码查询用户
		Map<String, Object> findUserByPhone = userLoginDao.findUserByPhone(phone);
		if (findUserByPhone == null) { // 不存在，注册
			String userNo = IDUtils.createUserNo();
			if (userLoginDao.registerUser(userNo, phone) > 0) {
				findUserByPhone = userLoginDao.findUserByPhone(phone);
			}
		}
		Map<String, Object> user = new LinkedHashMap<>();
		if (findUserByPhone != null) {
			user.put("userNo", findUserByPhone.get("userNo"));
			Object nick = findUserByPhone.get("nick");
			user.put("nick", null == nick ? findUserByPhone.get("phone") : nick);
			user.put("phone", findUserByPhone.get("phone"));
			// 加入缓存
			saveLoginData(user, session);
		}
		return user;
	}

	/** 根据openId登陆并获取登陆对象 */
	public void loginByOpenId(String openId, HttpSession session) {
		// 根据电话号码查询用户
		Map<String, Object> user = new LinkedHashMap<>();
		Map<String, Object> findUserByOpenId = userLoginDao.findUserByOpenId(openId);
		user.put("userNo", findUserByOpenId.get("userNo"));
		Object nick = findUserByOpenId.get("nick");
		user.put("nick", null == nick ? findUserByOpenId.get("phone") : nick);
		user.put("phone", findUserByOpenId.get("phone"));
		// 加入缓存
		saveLoginData(user, session);
	}

	/** 绑定手机并获取登陆对象 */
	public Map<String, Object> logonByPI(String phone,String openId, HttpSession session) {
		// 根据电话号码查询用户
		String userNo = IDUtils.createUserNo();
		Map<String, Object> findUserByPhone = new HashMap<>();
		if(userLoginDao.queryByIsPhone(phone)!=0) { // 此手机号码存在时
			if (userLoginDao.updateUser(openId, phone) > 0) {
				findUserByPhone = userLoginDao.findUserByPhone(phone);
				saveLoginData(findUserByPhone, session);
			}
		}else{ // 不存在时
			if (userLoginDao.registerUser(userNo, openId, phone) > 0) {
				findUserByPhone = userLoginDao.findUserByPhone(phone);
				saveLoginData(findUserByPhone, session);
			}
		}
		return findUserByPhone;
	}

	/** 绑定手机并获取登陆对象 */
	public Map<String, Object> logonByPI1(String phone,String openId, HttpSession session) {
		// 根据电话号码查询用户
		String userNo = IDUtils.createUserNo();
		Map<String, Object> findUserByPhone = new HashMap<>();
		if(userLoginDao.queryByIsOpenId(openId)!=0) { // 此openId存在时
			if (userLoginDao.updateUser1(openId, phone) > 0) {
				findUserByPhone = userLoginDao.findUserByPhone(phone);
				saveLoginData(findUserByPhone, session);
			}
		}else{ // 不存在时
			if (userLoginDao.registerUser(userNo, openId, phone) > 0) {
				findUserByPhone = userLoginDao.findUserByPhone(phone);
				saveLoginData(findUserByPhone, session);
			}
		}
		return findUserByPhone;
	}

	/** 用户登录信息加入缓存 */
	private void saveLoginData(Map<String, Object> user, HttpSession session) {
		try {
			String userNo = user.get("userNo").toString();
			String key = Constants.ACCESS_TOKEN;
			Object token = session.getAttribute(key);
			if (token == null) {
				token = createLoginToken(userNo);
				// 保存新的token信息
				session.setAttribute(key, token);
			}
			user.put("access_token", token);
			session.setAttribute((String) token,  user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 生成用户登陆令牌 */
	private String createLoginToken(String userNo) {
		String token = Helper.uuid();
		String logonTime = String.format("%tF", new Date()).replaceAll("-", "");
		token = logonTime + token.substring(0, 4) + "-" + userNo + "-" + token.substring(4);
		return token;
	}

	/** 根据openId 查询数据库是否有已有账号 */
	public Map<String, Object> queryByOpenId(String openId) {
		return userLoginDao.queryByOpenId(openId);
	}
}
