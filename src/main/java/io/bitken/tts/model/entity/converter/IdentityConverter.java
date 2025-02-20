package io.bitken.tts.model.entity.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;

public class IdentityConverter implements AttributeConverter<byte[], byte[]> {

    private static final Logger LOG = LoggerFactory.getLogger(IdentityConverter.class);

    @Override
    public byte[] convertToDatabaseColumn(byte[] attribute) {
        LOG.info("converting to db column");
        return attribute;
    }

    @Override
    public byte[] convertToEntityAttribute(byte[] dbData) {
        LOG.info("converting to entity attribute");
        return dbData;
    }
}
