package com.iu.base.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception;
	
	public MemberVO idDuplicateCheck(MemberVO memberVO)throws Exception;
	
	public int setJoin (MemberVO memberVO) throws Exception;
	
	public int setMemberRole(Map<String, Object> map) throws Exception;
	
	public List<MemberVO> getMemberlist () throws Exception;
	
	public MemberVO setLogOut (MemberVO memberVO) throws Exception;
	
	public int setEnabled() throws Exception;
	
	public List<MemberVO> setBirth() throws Exception;
	
	public MemberVO findPassword(MemberVO memberVO) throws Exception;
	
	public int updatePassword(String password ,String email,String username) throws Exception;
	
	
}
