package com.kvarek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WODExecution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    WOD wod;
    @ManyToOne
    Person athlete;
    LocalDate wodDate;
    String CoachComment;
    String UserComment;
    Double wodResult;
}
