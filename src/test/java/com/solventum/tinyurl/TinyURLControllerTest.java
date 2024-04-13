package com.solventum.tinyurl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solventum.tinyurl.DTO.RequestDTO;
import com.solventum.tinyurl.controller.TinyURLController;
import com.solventum.tinyurl.service.TinyURLService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TinyURLController.class)
public class TinyURLControllerTest {
    private static final  String END_POINT_PATH = "/api/v1/encode";

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TinyURLService tinyURLService;
    private static final Logger logger = LoggerFactory.getLogger(TinyURLController.class);

    @Test
    public void encodeTestPostShouldReturn400BadRequest() throws Exception {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl("");

        String requestBody = objectMapper.writeValueAsString(requestDTO);
        mockMvc.perform(post(END_POINT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void encodeTestPostShouldCreate201Created() throws Exception {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl("https://www.facebook.com");

        String requestBody = objectMapper.writeValueAsString(requestDTO);
        mockMvc.perform(post(END_POINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void encodeTestPostShould400ForWrongFormatURL() throws Exception {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl("ht://www.facebook.com");

        String requestBody = objectMapper.writeValueAsString(requestDTO);
        mockMvc.perform(post(END_POINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}
