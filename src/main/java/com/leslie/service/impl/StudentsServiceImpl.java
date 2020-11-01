package com.leslie.service.impl;

import com.leslie.mapper.StudentsMapper;
import com.leslie.pojo.Students;
import com.leslie.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsMapper studentsMapper;

    @Override
    public List<Students> findAllStudents() {
        return studentsMapper.selectList(null);
    }
}
