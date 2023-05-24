package com.github.sebastiangrosfeld.individual_proj_back.auth;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
