package com.idea.buzzcolony.repo.master;

import com.idea.buzzcolony.model.master.MtEstAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 21/02/21
 */
@Repository
public interface MtEstAmountRepo extends JpaRepository<MtEstAmount, Long> {
    List<MtEstAmount> findByIsActiveTrueOrderBySeqAsc();
}
