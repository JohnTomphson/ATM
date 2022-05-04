package com.example.demoatm.abstacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data

public abstract class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean active = true;


}
