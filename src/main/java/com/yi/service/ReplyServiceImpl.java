package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yi.domain.Creiteria;
import com.yi.domain.ReplyVO;
import com.yi.persistence.BoardDao;
import com.yi.persistence.ReplyDAO;

@Repository
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyDAO dao;
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	@Transactional
	public void create(ReplyVO vo) throws Exception {
		dao.create(vo);
		boardDao.updateReplyCnt(1, vo.getBno());
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	@Transactional
	public void delete(int rno) throws Exception {
		int bno = dao.getBno(rno);
		dao.delete(rno);
		boardDao.updateReplyCnt(-1, bno);
		
	}

	@Override
	public List<ReplyVO> listPage(int bno, Creiteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}

	@Override
	public int totalCount(int bno) throws Exception {
		return dao.totalCount(bno);
	}
	
}
