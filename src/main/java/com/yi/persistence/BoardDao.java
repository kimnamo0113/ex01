package com.yi.persistence;

import java.util.List;

import com.yi.domain.Board;


public interface BoardDao {
	public void create(Board board) throws Exception;
	public Board read(int bno) throws Exception;
	public List<Board> listAll() throws Exception;
	
}
