package com.iu.base.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("idDuplicateCheck")
	@ResponseBody
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception{
		
		memberVO.setEmail("test@email.com");
		log.error("================id 중복 체크====================");
		List<MemberVO> ar = new ArrayList<>();
		ar.add(memberVO);
		
		memberVO = new MemberVO();
		memberVO.setUserName("xppxpx");
		memberVO.setEmail("dsdsadsad@andsdnms.com");
//		= memberVO.getName()
//		mv.setViewName("member/join");
		return memberVO;
	}
	
	@GetMapping("join")
	public ModelAndView setJoin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		
		
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
