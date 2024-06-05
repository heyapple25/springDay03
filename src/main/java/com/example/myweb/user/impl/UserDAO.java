package com.example.myweb.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import com.example.myweb.user.UserVO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
// JdbcTemplate 적용
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


@Repository("userDAO")
public class UserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String SQL_GET = "select * from users where id=?";
	final String SQL_LIST = "select * from users";
	final String SQL_INSERT = "insert into users(id, password, name, role) " +
						"values(?,?,?,?)";
	final String SQL_UPDATE = "update users set name=?, password=?, role=? where id=?";
	final String SQL_DELETE = "delete from users where id=?";
	
	// 글등록
	public void insertUser(UserVO vo) {
		System.out.println("===> insertUser() - UserDAO 기능 처리");
		jdbcTemplate.update(SQL_INSERT,vo.getId(),vo.getPassword(),vo.getName(),vo.getRole());
	}
	
	// 글수정
	public void updateUser(UserVO vo) {
		System.out.println("===> updateUser() - UserDAO 기능 처리");
		jdbcTemplate.update(SQL_UPDATE,vo.getName(),vo.getPassword(),vo.getRole(),vo.getId());
	}
	
	// 글삭제
	public void deleteUser(UserVO vo) {
		System.out.println("===> deleteUser() - UserDAO 기능 처리");
		jdbcTemplate.update(SQL_DELETE, vo.getId());			
	}

	// 글 상세 조회
	public UserVO getUser(UserVO vo) {
		System.out.println("===> getBoard() - BoardDAO 기능 처리");
		Object[] objArr = new Object[]{vo.getId()};
		UserVO user= jdbcTemplate.queryForObject(SQL_GET, objArr, new BeanPropertyRowMapper<>(UserVO.class));
		return user;
	}
	
	class userMapper implements RowMapper<UserVO>{
		@Override
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new UserVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
		}
	}
	
	// 글 목록 조회
	public List<UserVO> getUserList() {
		System.out.println("===> getUserList() - USERDAO 기능 처리");
		List<UserVO> userList = jdbcTemplate.query(SQL_LIST, new BeanPropertyRowMapper<>(UserVO.class));
		return userList;
	}
}

