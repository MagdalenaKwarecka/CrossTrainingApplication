package com.kvarek.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Wod implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    WodType wodType;
    Integer wodDurationInSeconds;
    @OneToMany
    List<Round> rounds;
    @OneToMany
    List<WodExecution> wodExecutionList;
}
