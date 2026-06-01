package br.com.fuctura.biblioteca.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends StandardError{

    List<FieldErrors> errors = new ArrayList<>();

    public ValidationErrors(LocalDateTime timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldErrors> getErrors() {
        return errors;
    }

    public void addErrors(String field, String defaultmessage) {
        this.errors.add(new FieldErrors(field, defaultmessage));
    }
}
