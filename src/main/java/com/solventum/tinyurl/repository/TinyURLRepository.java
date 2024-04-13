package com.solventum.tinyurl.repository;

import com.solventum.tinyurl.model.URL;

public interface TinyURLRepository {
    String getLongURL(String tinyURL);
    void saveUrl(String tinyURL, URL longURL);
    String getTinyURL(String originalURL);
}
