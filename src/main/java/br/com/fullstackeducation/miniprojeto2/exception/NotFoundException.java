package br.com.fullstackeducation.miniprojeto2.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}