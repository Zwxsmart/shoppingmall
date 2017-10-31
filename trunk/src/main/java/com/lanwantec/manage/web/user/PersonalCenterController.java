package com.lanwantec.manage.web.user;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanwantec.manage.utils.Helper;
import com.lanwantec.manage.utils.ReturnMessage;

/**
 * 用户
 */
@Controller
@RequestMapping("/user")
public class PersonalCenterController {

	@Autowired
	private PersonalCenterService personalCenterService;

	@Autowired
	private ReturnMessage message;

	private Logger log = Logger.getLogger(this.getClass());

	/** 修改用户信息 */
	@ResponseBody
	@PostMapping("/update")
	public Map<String, Object> updataUser(@RequestParam String nick, @RequestParam String phone,
										  @RequestParam String gender, @RequestParam String email, HttpSession session) {
		if (!Helper.checkPhone(phone)) { // 手机格式校验
			return message.createMessage("1", "1", "手机号码无效！");
		}
		if (!"M".equals(gender) && !"F".equals(gender)) { // 性别校验
			return message.createMessage("1", "1", "性别无效！");
		}
		if (!Helper.checkEmail(email)) { // 邮箱校验
			return message.createMessage("1", "1", "邮箱无效！");
		}

		String token = Helper.getToken(session);
		if (token != null && !"".equals(token)) {
			if (personalCenterService.updataUser(token, nick, phone, gender, email, session)) {
				return message.createMessage("信息修改成功！");
			}
		}
		return message.createMessage("1", "1", "信息修改失败！");
	}

	/** 修改用户信息1 */
	@ResponseBody
	@PostMapping("/update1")
	public Map<String, Object> updataUser1(@RequestParam String nick, @RequestParam String phone,
										  @RequestParam String gender, @RequestParam String email,
										   String portrait, HttpSession session) {
		if (!Helper.checkPhone(phone)) { // 手机格式校验
			return message.createMessage("1", "1", "手机号码无效！");
		}
		if (!"M".equals(gender) && !"F".equals(gender)) { // 性别校验
			return message.createMessage("1", "1", "性别无效！");
		}
		if (!Helper.checkEmail(email)) { // 邮箱校验
			return message.createMessage("1", "1", "邮箱无效！");
		}

		String token = Helper.getToken(session);
		if (token != null && !"".equals(token)) {
			if (personalCenterService.update1(token, nick, gender, phone, email, portrait,session)) {
				return message.createMessage("信息修改成功！");
			}
		}
		return message.createMessage("1", "1", "信息修改失败！");
	}

	/** 获取用户信息 */
	@ResponseBody
	@GetMapping("/info")
	public Map<String, Object> getUserByToken(HttpSession session) {
		String token = Helper.getToken(session);
		Map<String, Object> user = personalCenterService.getUserByToken(token, session);
		if (user != null) {
			return message.createMessage(user);
		}
		return message.createMessage("1", "1", "请先登录！");
	}

}
