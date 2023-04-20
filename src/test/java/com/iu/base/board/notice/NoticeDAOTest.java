package com.iu.base.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.iu.base.board.BoardVO;

@SpringBootTest
@Rollback(true)
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	//@Test
	
	void setInsertTest() throws Exception{
		for(int i=0; i<120; i++) {
		BoardVO boardVO = new BoardVO();
		boardVO.setWriter("희진");
		boardVO.setTitle("Title test"+i);
		boardVO.setContents("contents Test"+i);
		
		int result = noticeDAO.setInsert(boardVO);
		
		if(i%10==0) {
			Thread.sleep(500);
		}
		
	}
	}
//	
//	@Test
//	void setDeleteTest() throws Exception{
//		BoardVO boardVO = new BoardVO();
//		boardVO.setNum(1L);
//		int result = noticeDAO.setDelete(boardVO);
//		assertEquals(1, result);
//		
//	}
//	
//	@Test
//	void setUpdateTest() throws Exception{
//		BoardVO boardVO = new BoardVO();
//		boardVO.setNum(2L);
//		boardVO.setTitle("수정했도용");
//		boardVO.setWriter("희진수정");
//		boardVO.setContents("수정내용");
//		int result = noticeDAO.setUpdate(boardVO);
//		assertEquals(1, result);
//		
//	}
	
	

}
