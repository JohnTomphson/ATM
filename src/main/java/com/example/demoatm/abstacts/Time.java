package com.example.demoatm.abstacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.time.LocalDate;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class  Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Timestamp issue;

    private Timestamp due;
//    private Timestamp due = Timestamp.valueOf(String.valueOf(LocalDate.now().plusDays(12)));

    private boolean active = true;

}
