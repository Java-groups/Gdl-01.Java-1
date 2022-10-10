package com.softserve.security.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.softserve.model.Role;
import com.softserve.model.User;
import com.softserve.repository.IUserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserServices implements UserDetailsService {

    
    private BCryptPasswordEncoder encoder;
    
    private IUserRepository userRepository;

    public User save(User user) {
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        user.setRoles(Arrays.asList(new Role("USER")));
        return userRepository.save(user);
    }
    
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final Optional<User>optionalUseruser= userRepository.findByFirstName(userName);
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
