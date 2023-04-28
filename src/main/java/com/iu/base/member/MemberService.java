package com.iu.base.member;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.error("===============Spring Security Login=========================");
		log.error("=============== {} =========================",username);
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberDAO.getLogin(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}


	
	//패스워드가 일치하는지 검증하는 메서드
	
	public boolean passwordCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean result = true;
		//false : error가 없음 , 검증성공
		//true : error가 실패, 검증 실패
		
		// 1. annotation 검증 결과
		result = bindingResult.hasErrors();
		
//		// 2. password 일치 검증
//		if(memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
//			result = true;
//			bindingResult.rejectValue("passwordCheck","member.password");
//			
//		}
		
		//3. ID 중복 검사
		 MemberVO checkMember = memberDAO.idDuplicateCheck(memberVO);
				 	if (checkMember != null) {
				 		result = true;
				 		bindingResult.rejectValue("username", "member.id.duplicate");
				 	}

		return result; 
	}
	 	
	
	
	
	public int setJoin(MemberVO memberVO) throws Exception{
		
		//memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("num", 3);
		result = memberDAO.setMemberRole(map);

		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception{
//		MemberVO loginDTO = MemberDAO.getMemberLogin(memberVO);
//		if(loginDTO!=null &&loginDTO.getUserName().equals(memberVO.getPassword())) {
//			memberVO.setRoleDTO(loginDTO.getRoleDTO());
//			memberVO.setPw(null);
//			return memberVO;
//
//		}
		return memberDAO.getLogin(memberVO);
			
	}
	
	
	public int updatePassword(String password , String email ,String username ) throws Exception{
		return memberDAO.updatePassword(password , email ,username);
	}
	
	public  MemberVO findPassword(MemberVO memberVO) throws Exception{
		return memberDAO.findPassword(memberVO);
	}

	

	
	public MemberVO idDuplicateCheck(MemberVO memberVO)  throws Exception {
		return memberDAO.idDuplicateCheck(memberVO);
		
	}

	public int setLogOut(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
	
}
