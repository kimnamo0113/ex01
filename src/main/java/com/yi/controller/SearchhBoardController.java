package com.yi.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yi.domain.Board;
import com.yi.domain.PageMaker;
import com.yi.domain.SearchCriteria;
import com.yi.service.BoardService;
import com.yi.util.UploadFileUtils;

@Controller
@RequestMapping("/sboard/*")
public class SearchhBoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri,Model model,RedirectAttributes ratt) throws Exception {
		logger.info("---------- list");
		List<Board> list = service.listSearch(cri);
		model.addAttribute("list",list);
		
		ratt.addAttribute("page",cri.getPage());
		ratt.addAttribute("searchType",cri.getSearchType());
		ratt.addAttribute("keyword",cri.getKeyword());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}//return : sboard/list.jsp
	
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public void registerGET(@ModelAttribute("cri") SearchCriteria cri) {
		logger.info("------------registerGet");
		// board/register
		
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String registerPOST(Board board, List<MultipartFile> imgFiles, SearchCriteria cri,RedirectAttributes ratt) throws Exception {
		logger.info("------------registerPOST");
		logger.info(board.toString());
		
		ArrayList<String> list = new ArrayList<>();
		
		for(MultipartFile file : imgFiles) {
			logger.info("file name : "+file.getOriginalFilename());
			logger.info("file size : "+file.getSize());
			String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			list.add(savedName);
		}
		
		board.setFiles(list);
		
		service.regist(board);
		
		ratt.addAttribute("page",cri.getPage());
		ratt.addAttribute("searchType",cri.getSearchType());
		ratt.addAttribute("keyword",cri.getKeyword());
		
		
		//리다이렉트 : 브라우저에 들어갈때 /baord/listAll주소로 바로 이동하라고 처리하는 것임
		//			브라우저가 화면을 그려지기 전에 바로 http://localhost:8080/ex01/board/listAll로 이동하게됨
		return "redirect:/sboard/list";
	}
	
	
	
	
	@RequestMapping(value="readPage",method=RequestMethod.GET)
	public void readPage(int bno, SearchCriteria cri, Model model) throws Exception {
		logger.info("--------------- readPage, bno="+bno);
		Board board = service.read(bno,true);
		model.addAttribute("board",board);
		model.addAttribute("cri", cri);
		
	}
	@RequestMapping(value="removePage",method=RequestMethod.POST)
	public String deletePage(int bno,SearchCriteria cri,RedirectAttributes ratt) throws Exception {
		logger.info("--------------- remove, bno="+bno);
		service.delete(bno);
		System.out.println(cri);
		System.out.println(cri.getSearchType());
		
		ratt.addAttribute("page",cri.getPage());
		ratt.addAttribute("searchType",cri.getSearchType());
		ratt.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="modPage",method=RequestMethod.GET)
	public void modPageGET(int bno,SearchCriteria cri, Model model) throws Exception {
		logger.info("--------------- mod, bno="+bno);
		Board board=service.read(bno,false);
		model.addAttribute(board);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value="modPage",method=RequestMethod.POST)
	public String modPagePOST(Board board,SearchCriteria cri,RedirectAttributes ratt) throws Exception {
		logger.info("--------------- mod, bno="+board);
		
		service.update(board);
		
		ratt.addAttribute("page",cri.getPage());
		ratt.addAttribute("searchType",cri.getSearchType());
		ratt.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="/displayFile",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> diplayFile(String filename){
		logger.info("----------displayFile,filename="+filename);
		
		String formatName = filename.substring(filename.lastIndexOf(".")+1);//확장자만 뽑아냄
		MediaType mType = null;
		ResponseEntity<byte[]> entity;
		
		if(formatName.equalsIgnoreCase("jpg")) {
			mType=MediaType.IMAGE_JPEG;
		}
		else if(formatName.equalsIgnoreCase("gif")) {
			mType=MediaType.IMAGE_GIF;
		}
		else if(formatName.equalsIgnoreCase("png")) {
			mType=MediaType.IMAGE_PNG;
		}
		InputStream in =null;
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath+"/"+filename);
			headers.setContentType(mType);
			
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in),
					headers,
					HttpStatus.CREATED
					);
		}catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			try {
				if(in !=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return entity;
	}
}
