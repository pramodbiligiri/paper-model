package io.bitken.tts.repo;

import io.bitken.tts.model.entity.PaperCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PaperCategoryRepo extends JpaRepository<PaperCategory, Long>  {

    @Query(nativeQuery = true,
        value = "select distinct(category) from paper_category where category like 'cs.%'"
    )
    List<String> getAllCsCategories();
}
