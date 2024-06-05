package com.example.myweb.user;

import java.util.List;

public interface UserService {

	UserVO getOne(UserVO vo);

	List<UserVO> getList(UserVO vo);

	void insert(UserVO vo);

	void update(UserVO vo);

	void delete(UserVO vo);

}