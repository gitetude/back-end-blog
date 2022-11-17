package sn.supdeco.blog.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserLoginDto {
    private String username;
    private String password;
}
