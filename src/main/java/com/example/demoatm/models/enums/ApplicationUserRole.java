package com.example.demoatm.models.enums;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;
@AllArgsConstructor
@Getter
@ToString
public enum ApplicationUserRole {
    THE_BOSS(Sets.newHashSet(AplicationUserPermission.CREATE_BANK,AplicationUserPermission.READ_BANK,AplicationUserPermission.CREATE_ATM)),
    ADMIN(Sets.newHashSet(AplicationUserPermission.READ_ATM,AplicationUserPermission.UPDATE_ATM)),
    BOSS(Sets.newHashSet(AplicationUserPermission.CREATE_ATM,AplicationUserPermission.DELETE_ATM,AplicationUserPermission.DELETE_USER)),
    CUSTOMER(Sets.newHashSet(AplicationUserPermission.READ_BANK,AplicationUserPermission.READ_ATM,AplicationUserPermission.READ_CARD));
    private Set<AplicationUserPermission> permissions;

}
