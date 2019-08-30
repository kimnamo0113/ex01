package com.yi.ex01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yi.domain.Board;
import com.yi.persistence.BoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoTest {
	@Autowired
	BoardDao dao;
	
	@Test
	public void test01Create() throws Exception {
		Board board = new Board();
		board.setContent("새로운 글을 넣습니다.");
		board.setTitle("새로운 글을 넣습니다.");
		board.setWriter("user00");
		dao.create(board);
	};
}
