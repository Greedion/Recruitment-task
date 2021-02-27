package com.task.model;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class TokenModel{

    Map<String, String> tokens;

    public TokenModel(){
        tokens = new HashMap<>();
    }

    public void put(String key, String value){
        tokens.put(key,value);
    }
}
