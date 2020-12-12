package com.idea.buzzcolony.repo;

import com.idea.buzzcolony.model.base.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmailIgnoreCaseAndIsActiveTrue(String email);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUserIdIgnoreCase(String userId);

    Optional<AppUser> findByEmailIgnoreCaseOrUserIdIgnoreCaseAndIsActiveTrue(String email, String userId);

    Optional<AppUser> findByEmailIgnoreCase(String email);
}
