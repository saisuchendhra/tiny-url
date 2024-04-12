package com.solventum.tinyurl.util.impl;

import com.solventum.tinyurl.util.TinyURLGenerator;

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
    public String generateTinyURL() {
        long id  = getNextID();
        return convertIDToBase62(id);
    }
}
