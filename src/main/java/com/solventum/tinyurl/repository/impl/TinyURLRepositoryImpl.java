package com.solventum.tinyurl.repository.impl;

import com.solventum.tinyurl.model.URL;
import com.solventum.tinyurl.repository.TinyURLRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TinyURLRepositoryImpl implements TinyURLRepository {
    private Map<String, URL> tinyURLMap = new HashMap<>();

    public String getLongURL(String tinyURL){
        return tinyURLMap.get(tinyURL).getOriginalURL();
    }

    public void saveUrl(String tinyURL,URL longURL){
        tinyURLMap.put(tinyURL,longURL);
    }
}
