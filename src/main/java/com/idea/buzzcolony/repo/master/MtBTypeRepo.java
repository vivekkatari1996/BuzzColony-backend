package com.idea.buzzcolony.repo.master;

import com.idea.buzzcolony.model.master.MtBtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 07/02/21
 */
@Repository
public interface MtBTypeRepo extends JpaRepository<MtBtype, Long> {

    List<MtBtype> findByIsActiveTrueOrderBySeqAsc();

}
