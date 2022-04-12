package org.ppijerman.ppijidentitybackend.server;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Security;

@SpringBootApplication
public class PpijIdentityBackendApplication {

    public static void main(String[] args) {
        Security.setProperty("crypto.policy", "unlimited");
        Security.addProvider(new BouncyCastleProvider());
        SpringApplication.run(PpijIdentityBackendApplication.class, args);
    }

}
