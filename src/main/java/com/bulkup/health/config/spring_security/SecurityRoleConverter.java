package com.bulkup.health.config.spring_security;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;
@Converter
public class SecurityRoleConverter implements AttributeConverter<SecurityRole, String> {
    @Override
    public String convertToDatabaseColumn(SecurityRole attribute) {
        return attribute.toString();
    }
    @Override
    public SecurityRole convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(SecurityRole.class).stream()
                .filter(e->e.toString().equals(dbData))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}

