package pl.qaaacademy.todo.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import pl.qaaacademy.todo.item.TodoItem;

public class ValidTodoItemMatcher extends TypeSafeMatcher<TodoItem> {

    private String title;
    private String description;

    protected ValidTodoItemMatcher(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    protected boolean matchesSafely(TodoItem todoItem) {
        return todoItem.getTitle().equals(title) & todoItem.getDescription().equals(description);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Valid Todo item with title and description");
    }

  public static Matcher<TodoItem> isValidTodoItemWith(String title, String description) {
        return new ValidTodoItemMatcher(title, description);
  }
}
