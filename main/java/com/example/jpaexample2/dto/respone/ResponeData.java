package com.example.jpaexample2.dto.respone;

public class ResponeData<T>{
    private Integer total;
    private Integer pageNo;
    private Integer pageSize;
    private T data;
}
