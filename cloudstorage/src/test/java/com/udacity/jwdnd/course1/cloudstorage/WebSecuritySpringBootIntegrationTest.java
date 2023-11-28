package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

public class WebSecuritySpringBootIntegrationTest {

    @RunWith(SpringRunner.class)
    @SpringBootTest(webEnvironment = RANDOM_PORT)
    public class WebSecuritySpringBootIntegrationTest {
        @Autowired
        private TestRestTemplate template;
    }

    @Test
    public void givenPublicResource_whenGetViaWeb_thenOk() {
        ResponseEntity<String> result = template.getForEntity("/hello/baeldung.txt", String.class);
        assertEquals("Hello From Baeldung", result.getBody());
    }
}
