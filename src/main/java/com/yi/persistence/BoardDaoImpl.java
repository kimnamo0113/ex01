package com.yi.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.yi.domain.Board;

@Repository
public class BoardDaoImpl implements BoardDao{
	private static final String namespace = "com.yi.mapper.BoardMapper";
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void create(Board board) throws Exception {
		sqlSession.insert(namespace+".create", board);
	}

	@Override
	public Board read(int bno) throws Exception {
		return sqlSession.selectOne(namespace+".read", bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		return sqlSession.selectList(namespace+".listAll");
	}
	
}
