package com.iu.base.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public int setJoin(MemberVO memberVO) throws Exception{
		memberVO.setEnabled(true);
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("userName", memberVO.getUserName());
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
	
	public MemberVO idDuplicateCheck(MemberVO memberVO)  throws Exception {
		return memberDAO.idDuplicateCheck(memberVO);
	}
	
	
	
}
