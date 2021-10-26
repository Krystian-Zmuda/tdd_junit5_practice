package pl.qaaacademy.todo.list;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pl.qaaacademy.todo.enums.ItemStatus;
import pl.qaaacademy.todo.exceptions.TodoListItemTitleException;
import pl.qaaacademy.todo.exceptions.TodoListTitleException;
import pl.qaaacademy.todo.item.TodoItem;


import static org.junit.jupiter.api.Assertions.*;

@Tag("list")
@Tag("unit")
public class BasicTodoListPropertiesTest extends BaseListTest{

    @Tag("happy")
    @Test
    public void shouldCreateAnEmptyList() {
        assertEquals(0, itemList.getListSize());
    }

    @Test
    public void shouldNotCreateListWithEmptyTitle() {
        String emptyTitle = "";
        assertThrows(TodoListTitleException.class, () -> new TodoList(emptyTitle));
    }
    @Tag("happy")
    @Test
    public void shouldHaveTitleAndSize() {
        assertEquals("This is List of a Title", itemList.getTitle() );
        assertEquals(0, itemList.getListSize());
    }
    @Tag("happy")
    @Test
    public void shouldAddItemToTheList() {
        TodoItem item = TodoItem.of("Item name", "Item description");
        itemList.addItem(item);
        assertEquals(1, itemList.getListSize());
    }
    @Tag("happy")
    @Test
    public void shouldDeleteItemByTitle()  {
        TodoItem item = TodoItem.of("Item name", "Item description");
        TodoItem item1 = TodoItem.of("Item name1", "Item description1");
        itemList.addItem(item);
        itemList.addItem(item1);
        itemList.removeItem("Item name1");
        assertEquals(1,itemList.getListSize());
    }
    @Tag("happy")
    @Test
    public void shouldFilterListByItemStatus() {
        TodoItem item = TodoItem.of("Item name", "Item description");
        TodoItem item1 = TodoItem.of("Item name1", "Item description1");
        TodoItem item2 = TodoItem.of("Item name2", "Item description2");
        itemList.addItem(item);
        itemList.addItem(item1);
        itemList.addItem(item2);
        item.toggleStatus();
        item1.toggleStatus();
        item1.complete();
        itemList.filterByStatus(ItemStatus.IN_PROGRESS);
        assertEquals(1,itemList.getListSize());
    }
    @Tag("happy")
    @Test
    public void shouldSortListByItemTitle() {
        TodoItem item1 = TodoItem.of("Item name1", "Item description1");
        TodoItem item2 = TodoItem.of("Item name2", "Item description2");
        TodoItem item3 = TodoItem.of("Item name3", "Item description3");
        TodoList reversedOrder = new TodoList(itemList.getTitle());
        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);
        reversedOrder.addItem(item3);
        reversedOrder.addItem(item2);
        reversedOrder.addItem(item1);
        reversedOrder.sortList();
        assertEquals(itemList,reversedOrder);
    }

    @Tag("exception")
    @Test
    public void shouldNotAddDuplicateItems() {
        TodoItem item1 = TodoItem.of("Item name", "Item description");
        TodoItem item2 = TodoItem.of("Item name", "Item description");
        itemList.addItem(item1);
        assertThrows(TodoListItemTitleException.class,() -> itemList.addItem(item2));
    }
}
