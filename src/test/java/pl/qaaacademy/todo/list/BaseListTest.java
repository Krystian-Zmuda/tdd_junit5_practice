package pl.qaaacademy.todo.list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseListTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseListTest.class);

    protected String listTitle;
    protected TodoList itemList;

    @BeforeEach
    public void todoListSetUp() {
        listTitle = "This is List of a Title";
        itemList = new TodoList(listTitle);
    }

    @AfterEach
    public void todoListCleanUp() {
        itemList = null;
    }
}
