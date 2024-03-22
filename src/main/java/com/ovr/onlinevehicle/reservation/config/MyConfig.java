//package com.ovr.onlinevehicle.reservation.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatchers;
//
//@Configuration
//@EnableWebSecurity
//public class MyConfig{
//	
//	@Bean
//	public UserDetailsService getUserDetailService() {
//		return new UserDetailsServiceImpl();
//	}
//	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		
//		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		
//		return daoAuthenticationProvider;
//	}
//	
//	//configure method
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.authorizeHttpRequests((authorizeHttpRequests) -> 
//				authorizeHttpRequests
//				.requestMatchers("/api/user/**").hasRole("USER")
//				.requestMatchers("/api/owner/**").hasRole("OWNER")
//				.requestMatchers("/api/admin/**").hasRole("ADMIN")
//				.requestMatchers("/**").permitAll()
//				
//				);
//		
////		http.authorizeHttpRequests().requestMatchers("/api/user/**").hasRole("USER")
////		.requestMatchers("/api/owner/**").hasRole("OWNER")
////		.requestMatchers("/api/admin/**").hasRole("ADMIN")
////		.requestMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
//		
//		return http.build();
//	}
//
//}
