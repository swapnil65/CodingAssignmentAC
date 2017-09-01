package com.avenuecode.orders.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestOrderResource.class,
    TestProductResource.class,
    TestOrdersService.class,
    TestProductsService.class
})

public class RunAllTestsSuite {}
