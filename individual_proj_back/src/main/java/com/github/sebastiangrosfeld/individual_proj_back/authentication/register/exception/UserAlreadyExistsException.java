package com.github.sebastiangrosfeld.individual_proj_back.authentication.register.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
