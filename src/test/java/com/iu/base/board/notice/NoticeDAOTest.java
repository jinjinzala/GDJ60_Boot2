package com.iu.base.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.base.board.BoardVO;

@SpringBootTest
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void setInsertTest() throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setWriter("희진");
		boardVO.setTitle("ㅌㅌ");
		boardVO.setContents("ㅇㅇㅇㅇ");
		
		int result = noticeDAO.setInsert(boardVO);
		
		assertEquals(1, result);
		
	}
	
	

}
