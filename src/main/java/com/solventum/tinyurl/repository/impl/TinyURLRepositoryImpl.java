package com.solventum.tinyurl.repository.impl;

import com.solventum.tinyurl.model.URL;
import com.solventum.tinyurl.repository.TinyURLRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TinyURLRepositoryImpl implements TinyURLRepository {

    private Map<String, URL> tinyURLMap = new HashMap<>();
    private Map<String, String> orginalURLMap = new HashMap<>();

    /**
     * This method is to get the Long URL from the short URL.
     * @param tinyURL
     * @return
     */
    public String getLongURL(String tinyURL){
        return Optional.ofNullable(tinyURLMap.get(tinyURL))
                .map(tinyURLObj -> tinyURLObj.getOriginalURL())
                .orElse(null);
    }

    /**
     * This method saves the give tinyURL and longURL in a map to make it easy for the later retreivals
     * @param tinyURL
     * @param longURL
     */
    public void saveUrl(String tinyURL, URL longURL){
        if (tinyURL != null && longURL != null) {
            tinyURLMap.put(tinyURL, longURL);
            orginalURLMap.put(longURL.getOriginalURL(),tinyURL);
        }
    }

    /**
     * This method is to get the TinyURL from the original Long URL
     * @param originalURL
     * @return
     */
    public String getTinyURL(String originalURL){
        return Optional.ofNullable(originalURL)
                .map(url -> orginalURLMap.get(url))
                .orElse(null);
    }
}
