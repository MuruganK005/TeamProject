package com.TeamExampleProject.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class TeamException extends Exception{
    private HttpStatus status;
    private String errorMessage;
    public TeamException(HttpStatus status, String errorMessage) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
