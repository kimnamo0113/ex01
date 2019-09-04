package com.yi.service;

import java.util.List;

import com.yi.domain.Board;
import com.yi.domain.Creiteria;
import com.yi.domain.SearchCriteria;

public interface BoardService {
	public void regist(Board board) throws Exception;
	public List<Board> listAll() throws Exception;
	public Board read(int bno,boolean viewCnt) throws Exception;
	
	public void delete(int bno) throws Exception;
	public void update(Board board) throws Exception;
	
	public List<Board> listCreteria(Creiteria cri) throws Exception;
	public int listCountCriteria() throws Exception;
	
	public List<Board> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
