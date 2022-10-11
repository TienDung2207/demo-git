package com.example.jpaexample2.controller;

import com.example.jpaexample2.service.ClassroomService;
import com.example.jpaexample2.entity.Student;
import com.example.jpaexample2.service.StudentService;
import com.example.jpaexample2.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @Autowired
    ClassroomService classroomService;

    @Autowired
    SubjectService subjectService;

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    //
    @GetMapping("get")
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/getbyname")
    public List<Student> getByName(@RequestParam(name = "name") String name) {
        return studentService.getByStudentName(name);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable(name = "id") int id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(name = "id") int id) {
        return studentService.deleteStudent(id);
    }

//--------------------------------------------------------------------------------------

    @PutMapping("{studentId}/classroom/{classId}")
    public Student assignClassroomToStudent(@PathVariable(name = "studentId") int studentId,
                                            @PathVariable(name = "classId") int classId) {
        return studentService.assignClassroomToStudent(classId, studentId);
    }

//    @PostMapping("addwithclass/{id}")
//    public Student addWithClass( @PathVariable(name = "classId") int classId) {
////        return studentService.assignClassroomToStudent(classId);
//    }

    @GetMapping("/getbynameclass")
    public List<Student> getByClassName(@RequestParam(name = "classname") String className) {
        return studentService.getStudentByClassName(className);
    }

    @GetMapping("/getbynameteacher")
    public List<Student> getByTeacher(@RequestParam(name = "teacher") String teacher) {
        return studentService.getStudentByTeacher(teacher);
    }

    @GetMapping("/get/{id}")
    public Student getById(@PathVariable Integer id) {
        return studentService.getByStudentId(id);
    }

//    @PutMapping("/{studentId}/subject/{subjectId}")
//    public Student addSubjectToStudent(@PathVariable int studentId, @PathVariable int subjectId) {
//        Student student = new Student();
//        student = studentService.addSubjectToStudent(studentId, subjectId);
//        return student;
//    }

    //    ============================== DEMO ==============================


}
