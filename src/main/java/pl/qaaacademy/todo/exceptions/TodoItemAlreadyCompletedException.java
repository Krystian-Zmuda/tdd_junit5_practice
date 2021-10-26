package pl.qaaacademy.todo.exceptions;

public class TodoItemAlreadyCompletedException extends RuntimeException{
    public TodoItemAlreadyCompletedException(String s) {
        super(s);
    }
}
