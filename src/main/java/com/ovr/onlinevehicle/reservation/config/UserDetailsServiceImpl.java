//package com.ovr.onlinevehicle.reservation.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.ovr.onlinevehicle.reservation.model.User;
//import com.ovr.onlinevehicle.reservation.repo.UserRepository;
//
//public class UserDetailsServiceImpl implements UserDetailsService{
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		//fetching user from database
//		User user = userRepository.getUserByUserName(username);
//		if(user == null) {
//			throw new UsernameNotFoundException("Could not find user.");
//		}
//		CustomUserDetails customUserDetails = new CustomUserDetails(user);
//		return customUserDetails;
//	}
//
//}
