package com.avenuecode.orders.test.config;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.avenuecode.orders.repository.OrderRepository;
import com.avenuecode.orders.repository.ProductRepository;
import com.avenuecode.orders.resource.OrderResource;
import com.avenuecode.orders.resource.ProductResource;
import com.avenuecode.orders.service.OrderService;
import com.avenuecode.orders.service.ProductService;
import com.avenuecode.orders.test.utility.MockOrdersForTest;
import com.avenuecode.orders.test.utility.MockProductsForTest;

@Configuration
public class TestContext {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public OrderService orderService() {
        return Mockito.mock(OrderService.class);
    }
    
    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }
    
    @Bean
    public OrderResource orderResource() {
        return new OrderResource();
    }
    
    @Bean
    public MockOrdersForTest mockOrdersForTest() {
        return new MockOrdersForTest();
    }
    
    @Bean
    public ProductService productService() {
        return Mockito.mock(ProductService.class);
    }
    
    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }
    
    @Bean
    public ProductResource productResource() {
        return new ProductResource();
    }
    
    @Bean
    public MockProductsForTest mockProductsForTest() {
        return new MockProductsForTest();
    }
    
}

