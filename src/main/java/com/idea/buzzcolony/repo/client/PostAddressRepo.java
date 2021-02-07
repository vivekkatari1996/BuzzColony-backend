package com.idea.buzzcolony.repo.client;

import com.idea.buzzcolony.model.client.PostAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Repository
public interface PostAddressRepo extends JpaRepository<PostAddress, Long> {
}
