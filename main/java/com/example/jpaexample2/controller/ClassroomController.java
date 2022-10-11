package com.example.jpaexample2.controller;

import com.example.jpaexample2.dto.request.SearchClassRequest;
import com.example.jpaexample2.dto.respone.ClassRoomDto;
import com.example.jpaexample2.dto.respone.ResponeData;
import com.example.jpaexample2.dto.respone.Response;
import com.example.jpaexample2.entity.Student;
import com.example.jpaexample2.service.ClassroomService;
import com.example.jpaexample2.entity.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/class")
public class ClassroomController {

    ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

//    @GetMapping("/add")
//    public String exThymeleaf() {
//
//        return "Xin chào";
//    }

//    @PostMapping("")
//    public ResponseEntity<ClassRoom> addAClass(@RequestBody ClassRoom classRoom) {
//        return new ResponseEntity<ClassRoom>(classroomService.addClass(classRoom), HttpStatus.CREATED);
//    }


    @PostMapping("/add")
    public ClassRoom addClass(@RequestBody ClassRoom aclass) {
        return classroomService.addClass(aclass);
    }
//
//    @PostMapping("/create")
//    public ClassRoom createClassAndStudent(@RequestBody ClassRoom classRoom, int stdId) {
//        ClassRoom addedClassroom = classroomService.createClassAndStudent(classRoom, stdId);
//        return addedClassroom;
//    }

//    public ClassRoomDto getusedto(@PathVariable int id) {
//        ClassRoom classRoom = classroomService.getByClassId(id);
//        ClassRoomDto classRoomDto = new ClassRoomDto();
//        classRoomDto.loadFromEntity(classRoom);
//        return a;
//    }

    @GetMapping("get")
    public List<ClassRoom> getAllClass() {
        List<ClassRoom> allClass = classroomService.getAllClass();
        return allClass;
    }

    @GetMapping("search")
    public List<ClassRoom> searchClassrooms () {
        return null;
    }

    @GetMapping("/asc")
    public Page<ClassRoom> getClassrooms(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size,
                                         @RequestParam(name = "field") Optional<String> sortBy) {
//        Response<ResponeData<ClassRoom>> response;
        Page<ClassRoom> classRoomPage = classroomService.getClassrooms(page, size, sortBy);
        return classRoomPage;
    }

    @GetMapping("/get/{id}")
    public ClassRoom getByClassId(@PathVariable int id ) {
        return classroomService.getByClassId(id);
    }

    @GetMapping("/getbyname")
    public ClassRoom getByClassName(@RequestParam(name = "class_name") String name ) {
        return classroomService.getByClassName(name);
    }

    //    Tìm tất cả sinh viên có lastname = ... , gradle = ...
    @GetMapping("/getbyteacher")
    public List<ClassRoom> getByTeacher(@RequestParam(name = "teacher") String teacher) {
        return classroomService.getByTeacher(teacher);
    }

    //    Xoá sinh viên có id = ....
    @PutMapping("/update/{id}")
    public ClassRoom updateClass(@PathVariable(name = "id") int id, @RequestBody ClassRoom aclass) {
        return classroomService.updateClass(id, aclass);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClass(@PathVariable(name = "id") int id) {
        return classroomService.deleteClass(id);
    }


}
