package com.multiple.log.aop.repository;

import com.multiple.log.aop.model.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("InstructorRepository")
public interface InstructorRepository extends CrudRepository<Instructor, Integer> {

}
