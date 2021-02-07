package com.idea.buzzcolony.service;

import com.idea.buzzcolony.dto.client.PostDto;
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
}
