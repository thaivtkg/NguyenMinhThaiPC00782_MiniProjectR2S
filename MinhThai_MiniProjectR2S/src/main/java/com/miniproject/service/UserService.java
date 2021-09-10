package com.miniproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.miniproject.dao.AccountDAO;
import com.miniproject.dao.AuthorityDAO;
import com.miniproject.entity.Account;
import com.miniproject.entity.Role;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	AccountDAO accDAO;
	@Autowired
	AuthorityDAO authDao;
	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
				Account acc = accDAO.findById(username).get();
				String password = acc.getPassword();
				String[] roles=authDao.findRoleByUsername(username).stream()
						.map(role->role.getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(pe.encode(password)).roles(roles).build();				
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found");
		}
	}
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String email;
		if(oauth2.getPrincipal().getAttribute("email") == null){
			email = oauth2.getPrincipal().getAttribute("name");
		}
		else {
			email = oauth2.getPrincipal().getAttribute("email");
		}
		String password=Long.toHexString(System.currentTimeMillis());
		UserDetails user=User.withUsername(email).password(pe.encode(password)).roles("GUEST").build();		
		Authentication auth=new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}