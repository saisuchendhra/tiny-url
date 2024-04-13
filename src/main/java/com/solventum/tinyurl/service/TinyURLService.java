package com.solventum.tinyurl.service;

import com.solventum.tinyurl.model.TinyURL;
import org.springframework.stereotype.Service;

public interface TinyURLService {
     String getTinyURL(String originalURL);
     String getOriginalURL(String tinyURL);
     void insertTinyURL(TinyURL tinyURL, String originalURL);
}
