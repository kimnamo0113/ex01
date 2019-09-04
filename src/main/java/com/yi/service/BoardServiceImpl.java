package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.Board;
import com.yi.domain.Creiteria;
import com.yi.domain.SearchCriteria;
import com.yi.persistence.BoardDao;

@Repository
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDao dao;

	@Override
	public void regist(Board board) throws Exception {
		dao.create(board);
	}

	@Override
	public List<Board> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public Board read(int bno,boolean viewCnt) throws Exception {

		Board board = dao.read(bno);
		if(viewCnt==true) {
			board.setViewcnt(board.getViewcnt()+1);
			dao.viewCnt(board);
		}
		return board;
	}

	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public void update(Board board) throws Exception {
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

	
}
