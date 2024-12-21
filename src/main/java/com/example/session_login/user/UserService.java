package com.example.session_login.user;

import com.example.session_login.user.model.User;
import com.example.session_login.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String create(@RequestBody UserDto userDto) {
        User user = userDto.toEntity(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }
}
