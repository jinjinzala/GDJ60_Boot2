package com.iu.base.board;

import java.util.List;

public interface BoardDAO {
	
//		//글개수
//		public Long getTotalCount() throws Exception;
//		
//		//글리스트
//		public  List<BoardVO> getList() throws Exception;
//	
//		//글하나 
//		public BoardVO getDetail (BoardVO boardVO) throws Exception;
	
//	    //글쓰기
//		public int setBoard(BoardVO boardVO) throws Exception;
//		
//		//글작성
//		public int setUpdate(BoardVO boardVO) throws Exception;
		
		//글작성
		public int setInsert(BoardVO boardVO) throws Exception;
		
//		//글삭제
//		public int setDelete(BoardVO boardVO) throws Exception;
		
}
