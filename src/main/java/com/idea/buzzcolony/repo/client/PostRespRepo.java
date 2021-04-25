package com.idea.buzzcolony.repo.client;

import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.client.Post;
import com.idea.buzzcolony.model.client.PostResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 06/03/21
 */
@Repository
public interface PostRespRepo extends JpaRepository<PostResp, Long> {
    List<PostResp> findByPostInAndAppUser(List<Post> posts, AppUser appUser);

    Optional<PostResp> findByPostAndAppUser(Post post, AppUser appUser);
}
