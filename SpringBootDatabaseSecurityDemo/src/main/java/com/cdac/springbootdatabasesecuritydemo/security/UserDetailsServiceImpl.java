package com.cdac.springbootdatabasesecuritydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.cdac.springbootdatabasesecuritydemo.pojo.User;
import com.cdac.springbootdatabasesecuritydemo.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		User ref = repository.getUserByUserName(username);
		
		if(ref != null) {
			MyUserDetails userDetails = new MyUserDetails(ref);
			return userDetails;
		}
		else {
			throw new UsernameNotFoundException("no such user exixts");
		}
	}
}






