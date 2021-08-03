/**
 * 
 */
package com.project.buku.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.buku.Entity.MUser;
import com.project.buku.Repository.MUserRepository;

/**
 * @author Fikri
 *
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MUserRepository mUserRepository;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username: {}",username);
		Optional<MUser>  user = mUserRepository.findByUsername(username);
		log.info("user: {} pass: {} ", user.get().getUsername(), user.get().getPassword());
	
		user.
			orElseThrow(() -> new UsernameNotFoundException("username not found"));
		
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
		log.info("auth: {}",authorities.toArray().toString());
		return new User (user.get().getUsername(), user.get().getPassword(), authorities);
	}

}
