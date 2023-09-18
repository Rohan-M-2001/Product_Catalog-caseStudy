package com.example.product_catalog;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductCatalogApplicationTests {

   @Autowired
   private DiscoveryClient discoveryClient;

   @Test
   void discoveryClientShouldNotBeNull() {
      assertNotNull(discoveryClient);
   }

}
