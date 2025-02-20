package io.bitken.tts.model.entity.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.time.Duration;

@SpringBootTest (classes = {LocalBlobStorageHandler.class, FilenameGenerator.class})
public class LocalAudioFileTest {

    @Autowired
    LocalBlobStorageHandler storageHandler;

    @Test
    @DisabledIfEnvironmentVariable(named = "GITHUB_ACTION", matches = "true")
    public void testFileDuration() throws IOException {
        byte[] bytes = StreamUtils.copyToByteArray(new ClassPathResource("sample-title.mp3").getInputStream());
        IAudioFile audioFile = storageHandler.newFile(bytes);
        Assertions.assertEquals(Duration.ofSeconds(6), audioFile.getDuration().get());
    }
}
