package com.idea.buzzcolony.repo.master;

import com.idea.buzzcolony.enums.master.MtCatType;
import com.idea.buzzcolony.model.master.MtCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Repository
public interface MtCategoryRepo extends JpaRepository<MtCategory, Long> {

    List<MtCategory> findByIsActiveTrueOrderBySeqAsc();

    Optional<MtCategory> findByType(MtCatType mtCatType);
}
