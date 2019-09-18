package com.yi.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	private static final String namespace = "com.yi.mapper.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace+".getTime");
	}

	@Override
	public void insertMember(Member member) {
		sqlSession.insert(namespace+".insertMember",member);
	}

	@Override
	public Member selectMember(String userid) {
		return sqlSession.selectOne(namespace+".selectMember",userid);
	}

	@Override
	public List<Member> selectAll() {
		return sqlSession.selectList(namespace+".selectAll");
	}

	@Override
	public void update(Member member) {
		sqlSession.update(namespace+".update",member);
	}

	@Override
	public void delete(String id) {
		sqlSession.delete(namespace+".delete",id);
	}

	@Override
	public Member selectMemberByIdAndPw(String userid,String userpw) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("userpw", userpw);
		return sqlSession.selectOne(namespace+".selectMemberByIdAndPw",map);
	}

}
