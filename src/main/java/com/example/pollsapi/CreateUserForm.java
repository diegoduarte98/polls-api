package com.example.pollsapi;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.LongConsumer;

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

    private Long roleId;

    public User toModel(Function<Long, Optional<Role>> finder) {
        Role role = finder.apply(roleId).orElseThrow(NotFoundException::new);
        return new User(name, username, password, email, Collections.singleton(role));
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

    public Long getRoleId() {
        return roleId;
    }
}
