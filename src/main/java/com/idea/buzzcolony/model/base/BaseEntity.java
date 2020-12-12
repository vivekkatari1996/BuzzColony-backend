package com.idea.buzzcolony.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private AppUser createdBy;

	@ManyToOne
	private AppUser updatedBy;

	@Column(name = "created_on", updatable = false)
	@JsonIgnore
	@CreationTimestamp
	private Timestamp createdOn = new Timestamp(new Date().getTime());

	@Version
	@Column(name = "updated_on")
	private Timestamp updatedOn = new Timestamp(new Date().getTime());
	
}
