package com.example.jpaexample2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "classroom")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int classId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "teacher")
    private String teacher;

    @Column(name = "gradle")
    private String gradle;


//    @JsonIgnore
//    @JsonIgnoreProperties("classRoom")
    @JsonBackReference
    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
    private List<Student> students;

//    @JsonManagedReference
//    public List<Student> getStudents() {
//        return students;
//    }
}
