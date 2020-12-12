package com.idea.thought.model.base;

import com.idea.thought.model.master.MtRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"app_user_id", "mt_role_id"})})
public class UserRole extends BaseEntity {

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private MtRole mtRole;
}
