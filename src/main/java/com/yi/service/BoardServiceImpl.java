package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yi.domain.Board;
import com.yi.domain.Creiteria;
import com.yi.domain.SearchCriteria;
import com.yi.persistence.BoardDao;

@Repository
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDao dao;

	@Transactional
	@Override
	public void regist(Board board) throws Exception {
		dao.create(board);
		for(String fullName : board.getFiles()) {
			dao.addAttach(fullName);
		}
	}
	
	@Override
	public List<Board> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Board read(int bno,boolean viewCnt) throws Exception {

		Board board = dao.read(bno);
		List<String> files = dao.getAttach(bno);
		board.setFiles(files);
		
		if(viewCnt==true) {
			board.setViewcnt(board.getViewcnt()+1);
			dao.viewCnt(board);
		}
		
		return board;
	}
	@Transactional
	@Override
	public void delete(int bno) throws Exception {
		dao.deleteAttach(bno);
		dao.delete(bno);
	}
	@Transactional
	@Override
	public void update(Board board) throws Exception {
		if(board.getFiles()!=null) {
			for(String file : board.getFiles()) {
				dao.deleteAttachByFullName(board.getBno(),file);
			}
		}
		dao.update(board);
	}
	

	@Override
	public List<Board> listCreteria(Creiteria cri) throws Exception {
		return dao.listCreteria(cri);
	}

	@Override
	public int listCountCriteria() throws Exception {
		return dao.countPaging();
	}

	@Override
	public List<Board> listSearch(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}

	@Override
	public void insertAttach(Board board) throws Exception {
		for(String fullName : board.getFiles()) {
			dao.insertAttach(fullName,board.getBno());
		}
	}

	
}
