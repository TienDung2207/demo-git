package com.example.jpaexample2.repository;


import com.example.jpaexample2.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    List<Subject> findAllByOrderBySubjectIdAsc();
    List<Subject> findByName(String subName);

//    List<Subject> findByClassRoom(ClassRoom classRoom);


//    @Query("select u from Subject u where u.id = ?1")
    Subject getBySubjectId(int id);



}