package com.idea.buzzcolony.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idea.buzzcolony.enums.base.FileType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class FileEntity {

    @Id
    private String uuid;

    private Long refId;

    private String name;

    private String type;

    private Long size;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Column(name = "created_on", updatable = false)
    @JsonIgnore
    @CreationTimestamp
    private Timestamp createdOn = new Timestamp(new Date().getTime());

    @Version
    @Column(name = "updated_on")
    private Timestamp updatedOn = new Timestamp(new Date().getTime());
}
