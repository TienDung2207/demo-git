package com.example.jpaexample2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer studentId;


    @Column(name = "name")
    private String studentName;


    @Column(name = "age")
    private Integer age;


    @Column(name = "address")
    private String address;


    @ManyToOne()
    @JoinColumn(name = "class_id")
//    @JsonIgnore
    @JsonManagedReference
    private ClassRoom classRoom;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY   )
    @JoinTable(name = "student_subject" ,
                joinColumns = @JoinColumn(name = "student_id"),
                inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

}