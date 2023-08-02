import extentions.ToDoClientProvider;
import extentions.ToDoProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.inno.todo.ToDoClient;
import ru.inno.todo.model.ToDoItem;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ToDoClientProvider.class)
public class ToDoBusinessTest {

    @Test
    @DisplayName("Проверяем, что задача создается")
    public void shouldCreateTask(ToDoClient client) throws IOException {
        // получить список задач
        List<ToDoItem> listBefore = client.getAll();
        // создать задачу
        String title = "My super task";
        ToDoItem newItem = client.create(title);

        // проверить, что задача отображается в списке
        assertFalse(newItem.getUrl().isBlank());
        assertFalse(newItem.isCompleted());
        assertTrue(newItem.getId() > 0);
        assertEquals(title, newItem.getTitle());
        // TODO: bug report. Oreder is null
        assertEquals(0, newItem.getOrder());
        // задач стало на 1 больше
        List<ToDoItem> listAfter = client.getAll();
        assertEquals(1, listAfter.size() - listBefore.size());

        // проверить еще и по id
        ToDoItem single  = client.getById(newItem.getId());
        assertEquals(title, single.getTitle());
    }

    @Test
    @ExtendWith(ToDoProvider.class)
    public void shouldRename(ToDoClient client, ToDoItem created, ToDoItem created1) throws IOException {

        String myNewTitle = "my new title";
        ToDoItem update = client.renameById(created.getId(), myNewTitle);

        ToDoItem item = client.getById(update.getId());

        assertEquals(myNewTitle, item.getTitle());
        assertEquals(myNewTitle, update.getTitle());
        assertEquals(created.getId(), item.getId());
        assertEquals(created.getUrl(), item.getUrl());
        assertEquals(created.getOrder(), item.getOrder());
        assertEquals(created.isCompleted(), item.isCompleted());

        client.deleteById(item.getId());
    }

//    @Test
//    @ExtendWith(ToDoListProvider.class)
//    public void getList10( @ItemList(10) List<ToDoItem> list) {
//
//    }
//
//    @Test
//    @ExtendWith(ToDoListProvider.class)
//    public void getList5( @ItemList(5) List<ToDoItem> list) {
//
//    }
}
