package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.UserDAO;
import com.excilys.cdb.model.UserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserDAO userDAO;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		com.excilys.cdb.model.User user = userDAO.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user
				.getUserRole());
		LOGGER.debug("User {} have {} ", user.getUsername(), authorities);
		return buildUserForAuthentication(user, authorities);
	}

	// Converts com.excilys.cdb.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.excilys.cdb.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getAuthority()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);
		return Result;
	}

}