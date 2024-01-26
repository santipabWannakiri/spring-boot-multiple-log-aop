package com.multiple.log.aop.constant;

import com.multiple.log.aop.model.json.AppResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class Constants {

    //====== SUCCESS ======
    public static final String SUCCESS_CODE = "0000";
    public static final String SUCCESS_MESSAGE_CODE = "SUCCESS";
    public static final String ADD_SUCCESS_MESSAGE = "Added new instructor";

    //====== INTERNAL_SERVER_ERROR ======
    public static final String INTERNAL_ERROR_CODE = "6601";
    public static final String INTERNAL_MESSAGE_CODE = "FAILURE";
    public static final String UNABLE_TO_PROCESS_MESSAGE = "Unable to process request. Please try again.";

    //====== INSTRUCTOR_NOT_FOUND======
    public static final String INSTRUCTOR_NOT_FOUND_ERROR_CODE = "6601";
    public static final String INSTRUCTOR_NOT_FOUND_MESSAGE_CODE = "FAILURE";
    public static final String INSTRUCTOR_NOT_FOUND_MESSAGE = "Instructor not found.";

    //====== UNABLE_TO_SAVE_INSTRUCTOR_ERROR ======
    public static final String UNABLE_TO_SAVE_INSTRUCTOR_ERROR_CODE = "6601";
    public static final String UNABLE_TO_SAVE_INSTRUCTOR_MESSAGE_CODE = "UNABLE_TO_SAVE_INSTRUCTOR";
    public static final String UNABLE_TO_SAVE_INSTRUCTOR_MESSAGE = "There is the issue while saving instructor.";

    //====== SYSTEM_OR_RUNTIME_ERROR ======
    public static final String SYSTEM_OR_RUNTIME_ERROR_ERROR_CODE = "6601";
    public static final String SYSTEM_OR_RUNTIME_ERROR_MESSAGE_CODE = "SYSTEM_OR_RUNTIME_EXCEPTION";
    public static final String SYSTEM_OR_RUNTIME_ERROR_MESSAGE = "System error or Runtime exception.";

    //====== BAD_REQUEST_ERROR ======
    public static final String BAD_REQUEST_ERROR_CODE = "6060";
    public static final String BAD_REQUEST_MESSAGE_CODE = "BAD_REQUEST";

    //====== INTERNAL_ERROR_RESPONSE_OBJECT ======
    public static final AppResponse INTERNAL_ERROR_RESPONSE_OBJECT = new AppResponse(Constants.INTERNAL_ERROR_CODE, Constants.INTERNAL_MESSAGE_CODE, Constants.UNABLE_TO_PROCESS_MESSAGE);

    //====== REQUEST_ATTRIBUTE_NAME ======
    public static final String X_REQUEST_ID = "X-Request-Id";

    //====== GET_REQUEST_ID ======
    public static String getRequestId() {
        String requestId = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            requestId = (String) request.getAttribute("X-Request-Id");
        }
        return requestId;
    }

}
