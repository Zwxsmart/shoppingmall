package com.lanwantec.manage.filter;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@Configuration
@WebFilter(filterName = "myFilter", urlPatterns = "/s/log/go")
public class LogCollectFilter implements Filter {

	private Logger log = Logger.getLogger(this.getClass());

	private final String serviceLogURI = "/s/log/go";

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("过滤器初始化");
	}

	@Override
	public void destroy() {
		System.out.println("过滤器销毁");
	}

    /**
     * /s/log/go?
     * from=pc
     * &req_url=http://aaa.bbb.com(请求页面)
     * &referer=(来源页面)http://xxxx.xxx.com/a.html
     * &sid=客户随机Id
     * &bt=开始时间
     * &et=结束时间
     * &looker=用户token
     * &p=请求页面的请求参数
     */
	@Override
	public void doFilter(ServletRequest req0, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req0;
		request.setCharacterEncoding("utf-8");

		if (!request.getRequestURI().endsWith(serviceLogURI)) {
			chain.doFilter(request, response);
			return;
		}

		// visitor
		String visitor = request.getHeader("x-real-ip"); // 线上
		if (visitor == null) {
			visitor = request.getHeader("X-Forwarded-For"); // 线上
			if (visitor == null) {
				visitor = request.getRemoteAddr(); // 线下测试
				log.debug("remote addr:" + visitor);
			} else {
				log.debug("X-Forwarded-For:" + visitor);
			}
		} else {
			log.debug("x-real-ip:" + visitor);
		}
		String[] ips = visitor.trim().split(",");
		if (ips.length > 1) {
			visitor = ips[0].trim();
		}
		visitor = "{\"ip\":\"" + visitor + "\",\"requestDate\":\"" + System.currentTimeMillis() + "\"}";

		// header
		String _header = "{";
		Enumeration<String> header = request.getHeaderNames();
		while (header.hasMoreElements()) {
			String headerName = header.nextElement();
			_header = _header + "\"" + headerName + "\":\"" + request.getHeader(headerName) + "\",";
		}
		_header = _header.substring(0, _header.length() - 1) + "}";

		// param
		String param = "{";
		Map<String, String[]> paramMap = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			if (entry.getValue()[0].length() < 256) {
				param = param + "\"" + entry.getKey() + "\":\"" + entry.getValue()[0] + "\",";
			} else {
				param = param + "\"" + entry.getKey() + "\":\"" + entry.getValue()[0].substring(0, 256) + "\",";
			}
		}
		param = param.substring(0, param.length() - 1) + "}";
		if (param.length() > 2) {
			log.info("{\"base\":" + visitor + ",\"param\":" + param + ",\"header\":" + _header + "}");
		} else {
			System.out.println("无效的请求：" + "{\"base\":" + visitor + ",\"param\":" + param + ",\"header\":" + _header + "}");
		}

		chain.doFilter(request, response);
	}

}
