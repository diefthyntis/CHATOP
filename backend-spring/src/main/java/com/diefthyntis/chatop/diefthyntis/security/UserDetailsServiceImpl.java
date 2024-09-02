package com.diefthyntis.chatop.diefthyntis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diefthyntis.chatop.diefthyntis.model.User;
import com.diefthyntis.chatop.diefthyntis.repository.UserRepository;

/*
 * In the code, we get full custom User object using UserRepository, 
 * then we build a UserDetails object using static build() method.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(emailAddress)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + emailAddress));

		/*
		 * UserDetailsImpl.build est un méthode statique donc il n'y a pas besoin
		 * d'instancier la classe pour appeler la méthode Dans l'environnement JAVA,tout
		 * ce qui est statique est écrit en italique
		 */
		return UserDetailsImpl.build(user);
	}

}