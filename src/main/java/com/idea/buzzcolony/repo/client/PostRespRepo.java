package com.idea.buzzcolony.repo.client;

import com.idea.buzzcolony.enums.post.PostRequest;
import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.client.Post;
import com.idea.buzzcolony.model.client.PostResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    Page<PostResp> findByPostIsActiveTrueAndAppUserAndIsSavedTrueOrderBySavedAtDesc(AppUser appUser, Pageable pageable);

    Page<PostResp> findByPostIsActiveTrueAndAppUserAndReqStatusOrderByReqSentAtDesc(AppUser appUser, PostRequest accepted, Pageable pageable);

    Page<PostResp> findByPostIsActiveTrueAndPostAppUserAndReqStatusNotOrderByReqSentAtDesc(AppUser appUser, PostRequest notYetSent, Pageable pageable);

    Optional<PostResp> findByPostIsActiveTrueAndIdAndPostAppUserAndReqStatusNot(Long id, AppUser appUser, PostRequest request);

    @Query(nativeQuery = true, value = "SELECT DISTINCT b.id\n" +
            "FROM            post_resp a\n" +
            "LEFT JOIN       app_user b\n" +
            "ON              a.app_user_id=b.id\n" +
            "LEFT JOIN       post c\n" +
            "ON              c.id=a.post_id\n" +
            "WHERE           c.app_user_id=?1\n" +
            "AND             c.is_active=true\n" +
            "AND             a.req_status=?2 limit ?3 offset ?4")
    List<Long> findByPostAppUserAndReqStatus(Long loggedInUSerId, String reqStatus, Integer limit, Integer offSet);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE post_resp\n" +
            "            SET       req_status=?1\n" +
            "\t\t\twhere id in \n" +
            "\t\t\t(select a.id FROM      post_resp a\n" +
            "            LEFT JOIN post b\n" +
            "            ON        a.post_id=b.id\n" +
            "            WHERE     a.app_user_id=?2\n" +
            "            AND       a.req_status=?3\n" +
            "            AND       b.app_user_id=?4)")
    void updatePostRespRequestStatusToRejected(String reqStatusRejected, Long appUserId, String accepted, Long loggedInUSerId);

    @Query(nativeQuery = true, value = "select count(*) from (SELECT DISTINCT b.id\n" +
            "            FROM            post_resp a\n" +
            "            LEFT JOIN       app_user b\n" +
            "            ON              a.app_user_id=b.id\n" +
            "            LEFT JOIN       post c\n" +
            "            ON              c.id=a.post_id\n" +
            "            WHERE           c.app_user_id=?1\n" +
            "            AND             c.is_active=true\n" +
            "            AND             a.req_status=?2) as id")
    Long countOfInsiders(Long id, String name);
}
