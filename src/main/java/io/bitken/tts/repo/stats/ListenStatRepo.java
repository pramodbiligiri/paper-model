package io.bitken.tts.repo.stats;

import io.bitken.tts.model.entity.stats.ListenStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListenStatRepo extends JpaRepository<ListenStat, Long> {

    @Modifying
    @Query(
        nativeQuery = true,
        value = "insert into listen_stat (session_id, paper_id, src, current, total) " +
                "values (:session_id, :paper_id, :src, :current, :total) "

    )
    void insertStat(@Param("session_id") String session,
                           @Param("paper_id") long paperId,
                           @Param("src") String src,
                           @Param("current") int current,
                           @Param("total") int total);
}
