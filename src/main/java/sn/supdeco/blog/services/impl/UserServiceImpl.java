package sn.supdeco.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sn.supdeco.blog.models.User;
import sn.supdeco.blog.repositories.UserRepository;
import sn.supdeco.blog.security.JwtProvider;
import sn.supdeco.blog.services.UserService;
import sn.supdeco.blog.services.dtos.RegisterRequest;
import sn.supdeco.blog.services.dtos.UserLoginDto;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

  //  @Override
  //  public User create(User user) {
       // return userRepository.save(user);
   // }
  public void signup(RegisterRequest registerRequest) {
      User user = new User();
      user.setUsername(registerRequest.getUsername());
      user.setFirstName(registerRequest.getFirstname());
      user.setLastName(registerRequest.getLastname());
      user.setPassword(encodePassword(registerRequest.getPassword()));

      userRepository.save(user);
  }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String login(UserLoginDto loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtProvider.generateToken(authenticate);

    }
}
