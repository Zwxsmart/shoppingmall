package com.lanwantec.manage.web.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanwantec.manage.utils.SignUtil;

@Controller
public class CoreController {

	/**
	 *  /wechatCore 总入口/出口
	 *  验证
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/wechatCore",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public void wechat_core_get(@RequestParam String signature, @RequestParam String timestamp,@RequestParam String nonce, @RequestParam String echostr, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 微信加密签名
//		String signature = request.getParameter("signature");
//		//时间戳
//		String timestamp = request.getParameter("timestamp");
//		//随机数
//		String nonce = request.getParameter("nonce");
//		//随机字符串
//		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		// 通过检验 signature 对请求进行校验， 若校验成功则原样返回 echostr， 表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
	}

}
