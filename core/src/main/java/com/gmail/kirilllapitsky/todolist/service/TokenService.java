package com.gmail.kirilllapitsky.todolist.service;

import com.gmail.kirilllapitsky.todolist.exception.NoSuchEntityException;
import com.gmail.kirilllapitsky.todolist.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void delete(String token) throws NoSuchEntityException {
        tokenRepository.delete(tokenRepository.findByValue(token).orElseThrow(() -> new NoSuchEntityException()));
    }
}
