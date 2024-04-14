package com.solventum.tinyurl.util.impl;

import com.solventum.tinyurl.model.TinyURL;
import com.solventum.tinyurl.util.TinyURLGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TinyURLGeneratorImpl implements TinyURLGenerator {
    private static final AtomicLong COUNTER  = new AtomicLong(2000000000000L);
    private static final String BASE_62_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String PREFIX_URL = "http://short.est/";

    /**
     * Increments the counter and returns the next ID.
     *
     * @return The next ID
     * @throws RuntimeException If an error occurs while getting the key for the long URL
     */
    @Override
    public long getNextID() {
        try{
            return COUNTER.incrementAndGet();
        }
        catch (Exception exception){
         throw  new RuntimeException("Exception thrown while getting the key for the long URL");
        }
    }

    /**
     * Converts the given ID to a Base62 representation.
     *
     * @param id The ID to convert
     * @return The Base62 representation of the ID
     * @throws RuntimeException If an error occurs while converting to Base62
     */
    @Override
    public String convertIDToBase62(long id) {
        try{
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(BASE_62_CHARACTERS.charAt((int) (id % 62)));
                id /= 62;
            } while (id > 0);
            return PREFIX_URL+sb.reverse().toString();
        }
        catch (Exception exception){
            throw  new RuntimeException("Exception thrown while converting to Base62");
        }
    }

    /**
     * Generates a TinyURL using the current ID.
     *
     * @return The generated TinyURL
     */
    @Override
    public TinyURL generateTinyURL() {
        long id  = getNextID();
        String base62ID = convertIDToBase62(id);
        return TinyURL.builder()
                .tinyURL(base62ID)
                .key(id)
                .creationTime(LocalDateTime.now())
                .build();
    }

    /**
     * Retrieves the current ID.
     *
     * @return The current ID
     * @throws RuntimeException If an error occurs while getting the current ID
     */
    @Override
    public long getCurrentID() {
        try{
            return COUNTER.get();
        }
        catch (Exception ex){
            throw new RuntimeException("Exception thrown while getting currentID");
        }
    }
}
