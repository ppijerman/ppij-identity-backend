package org.ppijerman.ppijidentitybackend.server;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class PpijIdentityBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PpijIdentityBackendApplication.class, args);
    }

}
