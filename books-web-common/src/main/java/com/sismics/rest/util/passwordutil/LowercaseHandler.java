package com.sismics.rest.util.passwordutil;
import java.text.MessageFormat;

import org.codehaus.jettison.json.JSONException;

import com.sismics.rest.exception.ClientException;

public class LowercaseHandler implements PasswordHandler {
    private PasswordHandler nextHandler;

    @Override
    public void setNextHandler(PasswordHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String password, String name) throws JSONException {
        if (!password.matches(".*[a-z]+.*")) {
            throw new ClientException("ValidationError", MessageFormat.format("{0} must contain at least one lowercase letter", name));
        }
        if (nextHandler != null) {
            nextHandler.handleRequest(password, name);
        }
    }
}