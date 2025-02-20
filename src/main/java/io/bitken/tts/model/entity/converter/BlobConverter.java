package io.bitken.tts.model.entity.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class BlobConverter implements AttributeConverter<IAudioFile, String> {

    private static final Logger LOG = LoggerFactory.getLogger(BlobConverter.class);

    @Autowired
    BlobStorageHandler blobStorageHandler;

    @Override
    public String convertToDatabaseColumn(IAudioFile file) {
        return file.getFilename();
    }

    @Override
    public IAudioFile convertToEntityAttribute(String filename) {
        try {
            return blobStorageHandler.newFile(filename);
        } catch (IOException e) {
            throw new RuntimeException("Error converting filename " + filename + " to file object", e);
        }
    }

}
