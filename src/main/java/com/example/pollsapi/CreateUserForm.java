package com.example.pollsapi;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserForm {

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 15)
    @UniqueValue(domainAttribute = "username", klass = User.class)
    private String username;

    @Email
    @Size(max = 100)
    @UniqueValue(domainAttribute = "email", klass = User.class)
    private String email;

    @NotBlank
    @Size(max = 8)
    private String password;

    public User toModel() {
        return new User(name, username, password, email);
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
