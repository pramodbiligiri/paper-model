package io.bitken.tts.repo;

import io.bitken.tts.model.entity.EmailSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSubRepo extends JpaRepository<EmailSubscriber, Long> {
}
