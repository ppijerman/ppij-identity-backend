package org.ppijerman.ppijidentitybackend.server.service.security.crypto.database;

import org.ppijerman.ppijidentitybackend.server.service.security.crypto.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;

@Component
@Converter
public class StringEncryptor extends AttributeEncryptor<String> {

    @Autowired
    StringEncryptor(CryptoService cryptoService) {
        super(cryptoService);
    }

    @Override
    public byte[] convertToDatabaseColumn(String attribute) {
        return cryptoService.encryptString(attribute);
    }

    @Override
    public String convertToEntityAttribute(byte[] dbData) {
        return cryptoService.decryptToString(dbData);
    }
}
