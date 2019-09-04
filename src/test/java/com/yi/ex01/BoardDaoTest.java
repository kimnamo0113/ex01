package com.yi.ex01;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yi.domain.Board;
import com.yi.domain.Creiteria;
import com.yi.persistence.BoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoTest {
	@Autowired
	BoardDao dao;
	
//	@Test
	public void test01Create() throws Exception {
		Board board = new Board();
		board.setContent("새로운 글을 넣습니다.");
		board.setTitle("새로운 글을 넣습니다.");
		board.setWriter("user00");
		dao.create(board);
	};
	
	
	
//	@Test
	public void test02ListPage() throws Exception {
		List<Board> list = dao.listPage(1);
		for(Board board : list) {
			System.out.println(board);
		}
	}
	
	@Test
	public void test03ListCreteria() throws Exception {
		Creiteria cri = new Creiteria();
		cri.setPerPageNum(10);
		cri.setPage(2);
		List<Board> list = dao.listCreteria(cri);
		for(Board board : list) {
			System.out.println(board);
		}
	}
	
}
