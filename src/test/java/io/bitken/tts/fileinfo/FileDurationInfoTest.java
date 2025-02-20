package io.bitken.tts.fileinfo;

import io.bitken.tts.model.entity.converter.FileInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import java.time.Duration;
import java.util.Optional;

public class FileDurationInfoTest {

    private static final Logger LOG = LoggerFactory.getLogger(FileDurationInfoTest.class);

    @Test
    @DisabledIfEnvironmentVariable(named = "GITHUB_ACTION", matches = "true")
    public void testFileDurationIsObtained() throws Exception {
        byte[] fileBytes = StreamUtils.copyToByteArray(
                new ClassPathResource("sample-title.mp3").getInputStream());

        Optional<Duration> durationOpt = new FileInfo().getDuration(fileBytes);
        Assertions.assertEquals(Duration.ofSeconds(6), durationOpt.get());

        fileBytes = StreamUtils.copyToByteArray(
                new ClassPathResource("title-2.mp3").getInputStream());

        durationOpt = new FileInfo().getDuration(fileBytes);
        Assertions.assertEquals(Duration.ofSeconds(4), durationOpt.get());

    }
}
