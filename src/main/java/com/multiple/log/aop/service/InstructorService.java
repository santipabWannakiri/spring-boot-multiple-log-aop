package com.multiple.log.aop.service;


import com.multiple.log.aop.model.Instructor;

import java.util.List;

public interface InstructorService {

    public Instructor save(Instructor instructor);
    public Instructor findById(int id);
    public List<Instructor> findAll();
    public void deleteById(int id);
    public void testRuntimeError();

}
