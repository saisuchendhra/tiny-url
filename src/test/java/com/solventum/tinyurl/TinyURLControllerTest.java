package com.solventum.tinyurl;

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
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TinyURLController.class)
public class TinyURLControllerTest {
    private static final  String ENCODE_END_POINT_PATH = "/api/v1/encode";
    private static final  String DECODE_END_POINT_PATH = "/api/v1/decode";

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TinyURLService tinyURLService;
    private static final Logger logger = LoggerFactory.getLogger(TinyURLController.class);

    @Test
    public void encodeTestWithEmptyBodyPostShouldReturn400BadRequest() throws Exception {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl("");

        String requestBody = objectMapper.writeValueAsString(requestDTO);
        mockMvc.perform(post(ENCODE_END_POINT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void encodeTestPostForCorrectRequestShouldCreate201Created() throws Exception {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl("https://example.com/library/react/");

        String requestBody = objectMapper.writeValueAsString(requestDTO);
        mockMvc.perform(post(ENCODE_END_POINT_PATH)
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
        mockMvc.perform(post(ENCODE_END_POINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void encodeTestShouldReturnSameTinyUrlForDuplicateUrls() throws Exception {

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl("https://www.example.com");

        MvcResult result1 = mockMvc.perform(post(ENCODE_END_POINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        MvcResult result2 = mockMvc.perform(post(ENCODE_END_POINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String tinyUrl1 = result1.getResponse().getContentAsString();
        String tinyUrl2 = result2.getResponse().getContentAsString();

        assertEquals(tinyUrl1, tinyUrl2);
    }

    @Test
    public void decodeTestShouldReturn400ForMissingPathVariable() throws Exception {
        mockMvc.perform(get(DECODE_END_POINT_PATH))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void decodeTestShouldReturn200ForCorrectPathVariable() throws Exception {

        String originalUrl = "https://example.com";
        String preCreatedTinyUrl = tinyURLService.getTinyURL(originalUrl);

        mockMvc.perform(get(DECODE_END_POINT_PATH + "?tinyUrl=" + preCreatedTinyUrl))
                .andExpect(status().isOk());
    }

    @Test
    public void decodeTestShouldReturn404IfTinyURLNotExists() throws Exception {


        String preCreatedTinyUrl = "http://short.est/zd5yba1";

        mockMvc.perform(get(DECODE_END_POINT_PATH + "?tinyUrl=" + preCreatedTinyUrl))
                .andExpect(status().isNotFound());
    }

}
