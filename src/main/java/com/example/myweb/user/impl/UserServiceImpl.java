package com.example.myweb.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.myweb.user.UserService;
import com.example.myweb.user.UserVO;

@Service("userService")
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userdao;
	
	//@Autowired
	//@Qualifier("logAdvice")
	//@Inject
	//private LogAdvice logAdvice;

	
	public UserVO getOne(UserVO vo) {
		//logAdvice.printLog();
		return userdao.getUser(vo);
	}

	@Override
	public List<UserVO> getList(UserVO vo) {
		System.out.println("getList() - BoardServiceImpl 실행");
		//logAdvice.printLog();
		return userdao.getUserList();
	}

	@Override
	public void insert(UserVO vo) {
		//logAdvice.printLog();
		userdao.insertUser(vo);
	}

	@Override
	public void update(UserVO vo) {
		//logAdvice.printLog();
		userdao.updateUser(vo);
	}

	@Override
	public void delete(UserVO vo) {
		//logAdvice.printLog();
		userdao.deleteUser(vo);
	}

}
