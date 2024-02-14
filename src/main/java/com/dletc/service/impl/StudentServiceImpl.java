package com.dletc.service.impl;

import com.dletc.dao.StudentDao;
import com.dletc.dao.impl.StudentDaoImpl;
import com.dletc.domain.Student;
import com.dletc.service.StudentService;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao=new StudentDaoImpl() ;
    @Override
    public int insertStudent(Student student) {
        return studentDao.insertStudent(student);
    }
}
