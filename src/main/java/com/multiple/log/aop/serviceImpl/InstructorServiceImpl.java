package com.multiple.log.aop.serviceImpl;

import com.multiple.log.aop.constant.Constants;
import com.multiple.log.aop.exception.type.InstructorNotFoundException;
import com.multiple.log.aop.exception.type.UnableToSaveInstructorException;
import com.multiple.log.aop.model.Instructor;
import com.multiple.log.aop.repository.InstructorRepository;
import com.multiple.log.aop.service.InstructorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InstructorServiceImpl implements InstructorService {

    private InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor save(Instructor instructor) {
        Optional<Instructor> result = Optional.of(instructorRepository.save(instructor));
        return result.orElseThrow(() -> {
            throw new UnableToSaveInstructorException(Constants.UNABLE_TO_SAVE_INSTRUCTOR_MESSAGE);
        });
    }

    @Override
    public Instructor findById(int id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        return instructor.orElseThrow(() -> {
            throw new InstructorNotFoundException(Constants.INSTRUCTOR_NOT_FOUND_MESSAGE);
        });
    }

    @Override
    public List<Instructor> findAll() {
        Optional<List<Instructor>> result = Optional.of((List<Instructor>) instructorRepository.findAll());
        return result.orElseThrow(() -> {
            throw new InstructorNotFoundException(Constants.INSTRUCTOR_NOT_FOUND_MESSAGE);
        });
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void testRuntimeError() {
        Optional<Instructor> instructor = instructor = null;
        instructor.get();
    }
}
