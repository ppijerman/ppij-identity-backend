package org.ppijerman.ppijidentitybackend.server.service.email;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotificationControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldNotifyUserViaEmail() {
        
    }
}