package com.idea.buzzcolony.repo;

import com.idea.buzzcolony.model.base.TokenStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 14/03/21
 */
@Repository
public interface TokenStoreRepo extends JpaRepository<TokenStore, Long> {

    Optional<TokenStore> findByToken(String token);
}
