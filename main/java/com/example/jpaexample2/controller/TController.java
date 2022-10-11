package com.example.jpaexample2.controller;

import com.example.jpaexample2.dto.request.SearchClassRequest;
import com.example.jpaexample2.dto.respone.StudentDto;
import com.example.jpaexample2.entity.ClassRoom;
import com.example.jpaexample2.entity.Student;
import com.example.jpaexample2.entity.Subject;
import com.example.jpaexample2.entity.User;
import com.example.jpaexample2.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Slf4j
@Controller
public class TController {

    @Autowired
    StudentService studentService;

    @RequestMapping("")
    public String homeS(Model model) {
//        String address = "";
//        Integer age = 0;
//        String name = "";
        return lSByPage(model, 1,"studentId", "asc", "", "", "");
    }


    @GetMapping("/page/{pageNumber}")
    public String lSByPage(Model model, @PathVariable("pageNumber") int currentPage,
                           @RequestParam(name = "sortField") String sortField,
                           @RequestParam(name = "sortDir") String sortDir,
                           @RequestParam(name = "address") String address,
                           @RequestParam(name = "age") String age,
                           @RequestParam(name = "name") String name) {
        Integer ageValue = null;
        try {
             ageValue = Integer.parseInt(age);
        }
        catch (Exception e) {
            log.error("parse ex: " , e );
        }
        Page<Student> page = studentService.listAlLS(currentPage, sortField, sortDir, address, ageValue, name);

        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Student> students = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("students", students);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        model.addAttribute("address", address);
        model.addAttribute("age", age);
        model.addAttribute("name", name);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "home";
    }


    @GetMapping("/student/{id}/detail")
    public String pageDetail(@PathVariable Integer id, Model model) {
        Student student = studentService.getByStudentId(id);
        model.addAttribute("stdID",student.getStudentId());
        model.addAttribute("name", student.getStudentName());
        model.addAttribute("age", student.getStudentId());
        model.addAttribute("address", student.getAddress());

        ClassRoom classRoom = student.getClassRoom();

        if(classRoom != null) {
            model.addAttribute("classID",student.getClassRoom().getClassId());
            model.addAttribute("className",student.getClassRoom().getClassName());
            model.addAttribute("teacher",student.getClassRoom().getTeacher());
            model.addAttribute("grable",student.getClassRoom().getGradle());
        }

        Subject subject = student.getSubjects().get(1);

        List<Subject> subjects = student.getSubjects();

        if(subject != null) {
            model.addAttribute("subjectId", subject.getSubjectId());
            model.addAttribute("SubName", subject.getName());
            model.addAttribute("tinChi",subject.getTinChi());
        }

        if(subjects != null) {
            model.addAttribute("subjects", subjects);
        }


        return "detail";
    }

//    @GetMapping("/studentdetail")
//    public String pageDetail2(@RequestParam SearchClassRequest searchClassRequest, Model model) {
//        Student student = studentService.getByStudentId(searchClassRequest.getId());
//        model.addAttribute("stdID",student.getStudentId());
//        model.addAttribute("name", student.getStudentName());
//        model.addAttribute("age", student.getStudentId());
//        model.addAttribute("address", student.getAddress());
//
//        ClassRoom classRoom = student.getClassRoom();
//
//        if(classRoom != null) {
//            model.addAttribute("classID",student.getClassRoom().getClassId());
//            model.addAttribute("className",student.getClassRoom().getClassName());
//            model.addAttribute("teacher",student.getClassRoom().getTeacher());
//            model.addAttribute("grable",student.getClassRoom().getGradle());
//        }
//
//        return "detail";
//    }


//    public String viewHome(Model model) {
//        Page<Student> page = studentService.listAlLS();
//        List<Student> students = page.getContent();
//
//        model.addAttribute("students",students);
//
//        return "home.html";
//    }

//    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
//    @PostMapping("/saveUser")
//    public String searchF(@ModelAttribute User user) {
////        System.out.println(studentDto.getAddress());
////        System.out.println(studentDto.getAge());
////        System.out.println(studentDto.getName());
//        System.out.println("User'name: " + user.getName());
//        System.out.println("Email'name: " + user.getEmail());
//        System.out.println("Age'name: " + user.getAge());
//
//
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("detail");
//        modelAndView.addObject("user",user);
//        return modelAndView.getViewName();
//    }

    @PostMapping("/saveUser")
    public String handle(@RequestParam(name = "email") String uEmail, @RequestParam(name = "age") int uAge,
                         @RequestParam(name = "name") String uName, Model model) {
        System.out.println("User'name: " + uName);
        System.out.println("Email'name: " + uEmail);
        System.out.println("Age'name: " + uAge);
        model.addAttribute("name",uName);
        model.addAttribute("age",uAge);
        model.addAttribute("email",uEmail);
        return "detail";
    }

    @RequestMapping("/index")
    public String demoIndex() {
        return "index";
    }

}
