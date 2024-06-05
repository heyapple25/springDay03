package com.example.myweb.user.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.myweb.user.UserService;
import com.example.myweb.user.UserVO;

import jakarta.annotation.Resource;

@Controller
public class UserController {
	@Autowired
	private UserService us;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/user/list.do", method=RequestMethod.GET)
	public String userList(Model m) throws SQLException{
		if(us==null)
			System.out.println("null인데?");
		List<UserVO>userList=us.getList(null);
		m.addAttribute("userList",userList);
		return "user/list";
	}
	@RequestMapping(value="/user/detail.do", method=RequestMethod.GET)
	public String userDetail(UserVO u,Model m) throws SQLException{
		m.addAttribute("user",us.getOne(u));
		return "user/detail";
	}
	@RequestMapping(value="/user/delete.do", method=RequestMethod.GET)
	public String userDelete(UserVO u,Model m) throws SQLException{
		logger.info("userDelete 진행 ");
		us.delete(u);
		//throw new RuntimeException();
		return "redirect:list.do";
	}
	@RequestMapping(value="/user/insert.do", method=RequestMethod.GET)
	public String userInsertPage(UserVO u,Model m) throws SQLException{
		return "user/write";
	}
	
	@RequestMapping(value="/user/update.do", method=RequestMethod.GET)
	public String userUpdatePage(UserVO u,Model m) throws SQLException{
		m.addAttribute("user",us.getOne(u));
		return "user/update";
	}
	@RequestMapping(value="/user/update.do", method=RequestMethod.POST)
	public String userUpdate(UserVO u,Model m) throws SQLException{
		us.update(u);
		return "redirect:list.do";
	}
	@RequestMapping(value="/user/insert.do", method=RequestMethod.POST)
	public String userInsert(UserVO u,Model m) throws SQLException{
		us.insert(u);
		return "redirect:list.do";
	}
}
