package io.bitken.tts.repo;

import io.bitken.tts.model.entity.PaperAudio;
import io.bitken.tts.model.entity.PaperData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperAudioRepo extends JpaRepository<PaperAudio, Long> {

    @Query(nativeQuery = true, value = "select exists (select 1 from paper_audio pa " +
            "where pa.paper_id = :paperId)")
    boolean checkExists(@Param("paperId") long paperId);

}