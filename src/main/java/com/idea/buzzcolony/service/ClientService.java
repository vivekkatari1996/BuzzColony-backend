package com.idea.buzzcolony.service;

import com.idea.buzzcolony.dto.client.PostDto;
import com.idea.buzzcolony.dto.login.SignUpDto;
import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.util.ApiResponse;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
public interface ClientService {

    ApiResponse saveOrUpdatePost(PostDto postDto) throws Exception;

    ApiResponse getCategories();

    ApiResponse getBtypes();

    ApiResponse getEstPartners();

    ApiResponse getEstAmounts();

    ApiResponse uploadVidep(FileDto fileDto) throws Exception;

    ApiResponse getTransCodeStatus(Long id) throws Exception;

    ApiResponse getPosts(PostDto postDto) throws Exception;

    ApiResponse confirmVideoUpload(Long id) throws Exception;

    ApiResponse getPostDetails(Long id) throws Exception;

    ApiResponse getProfileDetails(Long id) throws Exception;

    ApiResponse makeUserInActive() throws Exception;

    ApiResponse updateProfileDetails(SignUpDto signUpDto) throws Exception;

    ApiResponse saveOrUnsavePost(Long postId) throws Exception;

    ApiResponse sendReqToPost(Long postId) throws Exception;

    ApiResponse reportPost(Long postId) throws Exception;

    ApiResponse deletehisOwnPost(Long postId) throws Exception;
}
