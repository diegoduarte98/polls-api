package com.example.pollsapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid CreateUserForm form) {
        User user = userRepository.save(form.toModel());
        return new UserDto(user);
    }

    @PutMapping("{id}")
    public UserDto update(@PathVariable Long id, @RequestBody @Valid UpdateUserForm form) {
        return userRepository.findById(id).map(userFound -> {
            userFound.setName(form.getName());
            userFound.setPassword(form.getPassword());
            userFound.setEmail(form.getEmail());

            return new UserDto(userRepository.save(userFound));
        }).orElseThrow(NotFoundException::new);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public UserDto findById(@PathVariable Long id) {
        return userRepository.findById(id).map(UserDto::new).orElseThrow(NotFoundException::new);
    }
}
