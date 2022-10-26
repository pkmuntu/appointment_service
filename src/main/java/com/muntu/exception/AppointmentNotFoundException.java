package com.muntu.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppointmentNotFoundException extends  RuntimeException{

    private static final long serialVersionUID = 1L;
    private String field;

    public AppointmentNotFoundException(String msg){
        super(msg);
    }

    public AppointmentNotFoundException(String field,String msg){
        super(msg);
        this.field=field;
    }
}
