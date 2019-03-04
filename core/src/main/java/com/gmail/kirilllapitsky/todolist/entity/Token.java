package com.gmail.kirilllapitsky.todolist.entity;

import javax.persistence.*;
@Entity
@Table(name = "value")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Token() {
    }

    public Token(String login, User user) {
        this.value = login;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
