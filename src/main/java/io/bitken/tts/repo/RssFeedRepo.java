package io.bitken.tts.repo;

import io.bitken.tts.model.entity.RssFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RssFeedRepo extends JpaRepository<RssFeed, Long> {

    @Query(nativeQuery = true,
            value="select * from rss_feed r where selector = :selector order by create_time desc limit 1")
    List<RssFeed> findLatestFeed(@Param("selector") String selector);

}
