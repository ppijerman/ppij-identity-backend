package org.ppijerman.ppijidentitybackend.server.service.security.crypto.database;

import org.ppijerman.ppijidentitybackend.server.service.security.crypto.CryptoService;

import javax.persistence.AttributeConverter;

public abstract class AttributeEncryptor<S> implements AttributeConverter<S, byte[]> {

    CryptoService cryptoService;

    AttributeEncryptor(
            CryptoService cryptoService
    ) {
        this.cryptoService = cryptoService;
    }
}
