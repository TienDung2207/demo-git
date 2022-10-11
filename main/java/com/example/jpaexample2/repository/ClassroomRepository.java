package com.example.jpaexample2.repository;

import com.example.jpaexample2.entity.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassRoom, Integer> {
    ClassRoom getByClassName(String className);

    ClassRoom getByTeacher(String teacher);

    List<ClassRoom> findByTeacher(String teacher);

    ClassRoom getByClassId(int id);

//    Page<ClassRoom> findAllBy();
}
