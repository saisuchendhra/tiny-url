package com.solventum.tinyurl.service.impl;

import com.solventum.tinyurl.exception.TinyURLAlreadyExistsException;
import com.solventum.tinyurl.exception.TinyURLNotFoundException;
import com.solventum.tinyurl.model.TinyURL;
import com.solventum.tinyurl.model.URL;
import com.solventum.tinyurl.repository.TinyURLRepository;
import com.solventum.tinyurl.service.TinyURLService;
import com.solventum.tinyurl.util.TinyURLGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TinyURLServiceImpl implements TinyURLService {
    private static final Logger logger = LoggerFactory.getLogger(TinyURLServiceImpl.class);

    @Autowired
    TinyURLRepository tinyURLRepository;

    @Autowired
    TinyURLGenerator tinyURLGenerator;

    /**
     * Calls the tinyURLGenerator class to get the tinyURL from the originalURL
     * @param originalURL
     * @return
     */
    @Override
    public String getTinyURL(String originalURL) {
        if(tinyURLRepository.getTinyURL(originalURL)!=null){
            return tinyURLRepository.getTinyURL(originalURL);
        }

        TinyURL tinyURL = Optional.ofNullable(tinyURLGenerator.generateTinyURL())
                .orElseThrow(() -> new IllegalStateException("Failed to generate TinyURL"));

        logger.trace("Inserting TinyURL into the repository...");
        insertTinyURL(tinyURL, originalURL);
        return tinyURL.getTinyURL();
    }


    /**
     * Gets original URL by calling the repository bean
     * @param tinyURL
     * @return
     */
    @Override
    public String getOriginalURL(String tinyURL) {
        String longURL =  tinyURLRepository.getLongURL(tinyURL);
        if(longURL==null){
            throw new TinyURLNotFoundException("Original URL not found for this TinyURL.");
        }
        return  longURL;
    }

    /**
     * Inserts and maps the tinyURL and the original long URL
     * @param tinyURL
     * @param originalURL
     */
    @Override
    public void insertTinyURL(TinyURL tinyURL, String originalURL) {
        URL url = URL.builder()
                .originalURL(originalURL)
                .key(tinyURL.getKey())
                .creationTime(tinyURL.getCreationTime())
                .build();
        try{
            String tinyURLValue = Optional.ofNullable(tinyURL)
                    .map(TinyURL::getTinyURL)
                    .orElseThrow(() -> new TinyURLNotFoundException("TinyURL is null"));

            tinyURLRepository.saveUrl(tinyURLValue, url);
        }
        catch (Exception exception){
            throw new RuntimeException("Exception while saving to the DB.");
        }
    }
}
