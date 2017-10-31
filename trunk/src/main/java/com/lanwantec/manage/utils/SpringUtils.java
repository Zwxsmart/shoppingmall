package com.lanwantec.manage.utils;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 装配ApplicationContext
 * 功能：为先执行的程序提供依赖
 * 用法：直接从ApplicationContext中getBean
 */
@Configuration
public class SpringUtils implements ApplicationContextAware {

    private static Logger logger = Logger.getLogger(SpringUtils.class);

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
            logger.info("ApplicationContext config success");
        }
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static Object getBean(String name, Class<T> entity)
            throws BeansException {
        return getApplicationContext().getBean(name, entity);
    }

}
