package sn.supdeco.blog.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sn.supdeco.blog.models.BlogUserDetails;
import sn.supdeco.blog.models.User;
import sn.supdeco.blog.repositories.UserRepository;

@Service
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

 //   @Override
  //  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //  Optional<User> userOptional = userRepository.findByUsername(username);
      //  userOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found : " + username));
      //  System.out.println("User : " + userOptional.get());
      //  return userOptional.map(BlogUserDetails::new).get();
   // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("No user found " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                true, true, true, true,
                getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
    
}
