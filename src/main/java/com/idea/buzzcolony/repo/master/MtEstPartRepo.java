package com.idea.buzzcolony.repo.master;

import com.idea.buzzcolony.model.master.MtEstPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 21/02/21
 */
@Repository
public interface MtEstPartRepo extends JpaRepository<MtEstPart, Long> {
    List<MtEstPart> findByIsActiveTrueOrderBySeqAsc();
}
