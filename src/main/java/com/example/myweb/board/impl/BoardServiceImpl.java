package com.example.myweb.board.impl;

import java.util.List;

import com.example.myweb.board.BoardService;
import com.example.myweb.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDao;
	
	//@Autowired
	//@Qualifier("logAdvice")
	//@Inject
	//private LogAdvice logAdvice;

	@Override
	public BoardVO getOne(BoardVO vo) {
		//logAdvice.printLog();
		return boardDao.getBoard(vo);
	}

	@Override
	public List<BoardVO> getList(BoardVO vo) {
		System.out.println("getList() - BoardServiceImpl 실행");
		//logAdvice.printLog();
		return boardDao.getBoardList(vo);
	}

	@Override
	public void insert(BoardVO vo) {
		//logAdvice.printLog();
		boardDao.insertBoard(vo);
	}

	@Override
	public void update(BoardVO vo) {
		//logAdvice.printLog();
		boardDao.updateBoard(vo);
	}

	@Override
	public void delete(BoardVO vo) {
		//logAdvice.printLog();
		boardDao.deleteBoard(vo);
	}

}