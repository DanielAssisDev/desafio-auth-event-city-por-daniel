package com.devsuperior.bds04.errors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomErrorDTO{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String field, String error){
        errors.add(new FieldMessage(field, error));
    }
}

