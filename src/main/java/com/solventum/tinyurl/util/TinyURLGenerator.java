package com.solventum.tinyurl.util;

import com.solventum.tinyurl.model.TinyURL;

public interface TinyURLGenerator {
    long getNextID();
    String convertIDToBase62(long id);
    TinyURL generateTinyURL();
    long getCurrentID();
}
