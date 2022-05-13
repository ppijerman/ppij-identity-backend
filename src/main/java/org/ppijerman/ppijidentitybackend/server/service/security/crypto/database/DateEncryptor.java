package org.ppijerman.ppijidentitybackend.server.service.security.crypto.database;

import org.ppijerman.ppijidentitybackend.server.service.security.crypto.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class DateEncryptor extends AttributeEncryptor<Date> {

    @Autowired
    DateEncryptor(CryptoService cryptoService) {
        super(cryptoService);
    }

    @Override
    public byte[] convertToDatabaseColumn(Date attribute) {
        return cryptoService.encryptDate(attribute);
    }

    @Override
    public Date convertToEntityAttribute(byte[] dbData) {
        return cryptoService.decryptToDate(dbData);
    }
}
