package com.solventum.tinyurl.controller;

import com.solventum.tinyurl.DTO.RequestDTO;
import com.solventum.tinyurl.service.TinyURLService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling TinyURL operations.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class TinyURLController {

    TinyURLService tinyURLService;

    @Autowired
    public TinyURLController(TinyURLService tinyURLService) {
        this.tinyURLService = tinyURLService;
    }
    private static final Logger logger = LoggerFactory.getLogger(TinyURLController.class);

    /**
     * Encodes a URL to a shortened URL.
     *
     * @param originalURL The original URL to be encoded.
     * @return The shortened URL.
     */
    @PostMapping("v1/encode")
    public ResponseEntity<String> encodeTinyURL(@Valid @RequestBody RequestDTO originalURL){

        logger.info("originalURL : "+originalURL);
        if (originalURL == null || originalURL.equals("")) {
            throw new IllegalArgumentException("Request body contains null value for the URL");
        }
        String tinyURL =  tinyURLService.getTinyURL(originalURL.getUrl());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tinyURL);
    }

    /**
     * Decodes a shortened URL to its original URL.
     *
     * @param tinyUrl The shortened URL to be decoded.
     * @return The original URL.
     */
    @GetMapping("v1/decode/{tinyUrl}")
    public ResponseEntity<String> decodeOriginalURL(@PathVariable(required = true) String tinyUrl){

        logger.debug(tinyUrl);
        if (tinyUrl == null || tinyUrl.equals("")) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        String originalURL =  tinyURLService.getOriginalURL(tinyUrl);
        logger.info("originalURL : "+originalURL);
        return ResponseEntity.ok(originalURL);
    }

    @GetMapping("v1/decode/")
    public ResponseEntity<String> emptyParam(){
            throw new IllegalArgumentException("Request Parameter for the tiny URL cannot be empty");
    }
}
