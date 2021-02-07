package com.idea.buzzcolony.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
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
@MappedSuperclass
@Getter
@Setter
public class MasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "created_on", updatable = false)
    @JsonIgnore
    @CreationTimestamp
    private Timestamp createdOn = new Timestamp(new Date().getTime());

    @Version
    @Column(name = "updated_on")
    private Timestamp updatedOn = new Timestamp(new Date().getTime());
}
