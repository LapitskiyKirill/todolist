package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.RegisterUserDto;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.gmail.kirilllapitsky.todolist.security.Hasher.getHash;

@Service
@Transactional
public class RegisterService {

    private final UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterUserDto registerUserDto) {
        User user = new User(registerUserDto.login, getHash(registerUserDto.password));
        userRepository.save(user);
    }
}
