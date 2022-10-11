package com.example.jpaexample2.dto.respone;

import com.example.jpaexample2.entity.ClassRoom;
import lombok.Data;

@Data
public class ClassRoomDto {

    private int classId;
    private String className;

    public ClassRoomDto(ClassRoom classRoom) {
        this.classId = classRoom.getClassId();
        this.className = classRoom.getClassName();
    }
}
