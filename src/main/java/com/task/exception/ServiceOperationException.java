package com.task.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceOperationException extends RuntimeException {
    public ServiceOperationException(String message) {
        super(message);
    }
}
