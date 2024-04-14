package com.solventum.tinyurl.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    @NotBlank(message = "Invalid URL: Empty name")
    @NotNull(message = "Invalid URL: Name is NULL")
    @Pattern(regexp = "^(https?://[^/]+)?(/[^\\s]*)?$", message = "Invalid URL: Not a valid URL format")
    String url;
}
