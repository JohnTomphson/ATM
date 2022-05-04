package com.example.demoatm.models;

import com.example.demoatm.abstacts.Info;
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
public class Users extends Info {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;


    @Column(nullable = false)
    private String password="absdgklmnop242erxtcaidewqd1232432dnjsfnck";

    @OneToOne
    private Addresses addresses;

    @ManyToMany(fetch = FetchType.EAGER)
//    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roleList; //user_roleList




}
