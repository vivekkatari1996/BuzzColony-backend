package com.idea.buzzcolony.repo.client;

import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.client.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndAppUserAndIsActiveTrue(Long id, AppUser appUser);

    Optional<Post> findByIdAndAppUserNotAndIsActiveTrue(Long postId, AppUser appUser);

    Page<Post> findByAppUserAndIsActiveTrue(AppUser appUser, Pageable pageable);

    Optional<Post> findByIdAndAppUser(Long id, AppUser appUser);

    Optional<Post> findByIdAndIsActiveTrue(Long id);
}
