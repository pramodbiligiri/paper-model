package io.bitken.tts.model.entity.converter;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Small helper class. Currently just wraps UUID generation.
 **/
@Component
public class FilenameGenerator {

    public String generateNewFilenameWithoutExtension() {
        return UUID.randomUUID().toString();
    }
}
