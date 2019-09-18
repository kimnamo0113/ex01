package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yi.domain.Member;
import com.yi.persistence.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao dao;
	
	@Override
	public String getTime() {
		
		return dao.getTime();
	}

	@Override
	public void insertMember(Member vo) {
		dao.insertMember(vo);

	}

	@Override
	public Member readMember(String userid) {
		
		return dao.selectMember(userid);
	}

	@Override
	public List<Member> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public void update(Member vo) {
		dao.update(vo);

	}

	@Override
	public void delete(Member vo) {
		dao.delete(vo.getUserid());

	}

	@Override
	public Member selectMemberByIdAndPw(String userid, String userpw) {
		return dao.selectMemberByIdAndPw(userid, userpw);
	}

}
