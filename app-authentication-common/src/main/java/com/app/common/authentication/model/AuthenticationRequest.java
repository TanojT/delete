package com.app.common.authentication.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
}
