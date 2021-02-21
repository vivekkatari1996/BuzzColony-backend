package com.idea.buzzcolony.dto.client;

import com.idea.buzzcolony.dto.master.MtCategoryDto;
import com.idea.buzzcolony.dto.master.MtEstAmountDto;
import com.idea.buzzcolony.dto.master.MtEstPartDto;
import com.idea.buzzcolony.dto.master.MtSubBtypeDto;
import com.idea.buzzcolony.model.client.Post;
import com.idea.buzzcolony.model.master.MtEstAmount;
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

    public PostDto(Post post, String videoUrl) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.acceptedPartners = post.getAcceptedPrs();
        this.description = post.getDescription();
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
}
