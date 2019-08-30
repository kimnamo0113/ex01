package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.Board;
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
	
}
