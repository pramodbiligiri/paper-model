package io.bitken.tts.model.entity.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class LocalAudioFile implements IAudioFile {

    private static final Logger LOG = LoggerFactory.getLogger(LocalAudioFile.class);

    private String filename;

    private Path basePath; // not used in equals() or hashCode()

    public LocalAudioFile(Path basePath, String filename) throws IOException {
        this.basePath = basePath;
        this.filename = filename;
    }

    public LocalAudioFile(Path basePath, FilenameGenerator filenameGenerator, byte[] data) throws IOException {
        this.filename = save(basePath, filenameGenerator, data);
        this.basePath = basePath;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public byte[] getData() throws IOException {
        return read(basePath, filename);
    }

    @Override
    public String getFullPath() {
        return basePath.resolve(filename).toAbsolutePath().toString();
    }

    @Override
    public Optional<Duration> getDuration() throws IOException {
        return new FileInfo().getDuration(getData());
    }

    private static byte[] read(Path basePath, String filename) throws IOException {
        if (filename == null) {
            LOG.info("Filename is null. Returning empty array for data");
            return new byte[]{};
        }

        FileSystemResource fsr = new FileSystemResource(basePath.resolve(filename));

        return StreamUtils.copyToByteArray(fsr.getInputStream());
    }

    private static String save(Path basePath, FilenameGenerator filenameGenerator, byte[] data) throws IOException {
        if (data == null) {
            LOG.error ("No data to save. Returning null as filename");
            return null;
        }

        if (!basePath.toFile().exists()) {
            basePath.toFile().mkdir();
        }

        String filename = filenameGenerator.generateNewFilenameWithoutExtension() + ".mp3";
        FileSystemResource fsr = new FileSystemResource(basePath.resolve(filename));

        try (OutputStream os = fsr.getOutputStream()) {
            os.write(data);
            os.flush();
        }

        return filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalAudioFile that = (LocalAudioFile) o;
        return Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename);
    }
}
