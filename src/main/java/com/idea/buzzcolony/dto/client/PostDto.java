package com.idea.buzzcolony.dto.client;

import com.idea.buzzcolony.dto.master.MtCategoryDto;
import com.idea.buzzcolony.dto.master.MtSubBtypeDto;
import com.idea.buzzcolony.model.client.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Long id;

    private String title;

    private String estimatedPartners;

    private Long acceptedPartners;

    private String description;

    private Boolean isPhNoHidden = Boolean.TRUE;

    private String occupation;

    private String bType;

    private Long mtSubBTypeId;

    private PostAddressDto postAddressDto;

    private String email;

    private String phoneNo;

    private MtCategoryDto mtCategoryDto;

    private MtSubBtypeDto mtSubBtypeDto;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.estimatedPartners = post.getEstimatedPrs();
        this.acceptedPartners = post.getAcceptedPrs();
        this.description = post.getDescription();
        this.isPhNoHidden = post.getIsPhNoHidden();
        this.occupation = post.getOccupation();
        this.bType = post.getBType();
        this.postAddressDto = new PostAddressDto(post.getPostAddress());
        this.email = post.getAppUser().getEmail();
        this.phoneNo = post.getAppUser().getPhoneNo();
        this.mtCategoryDto = new MtCategoryDto(post.getMtCategory());
    }
}
