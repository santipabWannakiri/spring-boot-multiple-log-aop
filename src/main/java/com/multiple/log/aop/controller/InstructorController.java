package com.multiple.log.aop.controller;

import com.multiple.log.aop.constant.Constants;
import com.multiple.log.aop.model.Instructor;
import com.multiple.log.aop.model.json.AppResponse;
import com.multiple.log.aop.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/api")
public class InstructorController {

    private InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors")
    public ResponseEntity<?> getInstructors() {
        Optional<List<Instructor>> instructors = Optional.ofNullable(instructorService.findAll());
        if (instructors.isPresent()) {
            return new ResponseEntity<>(instructors.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Constants.INTERNAL_ERROR_RESPONSE_OBJECT, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<?> getInstructorById(@PathVariable("id") int id) {
        Optional<Instructor> instructor = Optional.ofNullable(instructorService.findById(id));
        if (instructor.isPresent()) {
            return new ResponseEntity<>(instructor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Constants.INTERNAL_ERROR_RESPONSE_OBJECT, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add/instructor")
    public ResponseEntity<?> addNewInstructor(@RequestBody Instructor instructor) {
        Optional<Instructor> result = Optional.ofNullable(instructorService.save(instructor));
        if (result.isPresent()) {
            return new ResponseEntity<>(new AppResponse(Constants.ADD_SUCCESS_MESSAGE), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Constants.INTERNAL_ERROR_RESPONSE_OBJECT, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/runtimeError")
    public void testRuntimeError(){
        instructorService.testRuntimeError();
    }
}
