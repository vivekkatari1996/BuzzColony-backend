package com.idea.buzzcolony.dto.vimeo;

import com.idea.buzzcolony.model.base.FileEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 07/02/21
 */
@Getter
@Setter
@NoArgsConstructor
public class FileDto {

    private String uuid;

    private String name;

    private String type;

    private Long size;

    private Long refId;

    private String fileType;

    private String base64Str;

    private String documentUrl;

    private Boolean isChanged = Boolean.FALSE;

    public FileDto(FileEntity fileEntity) {
        this.uuid = fileEntity.getUuid();
        this.name = fileEntity.getName();
        this.type = fileEntity.getType();
        this.size = fileEntity.getSize();
        this.refId = fileEntity.getRefId();
        this.fileType = fileEntity.getFileType().name();
    }
}
