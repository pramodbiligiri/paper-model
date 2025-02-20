package io.bitken.tts.repo;

import io.bitken.tts.model.entity.PaperTtsTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperTtsTaskRepo extends JpaRepository<PaperTtsTask, Long> {

}
