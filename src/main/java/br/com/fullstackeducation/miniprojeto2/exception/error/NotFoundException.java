package br.com.fullstackeducation.miniprojeto2.exception.error;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}