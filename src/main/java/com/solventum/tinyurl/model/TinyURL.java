package com.solventum.tinyurl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TinyURL {
    private long key;
    private String tinyURL;
    private LocalDateTime creationTime;
}
