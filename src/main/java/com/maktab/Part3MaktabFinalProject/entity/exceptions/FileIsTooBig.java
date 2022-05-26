package com.maktab.Part3MaktabFinalProject.entity.exceptions;

public class FileIsTooBig extends RuntimeException {
    public FileIsTooBig() {
    }

    public FileIsTooBig(String message) {
        super(message);
    }
}
