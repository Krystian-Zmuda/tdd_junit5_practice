package pl.qaaacademy.todo.list;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.qaaacademy.todo.enums.ItemStatus;
import pl.qaaacademy.todo.exceptions.TodoListItemTitleException;
import pl.qaaacademy.todo.exceptions.TodoListTitleException;
import pl.qaaacademy.todo.item.TodoItem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TodoList {
    private String title;
    private List<TodoItem> itemList;

    protected static final Logger logger = LoggerFactory.getLogger(TodoList.class);

    public TodoList(String title) {
        if (title.isBlank()) {
            logger.error("List cannot have empty title");
            throw new TodoListTitleException("List cannot have empty title");
        } else {
            this.title = title;
            this.itemList = new ArrayList<>();
        }
    }

    public int getListSize() {
        return itemList.size();
    }

    public String getTitle() {
        return title;
    }

    public List<TodoItem> getItemList() {
        return itemList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addItem(TodoItem item) {
        for (TodoItem t : this.itemList) {
            if (t.getTitle().equals(item.getTitle())) {
                logger.error("Item of that title already exists");
                throw new TodoListItemTitleException("Item of that title already exists");
            }
        }
        itemList.add(item);
    }

    public void removeItem(String itemTitle) {
        itemList.removeIf(t -> t.getTitle().equals(itemTitle));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoList todoList = (TodoList) o;
        return Objects.equals(title, todoList.title) && Objects.equals(itemList, todoList.itemList);
    }

    public void filterByStatus(ItemStatus status) {
        for (TodoItem s : itemList.stream().toList()) {
            if (!s.getStatus().equals(status)) {
                removeItem(s.getTitle());
            }
        }
    }
    public void sortList() {
        this.itemList = getItemList().stream()
                .sorted(Comparator.comparing(TodoItem::getTitle))
                .collect(Collectors.toList());
    }
}
