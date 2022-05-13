package org.ppijerman.ppijidentitybackend.server.service.security.crypto.database;

import org.ppijerman.ppijidentitybackend.server.service.security.crypto.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
import java.util.UUID;

@Component
@Converter
public class UuidEncryptor extends AttributeEncryptor<UUID> {

    @Autowired
    UuidEncryptor(CryptoService cryptoService) {
        super(cryptoService);
    }

    @Override
    public byte[] convertToDatabaseColumn(UUID attribute) {
        return cryptoService.encryptUUID(attribute);
    }

    @Override
    public UUID convertToEntityAttribute(byte[] dbData) {
        return cryptoService.decryptToUUID(dbData);
    }
}
