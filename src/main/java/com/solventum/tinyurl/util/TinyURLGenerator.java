package com.solventum.tinyurl.util;

public interface TinyURLGenerator {
    long getNextID();
    String convertIDToBase62(long id);
    String generateTinyURL();
}
