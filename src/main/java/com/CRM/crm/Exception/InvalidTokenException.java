package com.CRM.crm.Exception;

import com.mysql.cj.Messages;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){
       super(Messages.getString("User cant Activated"));
    }
}
