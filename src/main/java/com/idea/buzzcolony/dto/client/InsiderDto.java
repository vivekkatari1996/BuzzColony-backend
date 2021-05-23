package com.idea.buzzcolony.dto.client;

import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.base.FileEntity;
import com.idea.buzzcolony.service.S3Service;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 23/05/21
 */
@Getter
@Setter
@NoArgsConstructor
public class InsiderDto {

    private Long id;

    private String profilePicUrl;

    private String firstName;

    private String lastName;

    public InsiderDto(AppUser appUser, List<FileEntity> profilePics, S3Service s3Service) {
        this.id = appUser.getId();
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        Optional<FileEntity> profilePic = profilePics.stream().filter(i -> i.getRefId().longValue() == appUser.getId().longValue()).findFirst();
        profilePic.ifPresent(fileEntity -> this.profilePicUrl = s3Service.getPreSignedUrlForDownload(fileEntity.getUuid()));
    }
}
