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
import com.yi.domain.Creiteria;
import com.yi.domain.PageMaker;
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
	
	@RequestMapping(value="read",method=RequestMethod.GET)
	public void read(int bno, Model model) throws Exception {
		logger.info("--------------- read, bno="+bno);
		Board board = service.read(bno,true);
		model.addAttribute("board",board);
	}
	
	@RequestMapping(value="remove",method=RequestMethod.POST)
	public String delete(int bno) throws Exception {
		logger.info("--------------- remove, bno="+bno);
		service.delete(bno);
		
		return "redirect:/board/listAll";
	}
	@RequestMapping(value="mod",method=RequestMethod.GET)
	public void modGET(int bno, Model model) throws Exception {
		logger.info("--------------- mod, bno="+bno);
		Board board=service.read(bno,false);
		model.addAttribute(board);
	}
	
	@RequestMapping(value="mod",method=RequestMethod.POST)
	public String modPOST(Board board) throws Exception {
		logger.info("--------------- mod, bno="+board);
		
		service.update(board);
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="listPage",method=RequestMethod.GET)
	public void listPage(Model model,Creiteria cri) throws Exception {
		logger.info("---------- listPage");
		List<Board> list = service.listCreteria(cri);
		model.addAttribute("list",list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria());
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="readPage",method=RequestMethod.GET)
	public void readPage(int bno, Creiteria cri, Model model) throws Exception {
		logger.info("--------------- readPage, bno="+bno);
		Board board = service.read(bno,true);
		model.addAttribute("board",board);
		model.addAttribute("cri", cri);
	}
	@RequestMapping(value="removePage",method=RequestMethod.POST)
	public String deletePage(int bno,int page) throws Exception {
		logger.info("--------------- remove, bno="+bno);
		service.delete(bno);
		
		return "redirect:/board/listPage?page="+page;
	}
	
	@RequestMapping(value="modPage",method=RequestMethod.GET)
	public void modPageGET(int bno,Creiteria cri, Model model) throws Exception {
		logger.info("--------------- mod, bno="+bno);
		Board board=service.read(bno,false);
		model.addAttribute(board);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value="modPage",method=RequestMethod.POST)
	public String modPagePOST(Board board,int page) throws Exception {
		logger.info("--------------- mod, bno="+board);
		
		service.update(board);
		
		return "redirect:/board/listPage?page="+page;
	}
	
}
