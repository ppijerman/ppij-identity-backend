package org.ppijerman.ppijidentitybackend.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.ppijerman.ppijidentitybackend.server.service.appuser")
public class PpijIdentityBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PpijIdentityBackendApplication.class, args);
    }

}
