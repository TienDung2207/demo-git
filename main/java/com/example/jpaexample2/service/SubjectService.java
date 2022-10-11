package com.example.jpaexample2.service;

import com.example.jpaexample2.entity.Student;

import com.example.jpaexample2.entity.Subject;
import com.example.jpaexample2.repository.SubjectRepository;
import com.example.jpaexample2.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    public Subject addSubject(Subject subject) {
        if(subject != null) {
            return  subjectRepository.save(subject);
        }
        return null;

    }

    //
    public List<Subject> getAllSubject(){
        return subjectRepository.findAllByOrderBySubjectIdAsc();
    }

    public List<Subject> getByName(String subName) {
        return subjectRepository.findByName(subName);
    }

    public Subject updateSubject(int id, Subject subject) {
        if(id >= 1) {
            Subject subject1 = subjectRepository.getById(id);
            if(subject1 != null) {
                subject1.setName(subject.getName());
                subject1.setTinChi(subject.getTinChi());
                return subjectRepository.save(subject1);
            }
        }
        return  null;
    }

    public String deleteSubject(int id) {
        if(id >= 1) {
            Subject subject = subjectRepository.getById(id);
            if(subject != null) {
//                studentRepository.
                subjectRepository.delete(subject);
                return "Xoá thành công";
            }
        }
        return "ERROR";
    }

//    public Subject getBySubjectId(int subjectId) {
//        return subjectRepository.getById(subjectId);
//    }

    public Subject addStudentToSubject(int subjectId, int studentId ) {
        Subject subject = subjectRepository.getBySubjectId(subjectId);
        Student student = studentRepository.getByStudentId(studentId);
//        subject.getStudents().add(student);
//        return subjectRepository.save(subject);
        return subject;
    }
}



