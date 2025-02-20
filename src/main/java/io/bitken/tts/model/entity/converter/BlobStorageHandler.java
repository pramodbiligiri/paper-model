package io.bitken.tts.model.entity.converter;

import java.io.IOException;

public interface BlobStorageHandler {

    IAudioFile newFile(String filename) throws IOException;

    IAudioFile newFile(byte[] data) throws IOException;

}


