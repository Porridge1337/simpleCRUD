package ru.study.crud.service;

import ru.study.crud.dto.StudentDto;

import java.util.List;

public interface UserService {

    List<StudentDto> findAllStudent();

    StudentDto findStudentById(int id);

    void save(StudentDto studentDto);

    void delete(int id);

}
