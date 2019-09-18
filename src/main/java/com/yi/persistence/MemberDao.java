package com.yi.persistence;

import java.util.List;

import com.yi.domain.Member;

public interface MemberDao {
	public String getTime();
	public void insertMember(Member member);
	public Member selectMember(String userid);
	public List<Member> selectAll();
	public void update(Member member);
	public void delete(String id);
	public Member selectMemberByIdAndPw(String userid,String userpw);
}
