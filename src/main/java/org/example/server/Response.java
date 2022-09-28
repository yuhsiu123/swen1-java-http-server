package org.example.server;

import lombok.Data;

@Data

public class Response {
    private Integer status;
    private String message;
    private String contentType;
    private String content;

    public void setStatus(int  status){
        this.status=status;
    }

    public void setMessage(String message){
        this.message=message;
    }

    public int getStatus(){
        return status;
    }
}
