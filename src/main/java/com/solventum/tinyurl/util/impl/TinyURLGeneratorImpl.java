package com.solventum.tinyurl.util.impl;

import com.solventum.tinyurl.model.TinyURL;
import com.solventum.tinyurl.util.TinyURLGenerator;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class TinyURLGeneratorImpl implements TinyURLGenerator {
    private static final AtomicLong COUNTER  = new AtomicLong(2000000000000l);
    private static final String BASE_62_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Override
    public long getNextID() {
        return COUNTER.incrementAndGet();
    }

    @Override
    public String convertIDToBase62(long id) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(BASE_62_CHARACTERS.charAt((int) (id % 62)));
            id /= 62;
        } while (id > 0);
        return sb.reverse().toString();
    }

    @Override
    public TinyURL generateTinyURL() {
        long id  = getNextID();
        String base62ID = convertIDToBase62(id);
        TinyURL tinyURL = TinyURL.builder()
                .tinyURL(base62ID)
                .key(id)
                .creationTime(LocalDateTime.now())
                .build();

        return tinyURL;
    }

    @Override
    public long getCurrentID() {
        return COUNTER.get();
    }
}
