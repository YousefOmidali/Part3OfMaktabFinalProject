package com.maktab.Part3MaktabFinalProject.entity.exceptions;

    public class InvalidPassword extends RuntimeException{
        public InvalidPassword() {
        }

        public InvalidPassword(String message) {
            super(message);
        }
}
