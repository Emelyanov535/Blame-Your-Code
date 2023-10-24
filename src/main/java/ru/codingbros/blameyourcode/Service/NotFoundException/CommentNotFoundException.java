package ru.codingbros.blameyourcode.Service.NotFoundException;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(Long id){ super(String.format("Comment with id [%s] is not found", id));}
}
