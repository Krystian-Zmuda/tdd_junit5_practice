package pl.qaaacademy.todo.item;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.qaaacademy.todo.exceptions.TodoItemValidationException;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.qaaacademy.todo.matchers.ValidTodoItemMatcher.isValidTodoItemWith;

public class ParameterizedTodoItemTest extends BaseTest {

    public static Stream<String> generateInvalidDescription() {
        return Stream.iterate(251, i -> i + 1).map(RandomStringUtils::randomAlphabetic).limit(10);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Todo item 1", "Todo item 2"})
    public void shouldChangeItemTitleToNewOne(String title) {
        item.setTitle(title);
        assertThat(item.getTitle(), equalTo(title));
    }

    @ParameterizedTest
    @MethodSource("generateInvalidDescription")
    public void shouldNotAcceptDescriptionLongerThan250Chars(String argument) {
        assertThrows(TodoItemValidationException.class, () -> item.setDescription(argument));
    }

    @ParameterizedTest
    @CsvFileSource(files = {"src/test/resources/todos.csv"}, numLinesToSkip = 1)
    public void shouldCreateValidTodoItemCsvFileSource(String title, String description) {
        TodoItem newTodo = TodoItem.of(title, description);
        assertThat(newTodo, isValidTodoItemWith(title, description));
    }

    @ParameterizedTest
    @ArgumentsSource(TodoItemArgumentProvider.class)
    public void shouldCreateValidTodoItemsArgumentSource(String title, String description) {
        TodoItem newTodo = TodoItem.of(title, description);
        assertThat(newTodo, isValidTodoItemWith(title, description));
    }
}
