package com.idea.buzzcolony.repo.client;

import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.client.Post;
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

}
