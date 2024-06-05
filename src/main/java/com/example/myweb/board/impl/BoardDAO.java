package com.example.myweb.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import com.example.myweb.board.BoardVO;
import com.example.myweb.common.JDBCUtil;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
// JdbcTemplate 적용
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Repository("boardDAO")
public class BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String SQL_GET = "select * from board where seq=?";
	final String SQL_LIST = "select * from board order by seq desc";
	final String SQL_INSERT = "insert into board(seq, title, writer, content) " +
						"values((select nvl(max(seq),0)+1 from board),?,?,?)";
	final String SQL_UPDATE = "update board set title=?, content=? where seq=?";
	final String SQL_DELETE = "delete from board where seq=?";
	
	// 글등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> insertBoard() - BoardDAO 기능 처리");
		jdbcTemplate.update(SQL_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	
	// 글수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> updateBoard() - BoardDAO 기능 처리");
		jdbcTemplate.update(SQL_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	// 글삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> deleteBoard() - BoardDAO 기능 처리");
		jdbcTemplate.update(SQL_DELETE, vo.getSeq());				
	}
	
	class BoardMapper implements RowMapper<BoardVO> {
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			// DB테이블의 필드와 자바 객체 필드를 매핑한다.
			// 이 위치가 while문에 사용되었던 rs.next()를 실행 한것과 같다.
			BoardVO board = new BoardVO();
			board.setSeq(rs.getInt(1));
			board.setTitle(rs.getString(2));
			board.setWriter(rs.getString(3));
			board.setContent(rs.getString(4));
			board.setRegdate(rs.getString(5));
			board.setCnt(rs.getInt(6));
			return board;
		}
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> getBoard() - BoardDAO 기능 처리");
		BoardMapper rowMapper = new BoardMapper();
		Object[] objArr = new Object[]{vo.getSeq()};
		BoardVO board = jdbcTemplate.queryForObject(SQL_GET, objArr, rowMapper);
		return board;
	}
	
	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> getBoardList() - BoardDAO 기능 처리");
		BoardMapper rowMapper = new BoardMapper();
		List<BoardVO> boardList = jdbcTemplate.query(SQL_LIST, rowMapper);
		return boardList;
	}
}
