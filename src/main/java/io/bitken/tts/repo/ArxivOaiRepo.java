package io.bitken.tts.repo;

import io.bitken.tts.model.entity.ArxivOai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArxivOaiRepo extends JpaRepository<ArxivOai, Long> {

    @Query("select max(a.id) from ArxivOai a")
    Long findMaxId();

    @Query("select max(a.batchId) from ArxivOai a")
    Long findMaxBatchId();

    @Query("select a from ArxivOai a where batchId=:batch_id")
    List<ArxivOai> findByBatchId(@Param("batch_id") Long batchId);

    @Query(nativeQuery = true,
            value = "select a.oai_xml from arxiv_oai a " +
                    "order by batch_id desc, a.id desc " +
                    "limit 1")
    String findMostRecentXml();

    @Query(nativeQuery = true,
            value = "select distinct(batch_id) from arxiv_oai where batch_id >= :startingBatchId"
    )
    List<Long> findBatchIdsGreaterOrEqualTo(@Param("startingBatchId") long startingBatchId);

    @Query(nativeQuery = true,
            value = "select min(batch_id) from arxiv_oai"
    )
    Long findSmallestBatchId();

}
