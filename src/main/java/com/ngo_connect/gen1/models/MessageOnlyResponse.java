package com.ngo_connect.gen1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MessageOnlyResponse {
    public String msg;

    public MessageOnlyResponse(String msg){
        this.msg = msg;
    }
    public MessageOnlyResponse(){

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
