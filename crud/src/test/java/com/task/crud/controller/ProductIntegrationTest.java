package com.task.crud.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.task.crud.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Test
    public void testSaveAndGetProduct() throws Exception {
        // Prepare the JSON request body for the product to be saved
        String requestJson = "{\"name\":\"Test Product\",\"price\":9.99,\"description\":\"Test Description\"}";

        // Perform the POST request to save the product
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpc3MiOiJjcnVkLXNlY3VyaXR5Iiwic3ViIjoidXNlciIsImlhdCI6MTY5MTEyMTUwMSwiZXhwIjoxNjkxOTg1NTAxfQ.KruHFkmq4dQW7n-qVjeU5n0di3hmY7qz9xxlmfoZshY");

        MvcResult saveResult = mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Extract the response content (saved product) from the result
        String savedProductJson = saveResult.getResponse().getContentAsString();

        // Perform the GET request to retrieve the saved product
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/products/1", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Extract the response content (retrieved product) from the result
        String retrievedProductJson = getResult.getResponse().getContentAsString();

        // Assert that the saved product matches the retrieved product
        assertThat(savedProductJson).isEqualTo(retrievedProductJson);

        // Clean up: Delete the saved product
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1", 1L)
                        .headers(headers))
                .andExpect(status().isOk());

        // Verify that the product is deleted and cannot be retrieved
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isNotFound());
    }

    @Test
    public void loginUser() throws Exception {
        // @formatter:off
        this.mockMvc.perform(formLogin().user("user").password("password"))
                .andExpect(authenticated());
        // @formatter:on
    }

}
