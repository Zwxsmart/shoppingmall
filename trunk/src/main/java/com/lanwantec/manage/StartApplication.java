package com.lanwantec.manage;

import com.alibaba.druid.pool.DruidDataSource;
import com.lanwantec.manage.filter.HTTPBasicAuthorizeAttribute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StartApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);

	}

	private static Class<StartApplication> applicationClass = StartApplication.class;

	/*
	@Autowired
	private Environment env;
	*/
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		/*
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username")); //用户名
		dataSource.setPassword(env.getProperty("spring.datasource.password")); //密码
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(20);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(false);
		*/
		return dataSource;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() { // 进入后台页时检测是否登录监听器
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBasicAuthorizeAttribute httpBasicFilter = new HTTPBasicAuthorizeAttribute();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/manage/index/*");
		urlPatterns.add("/manage/statistics/*");
		urlPatterns.add("/manage/store/*");
		urlPatterns.add("/manage/user/*");
		urlPatterns.add("/manage/webSetting/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

//	@Configuration
//	public class MyWebAppConfig extends WebMvcConfigurerAdapter {
//		@Override
//		public void addResourceHandlers(ResourceHandlerRegistry registry) {
//			registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/");
//			super.addResourceHandlers(registry);
//		}
//	}

}
