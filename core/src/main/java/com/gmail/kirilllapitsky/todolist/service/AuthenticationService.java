package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.dto.AuthenticationUserDto;
import com.gmail.kirilllapitsky.todolist.dto.TokenDto;
import com.gmail.kirilllapitsky.todolist.entity.Token;
import com.gmail.kirilllapitsky.todolist.entity.User;
import com.gmail.kirilllapitsky.todolist.exception.AuthenticationException;
import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.repository.TokenRepository;
import com.gmail.kirilllapitsky.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.gmail.kirilllapitsky.todolist.security.Hasher.check;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public TokenDto authenticate(AuthenticationUserDto authenticationUserDto) throws AuthenticationException {
        User user = userRepository.findByLogin(authenticationUserDto.login);
        if (!check(authenticationUserDto.password, user.getPassword())) {
            throw new AuthenticationException("invalid login or password");
        }
        Token token = new Token(UUID.randomUUID().toString(), user);
        tokenRepository.save(token);
        return new TokenDto(token.getValue());
    }

    public User validate(String tokenValue) throws NoSuchEntityException {
        return tokenRepository.findByValue(tokenValue).orElseThrow(() -> new NoSuchEntityException("Invalid token")).getUser();
    }
}

