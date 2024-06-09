package com.example.student.service.impl;

import com.example.student.model.Clazz;
import com.example.student.repository.IClazzRepository;
import com.example.student.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService implements IClazzService {
    @Autowired
    private IClazzRepository clazzRepository;
    @Override
    public List<Clazz> findAll() {
        return clazzRepository.findAll();
    }
}