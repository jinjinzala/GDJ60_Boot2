package com.iu.base.member;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.Session;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.iu.base.email.MailManager;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailManager mailManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@GetMapping("info")
	public void info(HttpSession session) {
		
		String pw = "kkk";
		
		MemberVO memberVO =(MemberVO)memberService.loadUserByUsername("kkk");
		log.error("{}::::::::::::::::: ",memberVO.getPassword() );
		log.error("{}::::::::::::::::: ",passwordEncoder.encode(pw));
		log.error("{}::::::::::::::::: ",memberVO.getPassword().equals(passwordEncoder.encode(pw)));
		boolean check = passwordEncoder.matches(pw, memberVO.getPassword());
		log.error("{}::::::::::::::::: ",check);
// SPRING_SECURITY_CONTEXT		
		log.error("=======================Login info=============");
//		Enumeration<String> names = session.getAttributeNames();
//		while(names.hasMoreElements()) {
//			log.error("================= {} =================",names.nextElement());
//		}
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		Authentication authentication = contextImpl.getAuthentication();
		
		log.error("================= {} ================",obj);
		log.error("=================NAME : {} ================",authentication.getName());
		log.error("=================Detail : {} ================",authentication.getDetails());
		log.error("=================Principal : {} ================",authentication.getPrincipal());
	}
	
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
		
		//boolean check = memberService.passwordCheck(memberVO, bindingResult);
		
//		 if(check) {
//			  log.warn("======================검증에 실패=========================");
//			  mv.setViewName("member/join");
//			  return mv;
//		   }
		int result = memberService.setJoin(memberVO);
		mv.setViewName("redirect:../");
		return mv;
	}

	
	@GetMapping("login")
	public ModelAndView getLogin(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		mv.setViewName("./member/login");
		return mv;
	}
	
	
	@GetMapping("logout")
	public ModelAndView getLogOut(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		int result = memberService.setLogOut(memberVO);
		
		session.invalidate();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	
	@GetMapping("findPassword")
	public ModelAndView findPassword() throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO = new MemberVO();
		memberVO = memberService.findPassword(memberVO);
		System.out.println(memberVO);
		mv.setViewName("./member/findPassword");
		return mv;
	}	
	
	
	@PostMapping("findPassword")
	public ModelAndView findPassword(MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.findPassword(memberVO);
		System.out.println("=============================================================");
		System.out.println(memberVO.getEmail());
		System.out.println(memberVO.getUsername());
		String password1 = UUID.randomUUID().toString().substring(0, 10);
		String password = passwordEncoder.encode(password1);
		String email  = memberVO.getEmail();
		String username = memberVO.getUsername();
		//String password = String.format("%04d", random.nextInt(10000));
		memberService.updatePassword(password, email ,username);
		mailManager.send(email,"변경된 비밀번호를 전달해드리겠습니다",password1);
		mv.setViewName("redirect:./findPassword");
		if(memberVO !=null) {
        	mv.setViewName("redirect:/");
		}	
		return mv;
	}
	
	
	
	
	

//	@PostMapping("login")
//	public ModelAndView getLogin(MemberVO memberVO, HttpSession session,String remember,HttpServletResponse response) throws Exception{
//		ModelAndView mv = new ModelAndView();
//		memberVO = memberService.getLogin(memberVO);
//		
//		session.setAttribute("member", memberVO);
//
//		mv.setViewName("redirect:./login");
//		if(memberVO !=null) {
//        	mv.setViewName("redirect:/");
//		}	
//		return mv;
//	}
	
	
	
	

	}
		

