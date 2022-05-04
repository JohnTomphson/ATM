package com.example.demoatm.models;

import com.example.demoatm.abstacts.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Addresses extends Info {
    @Column(unique = true, nullable = false)
    private Integer zip_code;
}
