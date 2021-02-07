package com.idea.buzzcolony.repo.master;

import com.idea.buzzcolony.model.master.MtCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Repository
public interface MtCountryRepo extends JpaRepository<MtCountry, Long> {

    List<MtCountry> findByIsActiveTrueOrderBySeqAsc();

}
