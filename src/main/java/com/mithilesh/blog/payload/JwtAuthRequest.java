package com.mithilesh.blog.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String userName;

    private String password;

}
