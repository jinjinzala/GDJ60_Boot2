package com.iu.base.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberVO implements UserDetails{
	@NotBlank	
	private String username;
	@NotBlank
	private String password;

	private String passwordCheck;

	@NotBlank
	private String name;
	@Email
	private String email;
	@Past
	private Date birth;


	private List<MemberRoleVO> roleVOs;

	   
	   @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		   List<GrantedAuthority> authorities = new ArrayList<>();
		   for(MemberRoleVO roleVO : roleVOs) {
			   authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		   }
		return authorities;
	}

//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
	   // username(id) 반환
	   //password반환 
//		return null;
//	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		// 계정의 만료 여부 
		// true 만료 안됨
		// false 만료됨 (로그인안됨_)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		// true 안잠김 
		// false 잠김 
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		//password의 만료 여부
		// true 만료안됨
		// false 만료됨 
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		// 계정의 사용 여부 
		//true 사용가능
		// false 사용불가 
		return true;
	}



	
}
