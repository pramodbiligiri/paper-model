package io.bitken.tts.model.entity.converter;

import com.google.cloud.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gcp.storage.GoogleStorageResource;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

public class GcsAudioFile implements IAudioFile {

    private static final Logger LOG = LoggerFactory.getLogger(GcsAudioFile.class);

    private String filename;

    private Storage storage; // not used in equals or hashCode()
    private String bucketName; // not used in equals or hashCode

    public GcsAudioFile(Storage storage, String bucketName, String filename) throws IOException {
        this.storage = storage;
        this.bucketName = bucketName;
        this.filename = filename;
    }

    public GcsAudioFile(Storage storage, FilenameGenerator filenameGenerator, String bucketName, byte[] data) throws IOException {
        this.filename = save(storage, filenameGenerator, bucketName, data);
        this.bucketName = bucketName;
        this.storage = storage;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public byte[] getData() throws IOException {
        return read(storage, bucketName, filename);
    }

    @Override
    public String getFullPath() {
        return "https://storage.googleapis.com/" + bucketName + "/" + filename;
    }

    @Override
    public Optional<Duration> getDuration() throws IOException {
        return new FileInfo().getDuration(getData());
    }

    private static byte[] read(Storage storage, String bucketName, String filename) throws IOException {
        if (filename == null) {
            LOG.info("Filename is null. Returning empty array for data");
            return new byte[]{};
        }

        GoogleStorageResource res = createGsResource(storage, bucketName, filename);
        return StreamUtils.copyToByteArray(res.getInputStream());
    }

    private static String save(Storage storage, FilenameGenerator filenameGenerator, String bucketName,
                               byte[] data) throws IOException {
        if (data == null) {
            LOG.error ("No data to save. Returning null as filename");
            return null;
        }

        String filename = filenameGenerator.generateNewFilenameWithoutExtension() + ".mp3";

        GoogleStorageResource res = createGsResource(storage, bucketName, filename);
        try (OutputStream os = res.getOutputStream()) {
            os.write(data);
        }

        return filename;
    }

    private static GoogleStorageResource createGsResource(Storage storage, String bucketName, String filename) {
        return new GoogleStorageResource(storage, "gs://" + bucketName + "/" + filename);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GcsAudioFile that = (GcsAudioFile) o;
        return Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename);
    }
}
