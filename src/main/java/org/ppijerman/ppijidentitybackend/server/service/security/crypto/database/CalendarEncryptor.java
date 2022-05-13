package org.ppijerman.ppijidentitybackend.server.service.security.crypto.database;

import org.ppijerman.ppijidentitybackend.server.service.security.crypto.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
import java.util.Calendar;

@Component
@Converter
public class CalendarEncryptor extends AttributeEncryptor<Calendar> {

    @Autowired
    CalendarEncryptor(CryptoService cryptoService) {
        super(cryptoService);
    }

    @Override
    public byte[] convertToDatabaseColumn(Calendar attribute) {
        return cryptoService.encryptCalendar(attribute);
    }

    @Override
    public Calendar convertToEntityAttribute(byte[] dbData) {
        return cryptoService.decryptToCalendar(dbData);
    }
}
