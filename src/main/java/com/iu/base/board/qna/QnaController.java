package com.iu.base.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardVO;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	@GetMapping("list")
	public ModelAndView getList(ModelAndView mv , Pager pager) throws Exception {
		
		log.info("search : {}",pager.getSearch());
		log.info("kind : {}",pager.getKind());	
		
		System.out.println(pager.getSearch());
		System.out.println(pager.getKind());
		List<BoardVO> ar = qnaService.getList(pager);
		mv.addObject("list",ar);
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		BoardVO boardVO = qnaService.getDetail(qnaVO);
		
		mv.addObject("boardVO",boardVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFileVO boardFileVO) throws Exception {
		boardFileVO = qnaService.getFileDetail(boardFileVO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("BoardFileVO",boardFileVO);
		mv.setViewName("fileManager");
		return mv;
	}
	
	   @GetMapping("add")
	   public ModelAndView setInsert()throws Exception{
	      ModelAndView mv = new ModelAndView();
	      mv.setViewName("board/add");
	      return mv;
	   }
	   
	   //매개변수로 값을 받아올 boardvo 선언
	   @PostMapping("add" )
	   public ModelAndView setInsert(QnaVO qnaVO , MultipartFile[] boardFiles)throws Exception{
		   for (MultipartFile multipartFile2:boardFiles) {
			   log.info("OrignalName : {} size{}",multipartFile2.getOriginalFilename(),multipartFile2.getSize());
		   }
		  int result = qnaService.setInsert(qnaVO,boardFiles); 
	      ModelAndView mv = new ModelAndView();
	      mv.setViewName("redirect:./list");
	
	      return mv;
	   }
	
	

}
