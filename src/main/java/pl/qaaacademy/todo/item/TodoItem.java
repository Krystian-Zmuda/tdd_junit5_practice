package pl.qaaacademy.todo.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.qaaacademy.todo.enums.ItemStatus;
import pl.qaaacademy.todo.exceptions.TodoItemAlreadyCompletedException;
import pl.qaaacademy.todo.interfaces.StatusChangable;

import static pl.qaaacademy.todo.core.TodoItemValidator.validateDescription;
import static pl.qaaacademy.todo.core.TodoItemValidator.validateTitle;

public class TodoItem implements StatusChangable {

    private String title;
    private String description;
    private ItemStatus status;
    protected static final Logger logger = LoggerFactory.getLogger(TodoItem.class);

    private TodoItem() {

    }

    private TodoItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = ItemStatus.PENDING;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
            validateDescription(description);
            this.description = description;
    }

    public ItemStatus getStatus() {
        return status;
    }

//    public void setStatus(ItemStatus status) {
//        this.status = status;
//    }

    public static TodoItem of(String title, String description) {
        validateTitle(title);
        validateDescription(description);
        return new TodoItem(title,description);

    }

//    private static void validateTitle(String title) {
//        if (title == null || title.isBlank()) {
//            logger.error("Item title is null or blank");
//            throw new TodoItemValidationException("Item title is null or blank");
//        }
//    }
//    private static void validateDescription(String description) {
//        if (description != null) {
//            int stringLength = description.length();
//            if (stringLength > 250) {
//                logger.error("Too long description");
//                throw new TodoItemDescriptionTooLongException("Description is longer then 250 characters");
//            }
//        }else if (description == null) {
//            logger.error("Description i null");
//            throw new TodoItemNullDescriptionException("Description is null");
//        }
//    }
    @Override
    public void toggleStatus() {
        if (this.status == ItemStatus.PENDING) {
            this.status = ItemStatus.IN_PROGRESS;
        } else if (this.status == ItemStatus.COMPLETED) {
            logger.error("Completed status is not changeable");
            throw new TodoItemAlreadyCompletedException("Item has been completed");
        } else {
            this.status = ItemStatus.PENDING;
        }
    }

    @Override
    public void complete() {
        if (this.status == ItemStatus.IN_PROGRESS) {
            this.status = ItemStatus.COMPLETED;
        }

    }
}
