package pl.qaaacademy.todo.exceptions;

public class TodoItemDescriptionTooLongException extends RuntimeException{
    public TodoItemDescriptionTooLongException(String s) {
        super(s);
    }
}
