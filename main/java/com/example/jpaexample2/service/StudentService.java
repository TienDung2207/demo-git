package com.example.jpaexample2.service;

import com.example.jpaexample2.dto.respone.ResponeData;
import com.example.jpaexample2.dto.respone.Response;
import com.example.jpaexample2.entity.ClassRoom;
import com.example.jpaexample2.repository.ClassroomRepository;
import com.example.jpaexample2.entity.Student;
import com.example.jpaexample2.entity.Subject;
import com.example.jpaexample2.repository.StudentRepository;
import com.example.jpaexample2.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public Student addStudent(Student student) {
        try {
            if(student != null) {
                log.info("Begin add student: name={} - add={} - age={}",student.getStudentName(),student.getAddress(), student.getAge());

                Student addedStudent = studentRepository.save(student);
                log.info("End add student: id={} - name={} - add={} - age={}",
                        addedStudent.getStudentId(),addedStudent.getStudentName(),addedStudent.getAddress(), addedStudent.getAge());

                return addedStudent;
            }
            log.error("Error add student: student null");
            return null;
        }
        catch (Exception ex) {
            log.error("Add student ex: " ,ex);
            return null;
        }
    }
    //
    public List<Student> getAllStudent(){
//        studentRepository.findAll()
        return studentRepository.findAllByOrderByStudentIdAsc();
    }

    public List<Student> getByStudentName(String studentName) {
        return studentRepository.findByStudentName(studentName);
    }

    public Student updateStudent(int id, Student student) {
        if(id >= 1) {
            Student student1 = studentRepository.getByStudentId(id);
            if(student != null) {
                student1.setAddress(student.getAddress());
                student1.setAge(student.getAge());
                student1.setStudentName(student.getStudentName());
                return studentRepository.save(student1);
            }
        }
        return  null;
    }

    public String deleteStudent(int id) {
        if(id >= 1) {
            Student student = studentRepository.getByStudentId(id);
            if(student != null) {
//                studentRepository.
                studentRepository.delete(student);
                return "Xoá thành công";
            }
        }
        return "ERROR";
    }

    public Student assignClassroomToStudent(int classId, int studentId) {
        ClassRoom classRoom = classroomRepository.getByClassId(classId);
        Student student = studentRepository.getByStudentId(studentId);
        student.setClassRoom(classRoom);

        return studentRepository.save(student);
    }

//    public Student addWithClasroom(Student student, int id) {
//
//    }



    public List<Student> getStudentByClassName(String classname) {
        ClassRoom classRooms = classroomRepository.getByClassName(classname);
        return studentRepository.findByClassRoom(classRooms);
    }

    public List<Student> getStudentByTeacher(String teacher) {
        ClassRoom classRooms = classroomRepository.getByTeacher(teacher);
        return studentRepository.findByClassRoom(classRooms);
    }


    public Student getByStudentId(int studentId) {
        return studentRepository.getByStudentId(studentId);
    }

    public Student addSubjectToStudent(int studentId, int subId ) {
        Subject subject = subjectRepository.getBySubjectId(subId);
        Student student = studentRepository.getByStudentId(studentId);
        student.getSubjects().add(subject);
        return studentRepository.saveAndFlush(student);
    }

//    ============================== DEMO ==============================




    public Page<Student> listAlLS(int pageNumber, String sortField, String sortDir, String address, Integer age,String name) {
        Pageable pageable = PageRequest.of(  pageNumber - 1,5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
        );

//        if(address != null  || name != null ) {
//            return studentRepository.findAllS(address, name, pageable);
//        }
        return studentRepository.findAllS(address, age, name, pageable);

//        Page<Student> students = studentRepository.findAll(pageable);
//        return studentRepository.findAll(pageable);
    }

//    public Response<ResponeData<Student>> listALL2(int pageNumber, String sortField, String sortDir, String address, String name) {
//        Pageable pageable = PageRequest.of(  pageNumber - 1,5,
//                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
//        );
//        return pageable;
//    }

}
