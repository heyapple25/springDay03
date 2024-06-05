package com.example.myweb.board.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import com.example.myweb.board.BoardService;
import com.example.myweb.board.BoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(Locale locale, Model model) throws SQLException {
		
		//System.out.println(ds.getConnection());
		//Connection conn = jdbcUtil.getConnection();
		//System.out.println(conn);
		
		List<BoardVO> boardList = boardService.getList(null);
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	@RequestMapping(value="/boardDetail", method=RequestMethod.GET)
	public String boardDetail(@RequestParam("seq") int seq, Model model) throws SQLException {
		//System.out.println("boardDetail >>>> seq : " + seq);
		
		BoardVO vo = new BoardVO();
		vo.setSeq(seq);
		BoardVO board = boardService.getOne(vo);
		model.addAttribute("board", board);
		
		return "board/detail";
	}
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.GET)
	public String boardUpdate(@RequestParam("seq") int seq, Model model) {
		
		BoardVO vo = new BoardVO();
		vo.setSeq(seq);
		BoardVO board = boardService.getOne(vo);
		model.addAttribute("board", board);
		
		return "board/update";
	}
	
	/*@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate2(@RequestParam Map<String, String> params, Model model) {
		
		Iterator it = params.entrySet().iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		return "redirect:boardList";
	}*/
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate2(BoardVO vo, Model model) {
		
		//System.out.println(vo);
		boardService.update(vo);
		
		return "redirect:boardList";
	}
	
	// 설정페이지에서 view-controller 설정으로 대체
	/*@RequestMapping(value="/boardWrite", method=RequestMethod.GET)
	public String boardWrite(BoardVO vo) {
		return "board_write";
	}*/
	
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	public String boardWriteOk(BoardVO vo) {
		
		//System.out.println("vo ===> " + vo);
		
		boardService.insert(vo);
		
		return "redirect:boardList";
	}
	
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public String boardDelete(BoardVO vo) {
		
		boardService.delete(vo);
		
		return "redirect:boardList";
	}
}