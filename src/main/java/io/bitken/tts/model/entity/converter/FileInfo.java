package io.bitken.tts.model.entity.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public class FileInfo {

    private static final Logger LOG = LoggerFactory.getLogger(FileInfo.class);

    public Optional<Duration> getDuration(byte[] data) throws IOException {
        try {
            String[] outputLines = new ProcessExecutor().
                    command("ffprobe", "-i", "-", "-hide_banner", "-show_packets").
                    redirectInput(new ByteArrayInputStream(data)).
                    readOutput(true).execute().outputUTF8().split("\n");

            if (outputLines == null || outputLines.length == 0) {
                return Optional.empty();
            }

            Optional<String> value = Optional.empty();
            for (String line: outputLines) {
                if (!line.startsWith("dts_time")) {
                    continue;
                }

                String[] values = line.split("=",2);
                if (values.length < 2) {
                    continue;
                }

                value = Optional.of(values[1]);
            }

            if (value.isEmpty()) {
                return Optional.empty();
            }

            int durationSec = new BigDecimal(value.get()).setScale(0, RoundingMode.HALF_UP).intValue();
            LOG.debug("File duration in sec:" + durationSec);
            return Optional.of(Duration.ofSeconds(durationSec));
        } catch (InterruptedException e) {
            LOG.error("Error running ffprobe", e);
        } catch (TimeoutException e) {
            LOG.error("Error running ffprobe", e);
        }

        return Optional.empty();
    }

}
