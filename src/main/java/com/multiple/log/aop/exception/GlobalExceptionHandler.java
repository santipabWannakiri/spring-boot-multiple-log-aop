package com.multiple.log.aop.exception;

import com.multiple.log.aop.constant.Constants;
import com.multiple.log.aop.exception.type.InstructorNotFoundException;
import com.multiple.log.aop.exception.type.UnableToSaveInstructorException;
import com.multiple.log.aop.model.json.AppResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception ex) {
        AppResponse response = new AppResponse();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof InstructorNotFoundException) {
            response.setAppResponseCode(Constants.INSTRUCTOR_NOT_FOUND_ERROR_CODE);
            response.setAppMessageCode(Constants.INSTRUCTOR_NOT_FOUND_MESSAGE_CODE);
            response.setDescription(ex.getMessage());
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UnableToSaveInstructorException) {
            response.setAppResponseCode(Constants.UNABLE_TO_SAVE_INSTRUCTOR_ERROR_CODE);
            response.setAppMessageCode(Constants.UNABLE_TO_SAVE_INSTRUCTOR_MESSAGE_CODE);
            response.setDescription(ex.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof ConstraintViolationException) {
            response.setAppResponseCode(Constants.BAD_REQUEST_ERROR_CODE);
            response.setAppMessageCode(Constants.BAD_REQUEST_MESSAGE_CODE);
            response.setDescription(extractErrorMessage((ConstraintViolationException) ex));
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.setAppResponseCode(Constants.SYSTEM_OR_RUNTIME_ERROR_ERROR_CODE);
            response.setAppMessageCode(Constants.SYSTEM_OR_RUNTIME_ERROR_MESSAGE_CODE);
            response.setDescription(Constants.SYSTEM_OR_RUNTIME_ERROR_MESSAGE);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    private String extractErrorMessage(ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getConstraintViolations().forEach(violation -> {
            errorMessage.append(violation.getMessageTemplate());
        });
        return errorMessage.toString();
    }
}
