package com.example.demoatm.models;

import com.example.demoatm.abstacts.Info;
import com.example.demoatm.models.enums.AplicationUserPermission;
import com.example.demoatm.models.enums.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role extends Info {

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole roleName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AplicationUserPermission> permissionEnum;


}
