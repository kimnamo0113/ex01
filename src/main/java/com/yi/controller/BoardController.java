package com.yi.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yi.domain.Board;
import com.yi.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService service;
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public void registerGET() {
		logger.info("------------registerGet");
		// board/register
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String registerPOST(Board board) throws Exception {
		logger.info("------------registerPOST");
		logger.info(board.toString());
		
		service.regist(board);
		
		
		//리다이렉트 : 브라우저에 들어갈때 /baord/listAll주소로 바로 이동하라고 처리하는 것임
		//			브라우저가 화면을 그려지기 전에 바로 http://localhost:8080/ex01/board/listAll로 이동하게됨
		return "redirect:/board/listAll";
	}
	
	//board/listAll
	@RequestMapping(value="listAll",method=RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("-----------------listAll");
		
		List<Board> list = service.listAll();
		model.addAttribute("list",list);
	}
}
