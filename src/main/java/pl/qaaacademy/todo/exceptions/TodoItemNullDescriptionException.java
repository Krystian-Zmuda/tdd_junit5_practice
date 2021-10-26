package pl.qaaacademy.todo.exceptions;

public class TodoItemNullDescriptionException extends RuntimeException{
    public TodoItemNullDescriptionException(String s) {
        super(s);
    }
}
