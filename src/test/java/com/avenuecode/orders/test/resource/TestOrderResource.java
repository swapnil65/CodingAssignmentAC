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

import com.avenuecode.orders.repository.OrderRepository;
import com.avenuecode.orders.resource.OrderResource;
import com.avenuecode.orders.service.OrderService;
import com.avenuecode.orders.test.config.MyWebConfig;
import com.avenuecode.orders.test.config.TestContext;
import com.avenuecode.orders.test.utility.MockOrdersForTest;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, MyWebConfig.class})
@WebAppConfiguration
public class TestOrderResource {
 
    
	private MockMvc mockMvc;
	
	@Autowired
	private OrderRepository orderRepository;
    
    @MockBean
    private OrderService orderServiceMock;
    
    @InjectMocks
    private OrderResource orderResource;
    
    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private MockOrdersForTest mockOrders;
    
    @Before
    public void setUp(){
    	DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }
    
    @Test
    public void testGetSingleOrder_OrderFound_ShouldReturnFoundOrderEntry() throws Exception {
    	when(orderServiceMock.getOrder("3")).thenReturn(mockOrders.getSingleOrder());
        mockMvc
                .perform(get("/orders/{orderId}", "3")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.orderNumber", is("RTL_1003")))
        		.andExpect(jsonPath("$.status", is("SHIPPED")));
        verify(orderServiceMock, times(1)).getOrder("3");
        verifyNoMoreInteractions(orderServiceMock);

    }
    
    @Test
    public void testGetOrderByStatus_OrdersFound_ShouldReturnFoundOrdersEntries() throws Exception {
    	when(orderServiceMock.searchOrders("shipped")).thenReturn(mockOrders.getOrderByStatus());
        mockMvc
                .perform(get("/orders/search/{searchCriteria}", "shipped")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) 
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].orderNumber", is("RTL_1001")))
        		.andExpect(jsonPath("$[0].status", is("SHIPPED")))
        		.andExpect(jsonPath("$[1].orderNumber", is("RTL_1003")))
        		.andExpect(jsonPath("$[1].status", is("SHIPPED")))
        		.andExpect(jsonPath("$[2].orderNumber", is("RTL_1004")))
        		.andExpect(jsonPath("$[2].status", is("SHIPPED")));
        verify(orderServiceMock, times(1)).searchOrders("shipped");
        verifyNoMoreInteractions(orderServiceMock);
    }
    
    @Test
    public void testGetOrderByDiscount_OrdersFound_ShouldReturnFoundOrdersEntries() throws Exception {
    	when(orderServiceMock.searchOrders("discountApplied")).thenReturn(mockOrders.getOrderByDiscount());
        mockMvc
                .perform(get("/orders/search/{searchCriteria}", "discountApplied")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) 
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].orderNumber", is("RTL_1002")))
        		.andExpect(jsonPath("$[0].status", is("FULFILLED")))
        		.andExpect(jsonPath("$[1].orderNumber", is("RTL_1003")))
        		.andExpect(jsonPath("$[1].status", is("SHIPPED")));
        verify(orderServiceMock, times(1)).searchOrders("discountApplied");
        verifyNoMoreInteractions(orderServiceMock);
    }
    
    @Test
    public void testGetOrderMoreThanTwoProducts_OrdersFound_ShouldReturnFoundOrdersEntries() throws Exception {
    	when(orderServiceMock.searchOrders("moreThanTwoProducts")).thenReturn(mockOrders.getOrderMoreThanTwoProducts());
        mockMvc
                .perform(get("/orders/search/{searchCriteria}", "moreThanTwoProducts")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) 
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].orderNumber", is("RTL_1003")))
        		.andExpect(jsonPath("$[0].status", is("SHIPPED")))
        		.andExpect(jsonPath("$[0].products", hasSize(3)));
        verify(orderServiceMock, times(1)).searchOrders("moreThanTwoProducts");
        verifyNoMoreInteractions(orderServiceMock);
    }
    
    @Test
    public void testGetAllOrders_OrdersFound_ShouldReturnFoundOrderEntries() throws Exception {
    	
    	when(orderServiceMock.listOrders()).thenReturn(mockOrders.getAllOrders());

        mockMvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].orderNumber", is("RTL_1001")))
                .andExpect(jsonPath("$[0].status", is("SHIPPED")))
                .andExpect(jsonPath("$[1].orderNumber", is("RTL_1002")))
        		.andExpect(jsonPath("$[1].status", is("FULFILLED")))
                .andExpect(jsonPath("$[2].orderNumber", is("RTL_1003")))
        		.andExpect(jsonPath("$[2].status", is("SHIPPED")))
        		.andExpect(jsonPath("$[3].orderNumber", is("RTL_1004")))
        		.andExpect(jsonPath("$[3].status", is("SHIPPED")))
        		.andExpect(jsonPath("$[4].orderNumber", is("RTL_1005")))
        		.andExpect(jsonPath("$[4].status", is("FULFILLED")));
        verify(orderServiceMock, times(1)).listOrders();
        verifyNoMoreInteractions(orderServiceMock);
    }
}

