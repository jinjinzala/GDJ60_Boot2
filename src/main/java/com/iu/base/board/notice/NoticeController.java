package com.iu.base.board.notice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardVO;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
//	@RequestMapping(value="list" ,method = {RequestMethod.GET ,RequestMethod.POST})
	@GetMapping("list")
	public ModelAndView getList(ModelAndView mv , Pager pager) throws Exception {
		
		
		log.info("search : {}",pager.getSearch());
		log.info("kind : {}",pager.getKind());
		
		
		System.out.println(pager.getSearch());
		System.out.println(pager.getKind());
		List<BoardVO> ar = noticeService.getList(pager);
		mv.addObject("list",ar);
		mv.setViewName("board/list");
		return mv;
		
	}
	@GetMapping("detail")
	public ModelAndView getDetail(NoticeVO noticeVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		BoardVO boardVO = noticeService.getDetail(noticeVO);
		
		mv.addObject("boardVO",boardVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFileVO boardFileVO) throws Exception {
		boardFileVO = noticeService.getFileDetail(boardFileVO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("BoardFileVO",boardFileVO);
		mv.setViewName("fileManager");
		return mv;
	}
	
	   @GetMapping("add")
	   public ModelAndView setInsert( @ModelAttribute BoardVO boardVO)throws Exception{
	      ModelAndView mv = new ModelAndView();
	      mv.setViewName("board/add");
	      mv.addObject(new NoticeVO()); //속성명은 클래스명의 첫글자를 소문자로 바꾼거 
	      return mv;
	   }
	   
	   //매개변수로 값을 받아올 boardvo 선언
	   @PostMapping("add" )
	   public ModelAndView setInsert(@Valid BoardVO boardVO, BindingResult bindingResult,MultipartFile[] boardFiles)throws Exception{
		   
		   log.error("========={}==========",boardVO.getSubVO().getSubName());
		   
		   for(String n : boardVO.getNames()) {
			   log.error("========={}==========",n);
		   }
		   
		   for(BoardFileVO boardFileVO:boardVO.getBoardFileVOs()) {
			   
			   log.error("========={}==========",boardFileVO.getFileName());
		   }
		   
		   ModelAndView mv = new ModelAndView();
		   if(bindingResult.hasErrors()) {
			  log.warn("======================검증에 실패=========================");
			  mv.setViewName("board/add");
			  return mv;
		   }
		   
		   for (MultipartFile multipartFile2:boardFiles) {
			   log.info("OrignalName : {} size{}",multipartFile2.getOriginalFilename(),multipartFile2.getSize());
		   }
		 // int result = noticeService.setInsert(boardVO,boardFiles); 
	       mv = new ModelAndView();
	      mv.setViewName("redirect:./list");
	
	      return mv;
	   }
	
}
