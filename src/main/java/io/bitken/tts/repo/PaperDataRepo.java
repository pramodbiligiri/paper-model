package io.bitken.tts.repo;

import io.bitken.tts.model.entity.PaperData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.QueryHint;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PaperDataRepo extends JpaRepository<PaperData, Long> {

    @Query(nativeQuery = true,
            value="select p.* from paper_data p, paper_category pc, paper_audio pa " +
                    "where " +
                    "  p.id = pa.paper_id and " +
                    "  p.id = pc.paper_id and " +
                    "  pc.category in (:category) and " +
                    "  p.pubdate IS NOT NULL " +
                    "  group by p.id " +
                    "  order by p.pubdate DESC " +
                    "  LIMIT :limit offset :offset")
    List<PaperData> findLatestPapersWithAudioInCategory(@Param("category") String category, @Param("limit") long limit, @Param("offset") long offset);

    @Query(nativeQuery = true,
            value="select p.* from paper_data p, paper_category pc, paper_audio pa " +
                    "where " +
                    "  p.id = pa.paper_id and " +
                    "  p.id = pc.paper_id and " +
                    "  pc.category in (:categories) and " +
                    "  p.pubdate IS NOT NULL " +
                    "  group by p.id " +
                    "  order by p.pubdate DESC " +
                    "  LIMIT :limit offset :offset")
    List<PaperData> findLatestPapersWithAudioInCategories(@Param("categories") List<String> categories, @Param("limit") long limit, @Param("offset") long offset);

    @Query(nativeQuery = true,
            value="select p.* from paper_data p, paper_audio pa " +
                    "where " +
                    "  p.id = pa.paper_id and " +
                    "  p.pubdate IS NOT NULL " +
                    "  group by p.id " +
                    "  order by p.pubdate DESC " +
                    "  LIMIT :limit offset :offset")
    List<PaperData> findLatestPapersWithAudioAcrossAllCategories(@Param("limit") long limit, @Param("offset") long offset);

    @Query(nativeQuery = true,
            value="select count(distinct p.id) from paper_data p, paper_category pc, paper_audio pa " +
                    "where " +
                    "  p.id = pa.paper_id and " +
                    "  p.id = pc.paper_id and " +
                    "  pc.category in (:category) and " +
                    "  p.pubdate IS NOT NULL ")
    List<Object[]> findCountLatestPapersWithAudioInCategory(@Param("category") String category);

    @Query(nativeQuery = true,
            value="select count(distinct p.id) from paper_data p, paper_category pc, paper_audio pa " +
                    "where " +
                    "  p.id = pa.paper_id and " +
                    "  p.id = pc.paper_id and " +
                    "  pc.category in (:categories) and " +
                    "  p.pubdate IS NOT NULL ")
    List<Object[]> findCountLatestPapersWithAudioInCategories(@Param("categories") List<String> categories);

    @Query(nativeQuery = true,
            value="select count(distinct p.id) from paper_data p, paper_audio pa " +
                    "where " +
                    "  p.id = pa.paper_id and " +
                    "  p.pubdate IS NOT NULL ")
    List<Object[]> findCountPapersWithAudioAcrossAllCategories();

    @QueryHints(@QueryHint(name = "hibernate.query.passDistinctThrough", value = "false"))
    @Query("select distinct p from PaperData p " +
            "where " +
            "  p.id NOT IN (select paperData.id from PaperAudio)")
    List<PaperData> findPapersWithoutAudio();

    @Query("select p from PaperData p, PaperCategory pc " +
            "where " +
            "  p.id NOT IN (select paperData.id from PaperAudio) and " +
            "  p.id = pc.paperData.id AND " +
            "  pc.category = 'cs.AI' " +
            "  ORDER by p.pubDate DESC")
    List<PaperData> findLatestAIPapersWithoutAudio();

    @Query(nativeQuery = true, value = "select exists (select 1 from paper_data p " +
        "where p.arxiv_id = :arxivId)")
    boolean checkExists(@Param("arxivId") String arxivId);

    @Query(nativeQuery = true, value = "select exists (select 1 from paper_data p " +
            "where p.id = :paper_id)")
    boolean checkExists(@Param("paper_id") long paperId);

    @Query("select p from PaperData p where p.arxivId = :arxivId")
    List<PaperData> findByArxivId(String arxivId);

    @QueryHints(@QueryHint(name = "hibernate.query.passDistinctThrough", value = "false"))
    @Query("select distinct p from PaperData p, PaperCategory pc " +
            "where " +
            "  p.id NOT IN (select paperData.id from PaperAudio) and " +
            "  p.id = pc.paperData.id AND " +
            "  pc.category IN (:categories) " +
            "  ORDER by p.pubDate DESC")
    List<PaperData> findLatestPapersWithoutAudioInCategories(List<String> categories);

    @Query(nativeQuery = true,
            value=  "select p.* from paper_data p, paper_audio pa " +
                    "where " +
                    "  search @@ to_tsquery('english', :search_query) and " +
                    "  p.id = pa.paper_id and " +
                    "  p.pubdate IS NOT NULL " +
                    "  group by p.id " +
                    "  ORDER BY ts_rank(search, to_tsquery('english', :search_query)) " +
                    "  LIMIT :limit offset :offset")
    List<PaperData> searchPapers(@Param("search_query") String searchQuery, @Param("limit") long limit,
                                 @Param("offset") long offset);

    @Query(nativeQuery = true,
            value=  "select count(distinct p.id) from paper_data p, paper_audio pa " +
                    "where " +
                    "  search @@ to_tsquery('english', :search_query) and " +
                    "  p.id = pa.paper_id and " +
                    "  p.pubdate IS NOT NULL")
    List<Object[]> searchPapersCount(@Param("search_query") String searchQuery);

    @Query(nativeQuery = true,
            value = "select p.* from paper_data p, paper_category pc, paper_audio pa " +
                    "where " +
                    "  p.id = pa.paper_id and " +
                    "  p.id = pc.paper_id and " +
                    "  pc.category in (:categories) and " +
                    "  p.pubdate IS NOT NULL and " +
                    "  p.pubDate > :pubDate " +
                    "  group by p.id, pc.paper_id " +
                    "  order by p.pubdate DESC ")
    List<PaperData> findPapersNewerThanPubDateInCategory(@Param("categories") List<String> categories,
                                                               @Param("pubDate") OffsetDateTime pubDate);


}


