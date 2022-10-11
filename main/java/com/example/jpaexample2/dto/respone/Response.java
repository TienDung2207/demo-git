package com.example.jpaexample2.dto.respone;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response<T> {
    private String code;
    private String message;

    private T data;
}
