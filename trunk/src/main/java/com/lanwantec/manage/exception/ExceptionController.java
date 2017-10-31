package com.lanwantec.manage.exception;

import com.lanwantec.manage.utils.ReturnMessage;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * 重写BasicErrorController,主要负责系统的异常页面的处理以及错误信息的显示
 *
 * @author lys
 * @version 2016/1/17
 * @see org.springframework.boot.autoconfigure.web.BasicErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
 * @since JDK 8.0+
 */
@Controller
@EnableConfigurationProperties({ServerProperties.class})
public class ExceptionController implements ErrorController {

    private ErrorAttributes errorAttributes;

    @Autowired
    private ReturnMessage message;

    @Autowired
    private ServerProperties serverProperties;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("${logging.level.log}")
    private String logLevel;

    /**
     * 初始化ExceptionController
     *
     * @param errorAttributes
     */
    @Autowired
    public ExceptionController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }


    @Value("${server.error.path:/error}")
    private static final String ERROR_PATH ="/error/error";


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 用于测试
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/error/test")
    @ResponseBody
    public Object test(@RequestParam("id") int id,@RequestParam("name") String name){
        int total= 10+id;
        return name+total;
    }

    /**
     * 还没有对应没权限的错误机制
     * @return
     */
    @RequestMapping("/error/unauthorized")
    @ResponseBody
    public ModelMap unauthorized(){
        ModelMap mv = new ModelMap();
        Map<String,Object> map = new HashedMap();
        map.put("state",-1);
        map.put("stateName","您没有权限!");
        mv.put("data",map);
        return mv;
    }

    /**
     * 定义500的ModelAndView
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(produces = "text/html", value = ERROR_PATH)
    public ModelAndView errorHtml500(HttpServletRequest request,
                                     HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("url", request.getQueryString()==null?"":"?"+request.getQueryString());

        ModelAndView mv = new ModelAndView();
        mv.setViewName(ERROR_PATH);
        mv.addObject("error", model);

        logger.error("请求来源IP:" + request.getRemoteAddr() + ",访问时间:" + model.get("timestamp") + ",请求地址:" + model.get("path")  + model.get("url") +
                ",错误状态：" + getStatus(request).value() + ",错误信息：" + model.get("message"));

        return mv;
    }


    /**
     * 定义500的错误JSON信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Map<String, Object> error500(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        body.put("url", request.getQueryString()==null?"":"?"+request.getQueryString());
        HttpStatus status = getStatus(request);
        logger.error("请求来源IP:" + request.getRemoteAddr() + ",访问时间:" + body.get("timestamp") + ",请求地址:" + body.get("path")  + body.get("url") +
                ",错误状态：" + getStatus(request).value() + ",错误信息：" + body.get("message"));

        if (!logLevel.equalsIgnoreCase("debug")) body=null;
        return message.createMessage("1",String.valueOf(status.value()),body);
        //return new ResponseEntity<Map<String, Object>>(body, status);
    }


    /**
     * Determine if the stacktrace attribute should be included.
     *
     * @param request  the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request,
                                          MediaType produces) {
        System.out.println("referer:"+request.getHeader("referer"));
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }


    /**
     * 获取错误的信息
     *
     * @param request
     * @param includeStackTrace
     * @return
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                   boolean includeStackTrace) {
        System.out.println("referer:"+request.getHeader("referer"));
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes,
                includeStackTrace);
    }

    /**
     * 是否包含trace
     *
     * @param request
     * @return
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    /**
     * 获取错误编码
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}