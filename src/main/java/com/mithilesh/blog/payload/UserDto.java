package com.mithilesh.blog.payload;

import com.mithilesh.blog.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private int userId;

    @NotEmpty
    @Size(min = 3,message = "Name must be minimum length of 4")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 5,message = "UserName must be of minimum length of 5")
    private String userName;

    @NotEmpty
    @Size(min = 3,max = 10,message = "Password length should be min 3 and max 10 in length")
    private String password;

    @NotEmpty
    private String about;

    private Set<CommentDto> comments=new HashSet<>();
    Set<Role> roles = new HashSet<>();
}
