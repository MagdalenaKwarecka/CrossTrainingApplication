package com.kvarek.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class WodExecution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Wod wod;
    @ManyToOne
    Person athlete;
    LocalDate wodDate;
    String CoachComment;
    String UserComment;
    Double wodResult;
}
