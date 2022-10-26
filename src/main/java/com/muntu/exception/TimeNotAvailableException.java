package com.muntu.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TimeNotAvailableException extends  RuntimeException{

    private static final long serialVersionUID = 1L;
    private String field;

    TimeNotAvailableException(String msg){
        super(msg);
    }

    public TimeNotAvailableException(String field,String msg){
        super(msg);
        this.field=field;
    }

}
