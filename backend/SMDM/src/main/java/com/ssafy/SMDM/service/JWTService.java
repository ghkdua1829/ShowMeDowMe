package com.ssafy.SMDM.service;

public interface JWTService {
    public <T> String create(String key, T data, String subject);

}
