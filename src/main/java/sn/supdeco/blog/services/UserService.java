package sn.supdeco.blog.services;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;
import sn.supdeco.blog.services.dtos.RegisterRequest;
import sn.supdeco.blog.services.dtos.UserLoginDto;

@Service
public interface UserService {
   // User create(User user);
  //  User login(String username, String password);
    void signup(RegisterRequest registerRequest);
    String login(UserLoginDto loginRequest);

}
