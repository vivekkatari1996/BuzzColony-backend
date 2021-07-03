package com.idea.buzzcolony.dto.client;

import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.enums.post.PostRequest;
import com.idea.buzzcolony.model.base.AppUser;
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
//@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private String mtCategoryType;

    private String search;

    private Integer page;

    private Long mtCountryId;

    private String videoUrl;

    private Boolean isSaved = Boolean.FALSE;

    private String reqStatus = PostRequest.NOT_YET_SENT.name();

    private Boolean isOwnPosts = Boolean.FALSE;

    private String firstName;

    private String lastName;

    private String profilePicUrl;

    private Long postUserId;

    private FileDto videoDto;

    private String categoryName;

    private String bTypeName;

    private String estPartName;

    private String estAmountName;

    private String patentNo;

    public PostDto(Post post, String videoUrl, Boolean isFullDetails, Optional<PostResp> optionalPostResp, List<FileEntity> profilePics, S3Service s3Service) {
        if (isFullDetails) {
            this.acceptedPartners = post.getAcceptedPrs();
            this.isPhNoHidden = post.getIsPhNoHidden();
            this.occupation = post.getOccupation();
            this.bType = post.getBType();
            this.postAddressDto = new PostAddressDto(post.getPostAddress());
            this.email = post.getContactEmail();
            this.phoneNo = post.getPhoneNo();
            this.mtCategoryType = post.getMtCategory().getType().name();
            this.mtEstAmountId = post.getMtEstAmount().getId();
            this.mtEstPartId = post.getMtEstPart().getId();
            this.categoryName = post.getMtCategory().getName();
            this.estAmountName = post.getMtEstAmount().getName();
            this.estPartName = post.getMtEstPart().getName();
            this.bTypeName = post.getMtSubBtype().getName();
            this.patentNo = post.getPatentNo();
        }
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.videoUrl = videoUrl;
        this.firstName = post.getAppUser().getFirstName();
        this.lastName = post.getAppUser().getLastName();
        this.postUserId = post.getAppUser().getId();
        if (optionalPostResp.isPresent()) {
            this.isSaved = optionalPostResp.get().getIsSaved();
            this.reqStatus = optionalPostResp.get().getReqStatus().name();
        }
        Optional<FileEntity> profilePic = profilePics.stream().filter(i -> i.getRefId().longValue() == post.getAppUser().getId().longValue()).findFirst();
        profilePic.ifPresent(fileEntity -> this.profilePicUrl = s3Service.getPreSignedUrlForDownload(fileEntity.getUuid()));
    }

    public PostDto(PostResp postResp) {
        Post post = postResp.getPost();
        AppUser appUser = postResp.getAppUser();
        this.id = postResp.getId();
        this.title = post.getTitle();
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        this.reqStatus = postResp.getReqStatus().name();
        this.postUserId = appUser.getId();
    }
}
