package com.idea.buzzcolony.dto.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idea.buzzcolony.enums.post.PostRequest;
import com.idea.buzzcolony.model.base.FileEntity;
import com.idea.buzzcolony.model.client.Post;
import com.idea.buzzcolony.model.client.PostResp;
import com.idea.buzzcolony.service.S3Service;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {

    private Long id;

    private String title;

    private Long acceptedPartners;

    private String description;

    private Boolean isPhNoHidden = Boolean.TRUE;

    private String occupation;

    private String bType;

    private Long mtSubBTypeId;

    private Long mtEstPartId;

    private Long mtEstAmountId;

    private PostAddressDto postAddressDto;

    private String email;

    private String phoneNo;

    private Long mtCategoryId;

    private String search;

    private Integer page;

    private Long mtCountryId;

    private String videoUrl;

    private Boolean isSaved = Boolean.FALSE;

    private String requestStatus = PostRequest.NOT_YET_SENT.name();

    private Boolean isOwnPosts = Boolean.FALSE;

    private String firstName;

    private String lastName;

    private String profilePicUrl;

    private Long postUserId;

    public PostDto(Post post, String videoUrl, Boolean isFullDetails) {
        if (isFullDetails) {
            this.acceptedPartners = post.getAcceptedPrs();
            this.isPhNoHidden = post.getIsPhNoHidden();
            this.occupation = post.getOccupation();
            this.bType = post.getBType();
            this.postAddressDto = new PostAddressDto(post.getPostAddress());
            this.email = post.getAppUser().getEmail();
            this.phoneNo = post.getAppUser().getPhoneNo();
            this.mtCategoryId = post.getMtCategory().getId();
            this.mtEstAmountId = post.getMtEstAmount().getId();
            this.mtEstPartId = post.getMtEstPart().getId();
        }
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.videoUrl = videoUrl;
        this.firstName = post.getAppUser().getFirstName();
        this.lastName = post.getAppUser().getLastName();
        this.postUserId = post.getAppUser().getId();
    }

    public PostDto(Post post, String videoUrl, Boolean isFullDetails, Optional<PostResp> optionalPostResp) {
        this(post, videoUrl, isFullDetails);
        if (optionalPostResp.isPresent()) {
            this.isSaved = optionalPostResp.get().getIsSaved();
            this.requestStatus = optionalPostResp.get().getReqStatus().name();
        }
    }

    public PostDto(Post post, String videoUrl, Boolean isFullDetails, Optional<PostResp> optionalPostResp, List<FileEntity> profilePics, S3Service s3Service) {
        this(post, videoUrl, isFullDetails, optionalPostResp);
        Optional<FileEntity> profilePic = profilePics.stream().filter(i -> i.getRefId().longValue() == post.getAppUser().getId().longValue()).findFirst();
        if (profilePic.isPresent()) {
            this.profilePicUrl = s3Service.getPreSignedUrlForDownload(profilePic.get().getUuid());
        }
    }
}
