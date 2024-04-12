package com.solventum.tinyurl.service.impl;

import com.solventum.tinyurl.model.TinyURL;
import com.solventum.tinyurl.model.URL;
import com.solventum.tinyurl.repository.TinyURLRepository;
import com.solventum.tinyurl.service.TinyURLService;
import com.solventum.tinyurl.util.TinyURLGenerator;
import com.solventum.tinyurl.util.impl.TinyURLGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class TinyURLServiceImpl implements TinyURLService {

    @Autowired
    TinyURLRepository tinyURLRepository;

    @Autowired
    TinyURLGenerator tinyURLGenerator;


    @Override
    public String getTinyURL(String originalURL) {
        TinyURL tinyURL = tinyURLGenerator.generateTinyURL();
        URL url = URL.builder()
                .originalURL(originalURL)
                .key(tinyURL.getKey())
                .creationTime(tinyURL.getCreationTime())
                .build();
        tinyURLRepository.saveUrl(tinyURL.getTinyURL(), url);
        return tinyURL.getTinyURL();
    }

    @Override
    public String getOriginalURL(String tinyURL) {
        return tinyURLRepository.getLongURL(tinyURL);
    }
}
