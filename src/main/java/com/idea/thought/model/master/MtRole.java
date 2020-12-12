package com.idea.thought.model.master;

import com.idea.thought.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MtRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role name;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;

}
