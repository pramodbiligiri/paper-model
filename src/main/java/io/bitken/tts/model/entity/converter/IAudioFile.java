package io.bitken.tts.model.entity.converter;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

public interface IAudioFile {

    String getFilename();
    byte[] getData() throws IOException;
    String getFullPath();
    Optional<Duration> getDuration() throws IOException;

}
