package io.bitken.tts.model.entity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;

@Component
@ConditionalOnProperty(name="audiofiles.storage", havingValue="local")
public class LocalBlobStorageHandler implements BlobStorageHandler {

    @Value("${audio.local.basepath}")
    private String audioLocalBasePath;

    private Path basePath;

    @Autowired
    FilenameGenerator filenameGenerator;

    @PostConstruct
    public void postConstruct() {
         basePath = Path.of(audioLocalBasePath);
    }

    @Override
    public IAudioFile newFile(String filename) throws IOException {
        return new LocalAudioFile(basePath, filename);
    }

    @Override
    public IAudioFile newFile(byte[] data) throws IOException {
        return new LocalAudioFile(basePath, filenameGenerator, data);
    }

}
