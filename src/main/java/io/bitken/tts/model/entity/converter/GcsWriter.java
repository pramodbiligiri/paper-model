package io.bitken.tts.model.entity.converter;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ConditionalOnProperty(name="audiofiles.storage", havingValue="gcsWrite")
public class GcsWriter implements BlobStorageHandler {

    @Autowired
    Storage storage;

    @Value("${gcs.audiofiles.bucket}")
    String bucketName;

    @Autowired
    FilenameGenerator filenameGenerator;

    @Override
    public IAudioFile newFile(String filename) throws IOException {
        return new GcsAudioFile(storage, bucketName, filename);
    }

    @Override
    public IAudioFile newFile(byte[] data) throws IOException {
        return new GcsAudioFile(storage, filenameGenerator, bucketName, data);
    }
}
