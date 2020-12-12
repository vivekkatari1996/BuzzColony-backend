package com.idea.thought.repo;

import com.idea.thought.model.base.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmailIgnoreCaseAndIsActiveTrue(String email);

    Optional<AppUser> findByGoogleIdAndIsActiveTrue(String googleId);
}
