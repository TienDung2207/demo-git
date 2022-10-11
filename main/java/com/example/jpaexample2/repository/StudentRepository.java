package com.example.jpaexample2.repository;

import com.example.jpaexample2.entity.ClassRoom;
import com.example.jpaexample2.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    List<Student> findAllByOrderByStudentIdAsc();
    List<Student> findByStudentName(String studentName);

    List<Student> findByClassRoom(ClassRoom classRoom);

    Student getByStudentId(int id);

//     + "OR s.studentName like %?2%"
    @Query("select s from Student s where "
            + "((s.address like concat('%', :address, '%')) OR (:address is null ) OR (:address = ''))"
            + "AND((s.age = :age) or (:age is null ))"
            + "AND((s.studentName like concat('%', :name, '%')) OR (:name is null ) OR (:name = ''))")
//            + "AND ((s.studentName like %?2%)) ")
    Page<Student> findAllS(String address, Integer age, String name, Pageable pageable);
}
