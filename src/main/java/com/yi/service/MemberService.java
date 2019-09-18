package com.yi.service;

import java.util.List;

import com.yi.domain.Member;

public interface MemberService {
	public String getTime();
	public void insertMember(Member vo);
	public Member readMember(String userid);
	public List<Member> selectAll();
	public void update(Member vo);
	public void delete(Member vo);
	public Member selectMemberByIdAndPw(String userid,String userpw);
}
