package com.idea.buzzcolony.repo.client;

import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.client.Post;
import com.idea.buzzcolony.model.client.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 25/04/21
 */
@Repository
public interface PostReportRepo extends JpaRepository<PostReport, Long> {

    Optional<PostReport> findByPostAndAppUser(Post post, AppUser appUser);

}
