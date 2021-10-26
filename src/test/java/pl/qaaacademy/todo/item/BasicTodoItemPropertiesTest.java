package pl.qaaacademy.todo.item;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pl.qaaacademy.todo.enums.ItemStatus;
import pl.qaaacademy.todo.exceptions.TodoItemAlreadyCompletedException;
import pl.qaaacademy.todo.exceptions.TodoItemValidationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.qaaacademy.todo.matchers.ValidTodoItemMatcher.isValidTodoItemWith;

@Tag("item")
@Tag("unit")
public class BasicTodoItemPropertiesTest extends BaseTest {
    // mvn clean test -Dgroups="TAG"
    // command to filter tests by tag
    //TAG represents tag value
    // we can pass many tests using logical construction, example: "TAG | TAG" ,more in documentation

    @Tag("happy")
    @Test
    public void shouldCreateTodoItemWithTitleAndDescription() {
        logger2.warn("This is the test log string");
        assertThat(item, isValidTodoItemWith(title, description));
//        assertEquals(item.getTitle(), title);
//        assertEquals(item.getDescription(), description);
    }
    @Tag("exception")
    @Test
    public void shouldThrowAnExceptionWhileCreatingItemWithEmptyTitle() {
        String invalidTitle = "";
        description = "Some weird description for non-existing todo item";
        assertThrows(TodoItemValidationException.class,
                () -> TodoItem.of(invalidTitle, description));
    }
    @Tag("exception")
    @Test
    public void shouldThrowAnExceptionWhileSettingEmptyTitle() {
        String invalidTitle = "";
        assertThrows(TodoItemValidationException.class, () -> item.setTitle(invalidTitle));
    }
    @Tag("item")
    @Test
    public void shouldToggleStatusFromPendingToInProgress() {
        item.toggleStatus();
        assertEquals(item.getStatus(), ItemStatus.IN_PROGRESS);
    }
    @Tag("item")
    @Test
    public void shouldToggleStatusFromInProgressToPending() {
        item.toggleStatus();
        item.toggleStatus();
        assertEquals(item.getStatus(), ItemStatus.PENDING);
    }
    @Tag("item")
    @Test
    public void shouldCompleteATaskRepresentedByItem() {
        item.toggleStatus();
        item.complete();
        assertEquals(item.getStatus(), ItemStatus.COMPLETED);
    }
    @Tag("item")
    @Test
    public void shouldNotToggleStatusFromCompleteToInProgress() {
        item.toggleStatus();
        item.complete();
        assertThrows(TodoItemAlreadyCompletedException.class, () -> item.toggleStatus());
    }
    @Tag("exception")
    @Test
    public void shouldNotCreateATodoItemWithDescriptionLongerThan250Chars() {
        description = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium q";
        assertThrows(TodoItemValidationException.class, () -> TodoItem.of(title, description));
    }
    @Tag("exception")
    @Test
    public void shouldNotCreateATodoItemWithNullDescription() {
        assertThrows(NullPointerException.class, () -> TodoItem.of(title, null));
    }
    @Tag("exception")
    @Test
    public void shouldNotSetANewDescriptionLongerThan250Chars() {
        String toLong = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium q";
        assertThrows(TodoItemValidationException.class, () -> item.setDescription(toLong));
    }
    @Tag("exception")
    @Test
    public void shouldNotSetAnEmptyNewDescription() {
        assertThrows(TodoItemValidationException.class, () -> item.setDescription(""));
    }
}
