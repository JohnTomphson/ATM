package com.example.demoatm.models;

import com.example.demoatm.abstacts.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order extends Info {

    @Column(nullable = false)
    private Date date = new Date(new java.util.Date().getYear(),
            new java.util.Date().getMonth(),
            new java.util.Date().getDay());
    @ManyToOne
    private Users customer;



}
