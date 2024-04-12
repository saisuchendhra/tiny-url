package com.solventum.tinyurl.service.impl;

import com.solventum.tinyurl.model.URL;
import com.solventum.tinyurl.service.TinyURLService;
import com.solventum.tinyurl.util.impl.TinyURLGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class TinyURLServiceImpl implements TinyURLService {

    private Map<String, String> urlMappings = new HashMap<>();

    URL url;

    @Autowired
    public TinyURLServiceImpl(URL url){
        this.url = url;
    }

    @Override
    public String getTinyURL(String originalURL) {
        url.setOriginalURL(originalURL);
        return "";
    }

    @Override
    public String getOriginalURL(String tinyURL) {
        return "";
    }
}
