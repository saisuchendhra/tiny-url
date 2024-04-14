package com.solventum.tinyurl.model;

import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class URL {
    private long key;
    private String originalURL;
    private LocalDateTime creationTime;
}
