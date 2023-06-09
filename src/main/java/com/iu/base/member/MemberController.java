package com.iu.base.member;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.iu.base.board.BoardVO;
import com.iu.base.board.notice.NoticeVO;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	
	@GetMapping("admin")
	public void getAdmin(MemberVO memberVO)throws Exception {
		

	}
	
	@GetMapping("mypage")
	public void getMyPage(MemberVO memberVO)throws Exception {
		

	}
	
	
	@GetMapping("idDuplicateCheck")
	@ResponseBody
	public boolean idDuplicateCheck(MemberVO memberVO) throws Exception{
		
		log.error("================id 중복 체크====================");
		boolean cheack = false;
		memberVO = memberService.idDuplicateCheck(memberVO);
		if (memberVO == null) {
			cheack = true;	
		}
		return cheack;
	}
	
	@GetMapping("join")
	public ModelAndView setJoin(@ModelAttribute MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/join");
		
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(@Valid MemberVO memberVO,BindingResult bindingResult) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean check = memberService.passwordCheck(memberVO, bindingResult);
		
		 if(check) {
			  log.warn("======================검증에 실패=========================");
			  mv.setViewName("member/join");
			  return mv;
		   }
		int result = memberService.setJoin(memberVO);
		mv.setViewName("redirect:../");
		return mv;
	}

	
	@GetMapping("login")
	public ModelAndView getLogin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("./member/login");
		return mv;
	}
	
	@GetMapping("logout")
	public ModelAndView getLogOut(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	
	@PostMapping("login")
	public ModelAndView getLogin(MemberVO memberVO, HttpSession session,String remember,HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.getLogin(memberVO);
		
		session.setAttribute("member", memberVO);

		mv.setViewName("redirect:./login");
		if(memberVO !=null) {
        	mv.setViewName("redirect:/");
		}	
		return mv;
	}
	
		
}
