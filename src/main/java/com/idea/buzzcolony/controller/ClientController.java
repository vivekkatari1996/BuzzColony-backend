package com.idea.buzzcolony.controller;

import com.idea.buzzcolony.dto.client.PostDto;
import com.idea.buzzcolony.dto.login.SignUpDto;
import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.enums.post.PostRequest;
import com.idea.buzzcolony.service.ClientService;
import com.idea.buzzcolony.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@RestController
@RequestMapping(value = "/api/v1/publisher", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("save-post")
    public ResponseEntity<ApiResponse> saveOrUpdatePost(@RequestBody PostDto postDto) throws Exception {
        ApiResponse apiResponse = clientService.saveOrUpdatePost(postDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("categories")
    public ResponseEntity<ApiResponse> getCategories() throws Exception {
        ApiResponse apiResponse = clientService.getCategories();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("b-types")
    public ResponseEntity<ApiResponse> getBtypes() throws Exception {
        ApiResponse apiResponse = clientService.getBtypes();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("est-part")
    public ResponseEntity<ApiResponse> getEstPartners() throws Exception {
        ApiResponse apiResponse = clientService.getEstPartners();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("est-amt")
    public ResponseEntity<ApiResponse> getEstAmounts() throws Exception {
        ApiResponse apiResponse = clientService.getEstAmounts();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

//    @PostMapping("upload-video")
    public ResponseEntity<ApiResponse> uploadVidep(@RequestBody FileDto fileDto) throws Exception {
        ApiResponse apiResponse = clientService.uploadVidep(fileDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

//    @GetMapping("transcode-status")
    public ResponseEntity<ApiResponse> getTransCodeStatus(@RequestParam Long id) throws Exception {
        ApiResponse apiResponse = clientService.getTransCodeStatus(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("posts")
    public ResponseEntity<ApiResponse> getPosts(@RequestBody PostDto postDto) throws Exception {
        ApiResponse apiResponse = clientService.getPosts(postDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("confirm-video-upload")
    public ResponseEntity<ApiResponse> confirmVideoUpload(@RequestParam Long id) throws Exception {
        ApiResponse apiResponse = clientService.confirmVideoUpload(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("post-details")
    public ResponseEntity<ApiResponse> getPostDetails(@RequestParam Long id) throws Exception {
        ApiResponse apiResponse = clientService.getPostDetails(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("profile-details")
    public ResponseEntity<ApiResponse> updateProfileDetails(@RequestBody SignUpDto signUpDto) throws Exception {
        ApiResponse apiResponse = clientService.updateProfileDetails(signUpDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("profile-details")
    public ResponseEntity<ApiResponse> getProfileDetails(@RequestParam(required = false, defaultValue = "0") Long id) throws Exception {
        ApiResponse apiResponse = clientService.getProfileDetails(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

//    @GetMapping("/inactive")
    public ResponseEntity<ApiResponse> makeUserInActive() throws Exception {
        ApiResponse apiResponse = clientService.makeUserInActive();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("save-unsave-post")
    public ResponseEntity<ApiResponse> saveOrUnsavePost(@RequestParam Long postId) throws Exception {
        ApiResponse apiResponse = clientService.saveOrUnsavePost(postId);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("send-req-post")
    public ResponseEntity<ApiResponse> sendReqToPost(@RequestParam Long postId) throws Exception {
        ApiResponse apiResponse = clientService.sendReqToPost(postId);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("report-post")
    public ResponseEntity<ApiResponse> reportPost(@RequestParam Long postId, @RequestParam String reason) throws Exception {
        ApiResponse apiResponse = clientService.reportPost(postId, reason);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @DeleteMapping("delete-post")
    public ResponseEntity<ApiResponse> deletehisOwnPost(@RequestParam Long postId) throws Exception {
        ApiResponse apiResponse = clientService.deletehisOwnPost(postId);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("others-posts")
    public ResponseEntity<ApiResponse> getOthersPosts(@RequestParam Long id, @RequestParam(required = false, defaultValue = "0") Integer page) throws Exception {
        ApiResponse apiResponse = clientService.getOthersPosts(id, page);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("saved-posts")
    public ResponseEntity<ApiResponse> getSavedPosts(@RequestParam(required = false, defaultValue = "0") Integer page) throws Exception {
        ApiResponse apiResponse = clientService.getSavedOrRequestedPosts(page, Boolean.TRUE);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("requestd-posts")
    public ResponseEntity<ApiResponse> getRequestedPosts(@RequestParam(required = false, defaultValue = "0") Integer page) throws Exception {
        ApiResponse apiResponse = clientService.getSavedOrRequestedPosts(page, Boolean.FALSE);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("requests")
    public ResponseEntity<ApiResponse> getRequests(@RequestParam(required = false, defaultValue = "0") Integer page) throws Exception {
        ApiResponse apiResponse = clientService.getRequestsRecieved(page);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @PostMapping("accpt-rej-requests")
    public ResponseEntity<ApiResponse> acceptOrRejRequests(@RequestParam Long id, @RequestParam PostRequest postRequest) throws Exception {
        ApiResponse apiResponse = clientService.acceptOrRejRequests(id, postRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}
