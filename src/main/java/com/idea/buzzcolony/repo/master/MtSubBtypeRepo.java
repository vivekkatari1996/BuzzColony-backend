package com.idea.buzzcolony.repo.master;

import com.idea.buzzcolony.model.master.MtSubBtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 07/02/21
 */
@Repository
public interface MtSubBtypeRepo extends JpaRepository<MtSubBtype, Long> {
}
