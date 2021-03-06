package com.example.pollsapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid CreateUserForm form) {
        Function<Long, Optional<Role>> findById = roleRepository::findById;
        User user = form.toModel(findById);
        User userSaved = userRepository.save(user);
        return new UserDto(userSaved);
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
