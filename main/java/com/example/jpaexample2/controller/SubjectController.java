package com.example.jpaexample2.controller;

import com.example.jpaexample2.entity.Subject;
import com.example.jpaexample2.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

//
//    @PostMapping("/add")
//    public Subject addSubject(@RequestBody Subject subject) {
//        return subjectService.addSubject(subject);
//    }
//    //
//    @GetMapping("get")
//    public List<Subject> getAllSubject() {
//        return subjectService.getAllSubject();
//    }
////
////    @GetMapping("/getbyname")
////    public List<Subject> getByName (@RequestParam(name = "name") String name ) {
////        return studentService.getByStudentName(name);
////    }
//
//    @PutMapping("/update/{id}")
//    public Subject updateSubject(@PathVariable(name = "id") int id, @RequestBody Subject subject) {
//        return subjectService.updateSubject(id, subject);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteSubject(@PathVariable(name = "id") int id) {
//        return subjectService.deleteSubject(id);
//    }
//
////    -------------------------------
////    @PutMapping("/{subjectId}/student/{studentId}")
////    public Subject addStudentToSubject(@PathVariable int subjectId,  @PathVariable int studentId ) {
////        return subjectService.addStudentToSubject(subjectId, studentId);
////    }

    @GetMapping("/{subjectId}/student/{studentId}")
    public Subject addStudentToSubject1(@PathVariable int subjectId,  @PathVariable int studentId ) {
        return subjectService.addStudentToSubject(subjectId, studentId);
    }


}
