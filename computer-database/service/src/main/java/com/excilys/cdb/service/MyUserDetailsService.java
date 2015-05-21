package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.UserDAO;
import com.excilys.cdb.model.UserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		com.excilys.cdb.model.User user = userDAO.findByUserName(username);
		List<UserRole> userRole = new ArrayList<UserRole>(user.getUserRole());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		userRole.stream().map(
				x -> authorities.add(new SimpleGrantedAuthority(x
						.getAuthority())));

		return buildUserForAuthentication(user, authorities);

	}

	// Converts ccom.excilys.cdb.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.excilys.cdb.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

}
