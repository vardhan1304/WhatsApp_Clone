package com.whatsapp.exception;

import com.whatsapp.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserException extends Exception{
   public UserException(String message){
        super(message);
    }
}
