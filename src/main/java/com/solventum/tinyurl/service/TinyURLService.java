package com.solventum.tinyurl.service;

public interface TinyURLService {
    public String getTinyURL(String originalURL);
    public String getOriginalURL(String tinyURL);
}
