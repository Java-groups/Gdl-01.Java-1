package com.softserve.security.user;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.softserve.model.Role;
import com.softserve.model.User;
import com.softserve.repository.IUserRepository;


@Service
public class UserServices implements UserDetailsService {
    
    @Autowired
    private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final Optional<User>optionalUseruser= userRepository.findByEmail(userName);
        if(optionalUseruser.isPresent()){

            User user= optionalUseruser.get();
            return new org.springframework.security.core.userdetails.User(user.getFirstName(),user.getUserPassword(),mapAutorities(user.getRoles()));
        }else
            throw  new UsernameNotFoundException("User or password wrong.");
	}
	
	private Collection<? extends GrantedAuthority> mapAutorities(Collection<Role> roles){
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
