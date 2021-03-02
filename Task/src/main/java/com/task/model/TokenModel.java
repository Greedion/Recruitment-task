package com.task.model;

import logic.Algorithm;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class TokenModel{

    Map<String, String> tokens;

    public TokenModel(){
        tokens = new ConcurrentHashMap<>();
    }

    public void put(String key, String value){
        tokens.put(key,value);
    }
}
