package com.task.crud.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.task.crud.model.Product;
import com.task.crud.service.ProductService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = getProductList();

        when(productService.getAllProducts()).thenReturn(products);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(getExpectedJsonResponse(), result.getResponse().getContentAsString());

        verify(productService, times(1)).getAllProducts();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testGetProductById() throws Exception {

        when(productService.getProductById(anyLong())).thenReturn(getOptionalProduct());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(getSingleExpectedJsonResponse(), result.getResponse().getContentAsString());

        verify(productService, times(1)).getProductById(anyLong());
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testCreateProduct() throws Exception {
        when(productService.saveProduct(any(Product.class))).thenReturn(getProduct());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products")
                .content(getRequestJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(getSingleExpectedJsonResponse(), result.getResponse().getContentAsString());

        verify(productService, times(1)).saveProduct(any(Product.class));
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(productService.saveProduct(any(Product.class))).thenReturn(getProduct());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/1")
                .content(getRequestJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(getSingleExpectedJsonResponse(), result.getResponse().getContentAsString());

        verify(productService, times(1)).saveProduct(any(Product.class));
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(anyLong());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("", result.getResponse().getContentAsString());

        verify(productService, times(1)).deleteProduct(anyLong());
        verifyNoMoreInteractions(productService);
    }

    private static String getRequestJson() {
        return "{\"name\":\"Product A\",\"price\":100.00,\"description\":\"Description A\"}";
    }

    private static String getExpectedJsonResponse() {
        return "[{\"id\":1,\"name\":\"Product A\",\"price\":100.0,\"description\":\"Description A\"},"
               + "{\"id\":2,\"name\":\"Product B\",\"price\":150.0,\"description\":\"Description B\"}]";
    }

    private static String getSingleExpectedJsonResponse() {
        return "{\"id\":1,\"name\":\"Product A\",\"price\":100.0,\"description\":\"Description A\"}";
    }
    private static List<Product> getProductList() {
        Product product1 = new Product(1L, "Product A", 100.0, "Description A");
        Product product2 = new Product(2L, "Product B", 150.0, "Description B");
        List<Product> products = Arrays.asList(product1, product2);
        return products;
    }

    private static Optional<Product> getOptionalProduct() {
        return Optional.of(getProduct());
    }

    private static Product getProduct() {
        return new Product(1L, "Product A", 100.0, "Description A");
    }
}