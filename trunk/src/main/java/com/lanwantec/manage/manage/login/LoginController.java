package com.lanwantec.manage.manage.login;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lanwantec.manage.utils.MD5Utils;
import com.lanwantec.manage.utils.ReturnMessage;

import javax.servlet.http.HttpSession;

/**
 * 后台登录
 */
@Controller
@RequestMapping("/manage")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private ReturnMessage message;

	/** 登录页 */
	@GetMapping("/login")
	public ModelAndView login(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("operNo") == null) {
			mv.setViewName("manage/login");
		} else {
			if (loginService.existsOperNo(session.getAttribute("operNo").toString())) {
				mv.setViewName("manage/index");
			}
		}
		return mv;
	}

	/** 登录 */
	@ResponseBody
	@PostMapping("/login")
	public Map<String, Object> login(@RequestParam(value = "username") String username,
									 @RequestParam(value = "password") String password, HttpSession session) {
		String md5Pass = null;
		try {
			md5Pass = MD5Utils.md5Encode(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map = loginService.findOperatorByOperNo(username);
		if (map == null) {
			return message.createMessage("1", "1", "用户名或密码错误！");
		}else{
			System.out.println("now login sessionId:" + session.getId());
			session.setAttribute("operNo", map.get("operNo").toString());
			session.setAttribute("operName", map.get("operName").toString());
			session.setAttribute("operType", map.get("operName").toString());
			return message.createMessage("登陆成功！");
		}
	}

	/** 退出登录 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		if(session.getAttribute("operNo") ==null){
		}else{
			session.removeAttribute("operNo");
		}
		if(session.getAttribute("operName") ==null){
		}else{
			session.removeAttribute("operName");
		}
		if(session.getAttribute("operName") ==null){
		}else{
			session.removeAttribute("operName");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manage/login");
		return mv;
	}

}
