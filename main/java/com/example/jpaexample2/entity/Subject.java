package com.example.jpaexample2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "sub_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectId;

    @Column(name = "sub_name")
    private String name;

    @Column(name = "tinchi")
    private int tinChi;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;
}
