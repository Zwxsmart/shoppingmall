package com.lanwantec.manage.web.user;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalCenterService {

	@Autowired
	private PersonalCenterDao personalCenterDao;

	/** 根据token获取用户信息 */
	public Map<String, Object> getUserByToken(String token, HttpSession session) {
		String userNo = getUserNoByToken(token, session);
		if (userNo != null) {
			return personalCenterDao.getUserByUserNo(userNo);
		}
		return null;
	}

	/** 修改用户信息 */
	public boolean updataUser(String token, String nick, String phone, String gender, String email, HttpSession session) {
		String userNo = getUserNoByToken(token, session);
		if (userNo != null) {
			// 更新用户信息
			if (personalCenterDao.updataUserByUserNo(userNo, nick, phone, gender, email) > 0) {
				if (updataMemCached(token, userNo, session)) { // 更新缓存
					return true;
				}
			}
		}
		return false;
	}

	public boolean update1(String token, String nick, String gender, String phone,String email, String portrait, HttpSession session) {
		String userNo = getUserNoByToken(token, session);
		if (userNo != null) {
			// 更新用户信息
			if (personalCenterDao.update1(userNo, nick, phone, gender, email, portrait) > 0) {
				if (updataMemCached(token, userNo, session)) { // 更新缓存
					return true;
				}
			}
		}
		return false;
	}

	/** 更新缓存 */
	public boolean updataMemCached(String token, String userNo, HttpSession session) {
		Map<String, Object> map = personalCenterDao.getUserByUserNo(userNo);
		Map<String, Object> user = new LinkedHashMap<>();
		if (map != null) {
			user.put("userNo", map.get("userNo"));
			Object nick = map.get("nick");
			user.put("nick", null == nick ? map.get("phone") : nick);
			user.put("phone", map.get("phone"));
			try {
				session.setAttribute(token, user);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/** 根据token获取用户编号 */
	@SuppressWarnings("unchecked")
	public String getUserNoByToken(String token, HttpSession session) {
		String userNo = null;
		try {
			Object value = session.getAttribute(token);
			if (value != null) {
				userNo = ((Map<String, Object>) value).get("userNo").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userNo;
	}

}
