package com.avenuecode.orders.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.avenuecode.orders.test.resource.TestOrderResource;
import com.avenuecode.orders.test.resource.TestProductResource;
import com.avenuecode.orders.test.service.TestOrdersService;
import com.avenuecode.orders.test.service.TestProductsService;

@RunWith(Suite.class)
@SuiteClasses({
    TestOrderResource.class,
    TestProductResource.class,
    TestOrdersService.class,
    TestProductsService.class
})

public class RunAllTestsSuite {}
