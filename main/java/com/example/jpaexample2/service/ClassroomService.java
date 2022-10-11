package com.example.jpaexample2.service;


import com.example.jpaexample2.entity.ClassRoom;
import com.example.jpaexample2.entity.Student;
import com.example.jpaexample2.repository.ClassroomRepository;
import com.example.jpaexample2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {
    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    StudentRepository studentRepository;

    public ClassRoom addClass(ClassRoom classRoom) {
        if(classRoom != null) {
//            List<Student> students = classRoom.getStudents();
//                if(students != null) {
//                    for(Student s : students) {
////                        Student studentCheck = studentRepository.getByStudentId(s.getStudentId());
//                        studentRepository.
//                    }
//                }
            return  classroomRepository.save(classRoom);
        }
        return null;
    }

//    public ClassRoom createClassAndStudent(ClassRoom classRoom, int stdId) {
//        if(classRoom != null) {
//            List<Student> students = classRoom.getStudents();
//            if (students != null) {
//                for (Student s : students) {
//                    int id = stdId;
//                    Student studentCheck = studentRepository.getByStudentId(s.getStudentId());
//                    if (studentCheck == null) {
//                        studentRepository.save(s);
//                    } else {
//                        studentRepository.save(studentCheck);
//                    }
//                }
//            }
//            return classroomRepository.save(classRoom);
//        }
//        return null;
//    }

    public List<ClassRoom> getAllClass(){
        List<ClassRoom> classes = classroomRepository.findAll();
        return classes;
    }

    public Page<ClassRoom> getClassrooms(int page, int size, Optional<String> sortBy) {
        Page<ClassRoom> classRooms = classroomRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, sortBy.orElse("id")));
        return classRooms;
    }

    public ClassRoom getByClassId(int id) {
        return classroomRepository.getByClassId(id);
    }

    public ClassRoom getByClassName(String className) {
        return classroomRepository.getByClassName(className);
    }

    public List<ClassRoom> getByTeacher(String teacher) {
        return classroomRepository.findByTeacher(teacher);
    }

    public ClassRoom updateClass(int id, ClassRoom classRoom) {
        if(id >= 1){
            ClassRoom classRoom1 = classroomRepository.getByClassId(id);
            if(classRoom != null) {
                classRoom1.setClassName(classRoom.getClassName());
                classRoom1.setGradle(classRoom.getGradle());
                classRoom1.setTeacher(classRoom.getTeacher());
                return classroomRepository.save(classRoom1);
            }
        }
        return  null;
    }

    public String deleteClass(int id) {
        if(id >= 1) {
            ClassRoom classRoom = classroomRepository.getByClassId(id);
            if(classRoom != null) {
                classroomRepository.delete(classRoom);
                return "Xoá thành công";
            }
        }
        return "ERROR";
    }
}