package com.simu.ilearn.common.shared.dispatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatedResponse extends Response {
    private Map<String, String> errors;
    private List<String> globalErrors;

    public ValidatedResponse() {
        errors = new HashMap<String, String>();
        globalErrors = new ArrayList<String>();
    }

    public ValidatedResponse(Map<String, String> errors, List<String> globalErrors) {
        this.errors = errors;
        this.globalErrors = globalErrors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<String> globalErrors) {
        this.globalErrors = globalErrors;
    }
}
