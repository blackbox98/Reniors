package com.common.reniors.common.exception;

public class ApiRequestException extends RuntimeException{
    public ApiRequestException(){
        super();
    }
    public ApiRequestException(String message){
        super(message);
    }
}
