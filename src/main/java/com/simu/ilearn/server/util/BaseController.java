package com.simu.ilearn.server.util;

import com.simu.ilearn.common.shared.dispatch.Response;
import com.simu.ilearn.common.shared.dispatch.ValidatedResponse;
import com.simu.ilearn.server.service.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ValidatedResponse handleException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> global = ex.getBindingResult().getGlobalErrors();

        Map<String, String> errors = new HashMap<String, String>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        List<String> globalErrors = new ArrayList<String>();
        for (ObjectError objectError : global) {
            globalErrors.add(objectError.getDefaultMessage());
        }

        return new ValidatedResponse(errors, globalErrors);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Response handleBusinessException(BusinessException ex) {
        return new Response(ex.getMessage());
    }
}
