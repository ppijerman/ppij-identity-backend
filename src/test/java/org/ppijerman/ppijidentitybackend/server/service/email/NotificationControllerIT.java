package org.ppijerman.ppijidentitybackend.server.service.email;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotificationControllerIT {

    // Pass in configuration to authenticate with local email server
    // As a small optimisation, start the greenMail server once for the entire test class
    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("adib", "springboot"))
            .withPerMethodLifecycle(false);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldNotifyUserViaEmail() throws Exception {

        String payload = "{ \"email\": \"adib@spring.io\", \"content\": \"This is email confirmation!\" }";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        ResponseEntity<Void> response = this.testRestTemplate.postForEntity("/notifications", request, Void.class);

        assertEquals(302, response.getStatusCodeValue());


    }
}