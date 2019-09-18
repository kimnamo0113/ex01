package com.yi.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.yi.domain.Board;
import com.yi.domain.Creiteria;
import com.yi.domain.SearchCriteria;

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

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete(namespace+".delete",bno);
	}

	@Override
	public void update(Board board) throws Exception {
		sqlSession.update(namespace+".update",board);
	}

	@Override
	public void viewCnt(Board board) throws Exception {
		sqlSession.update(namespace+".viewCnt",board);
	}

	@Override
	public List<Board> listPage(int page) throws Exception {
		page=(page-1)*10;
		return sqlSession.selectList(namespace+".listPage",page);
	}

	@Override
	public List<Board> listCreteria(Creiteria cri) throws Exception {
		return sqlSession.selectList(namespace+".listCreteria",cri);
	}

	@Override
	public int countPaging() throws Exception {
		return sqlSession.selectOne(namespace+".countPaging");
	}

	@Override
	public List<Board> listSearch(SearchCriteria cri) throws Exception {
		return sqlSession.selectList(namespace+".listSearch",cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return sqlSession.selectOne(namespace+".listSearchCount",cri);
	}

	@Override
	public void updateReplyCnt(int amount, int bno) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("amount", amount);
		map.put("bno", bno);
		sqlSession.update(namespace+".updateReplyCnt",map);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		sqlSession.insert(namespace+".addAttach",fullName);
	}
	@Override
	public void insertAttach(String fullName ,int bno) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("fullName", fullName);
		map.put("bno", bno);
		System.out.println("DAOIMPL");
		System.out.println(fullName+"///"+bno);
		sqlSession.insert(namespace+".insertAttach",map);
	}

	@Override
	public List<String> getAttach(int bno) throws Exception {
		return sqlSession.selectList(namespace+".getAttach",bno);
	}

	@Override
	public void deleteAttach(int bno) throws Exception {
		sqlSession.delete(namespace+".deleteAttach",bno);
	}

	@Override
	public void deleteAttachByFullName(int bno, String fullName) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("fullName", fullName);
		sqlSession.delete(namespace+".deleteAttachByFullName",map);
	}

	
	
}
