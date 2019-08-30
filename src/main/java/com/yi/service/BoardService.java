package com.yi.service;

import java.util.List;

import com.yi.domain.Board;

public interface BoardService {
	public void regist(Board board) throws Exception;
	public List<Board> listAll() throws Exception;
}
