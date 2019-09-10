package com.yi.persistence;

import java.util.List;

import com.yi.domain.Board;
import com.yi.domain.Creiteria;
import com.yi.domain.SearchCriteria;


public interface BoardDao {
	public void create(Board board) throws Exception;
	public Board read(int bno) throws Exception;
	public List<Board> listAll() throws Exception;
	public List<Board> listPage(int page) throws Exception;
	public List<Board> listCreteria(Creiteria cri) throws Exception;
	public void delete(int bno) throws Exception;
	public void update(Board board) throws Exception;
	public void viewCnt(Board board) throws Exception;
	public int countPaging() throws Exception;
	
	public List<Board> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	public void updateReplyCnt(int amount, int bno) throws Exception;
	
}
