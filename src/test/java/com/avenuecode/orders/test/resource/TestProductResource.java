package com.avenuecode.orders.test.resource;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.avenuecode.orders.repository.ProductRepository;
import com.avenuecode.orders.resource.ProductResource;
import com.avenuecode.orders.service.ProductService;
import com.avenuecode.orders.test.config.MyWebConfig;
import com.avenuecode.orders.test.config.TestContext;
import com.avenuecode.orders.test.utility.MockProductsForTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, MyWebConfig.class})
@WebAppConfiguration
public class TestProductResource {
	
	private MockMvc mockMvc;
	
	@Autowired
	private ProductRepository productRepository;
    
    @MockBean
    private ProductService productServiceMock;
    
    @InjectMocks
    private ProductResource productResource;
    
    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private MockProductsForTest mockProducts;
    
    @Before
    public void setUp(){
    	DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }
    
    @Test
    public void testFindOne_ProductFound_ShouldReturnFoundProductEntry() throws Exception {
    	when(productServiceMock.getProduct("2")).thenReturn(mockProducts.getSingleProduct());
        mockMvc
                .perform(get("/products/{productId}", "2")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.upc", is("1358743283")))
        		.andExpect(jsonPath("$.sku", is("7394650110003")))
        		.andExpect(jsonPath("$.description", is("Polo Shirt")))
        		.andExpect(jsonPath("$.price", is(new BigDecimal(19.99))));
        verify(productServiceMock, times(1)).getProduct("2");
        verifyNoMoreInteractions(productServiceMock);
    }
    
    @Test
    public void testGetAllProducts_ProductsFound_ShouldReturnFoundProductEntries() throws Exception {
    	
    	when(productServiceMock.listProducts()).thenReturn(mockProducts.getAllProducts());

        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].upc", is("1257833283")))
                .andExpect(jsonPath("$[0].description", is("Diva Jeans")))
                .andExpect(jsonPath("$[1].upc", is("1358743283")))
        		.andExpect(jsonPath("$[1].description", is("Polo Shirt")))
                .andExpect(jsonPath("$[2].upc", is("1458843283")))
        		.andExpect(jsonPath("$[2].description", is("Floral Swing Skirt")))
        		.andExpect(jsonPath("$[3].upc", is("1358753283")))
        		.andExpect(jsonPath("$[3].description", is("Denim Short")))
        		.andExpect(jsonPath("$[4].upc", is("1258793283")))
        		.andExpect(jsonPath("$[4].description", is("True Skinny Jeans")));
        verify(productServiceMock, times(1)).listProducts();
        verifyNoMoreInteractions(productServiceMock);
    }
    
    @Test
    public void testGetProductsMoreThan30_ProductsFound_ShouldReturnFoundProductsEntries() throws Exception {
    	when(productServiceMock.getProductsWithPriceMoreThan30()).thenReturn(mockProducts.getProductsMoreThan30());
        mockMvc
                .perform(get("/products/search/priceMoreThanThirty")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) 
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].upc", is("1257833283")))
        		.andExpect(jsonPath("$[0].description", is("Diva Jeans")))
        		.andExpect(jsonPath("$[1].upc", is("1458843283")))
        		.andExpect(jsonPath("$[1].description", is("Floral Swing Skirt")))
        		.andExpect(jsonPath("$[2].upc", is("1258793283")))
        		.andExpect(jsonPath("$[2].description", is("True Skinny Jeans")));
        verify(productServiceMock, times(1)).getProductsWithPriceMoreThan30();
        verifyNoMoreInteractions(productServiceMock);
    }

}
