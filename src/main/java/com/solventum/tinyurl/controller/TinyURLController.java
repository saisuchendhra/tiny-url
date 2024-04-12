package com.solventum.tinyurl.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling TinyURL operations.
 */
@RestController
public class TinyURLController {
    /**
     * Encodes a URL to a shortened URL.
     *
     * @param originalURL The original URL to be encoded.
     * @return The shortened URL.
     */
    @PostMapping("/encode")
    public String encodeTinyURL(@RequestBody String originalURL){
        return "";
    }

    /**
     * Decodes a shortened URL to its original URL.
     *
     * @param tinyUrl The shortened URL to be decoded.
     * @return The original URL.
     */
    @GetMapping("/decode")
    public String decodeOriginalURL(@PathVariable String tinyUrl){
        return "";
    }
}
