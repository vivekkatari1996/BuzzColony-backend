package com.idea.buzzcolony.repo.client;

import com.idea.buzzcolony.enums.post.PostRequest;
import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.client.Post;
import com.idea.buzzcolony.model.client.PostResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    Page<PostResp> findByAppUserAndIsSavedTrueOrderBySavedAtDesc(AppUser appUser, Pageable pageable);

    Page<PostResp> findByAppUserAndReqStatusOrderByReqSentAtDesc(AppUser appUser, PostRequest accepted, Pageable pageable);

    Page<PostResp> findByPostAppUserAndReqStatusNotOrderByReqSentAtDesc(AppUser appUser, PostRequest notYetSent, Pageable pageable);

    Optional<PostResp> findByIdAndPostAppUserAndReqStatusNot(Long id, AppUser appUser, PostRequest request);

    @Query(nativeQuery = true, value = "SELECT DISTINCT b.*\n" +
            "FROM            post_resp a\n" +
            "LEFT JOIN       app_user b\n" +
            "ON              a.app_user_id=b.id\n" +
            "LEFT JOIN       post c\n" +
            "ON              c.id=a.post_id\n" +
            "WHERE           c.app_user_id=?1\n" +
            "AND             a.req_status=?2 limit ?3 offset ?4")
    List<AppUser> findByPostAppUserAndReqStatus(Long loggedInUSerId, String reqStatus, Integer limit, Integer offSet);
}
