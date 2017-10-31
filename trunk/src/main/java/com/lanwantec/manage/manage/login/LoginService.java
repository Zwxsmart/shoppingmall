package com.lanwantec.manage.manage.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	/** 判断operNo是否存在 */
	public boolean existsOperNo(String operNo) {
		return findOperatorByOperNo(operNo) != null;
	}

	/** 根据operNo查找operator信息 */
	public Map<String, Object> findOperatorByOperNo(String operNo) {
		return loginDao.findOperatorByOperNo(operNo);
	}

}
