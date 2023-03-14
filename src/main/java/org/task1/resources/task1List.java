package org.task1.resources;
import java.util.*;

public class task1List {
    private Map<String, Task1Item> items=new HashMap<>();

    public List<Task1Item> getAll() {
        return new ArrayList<>(items.values());
    }
    public Task1Item get(String id) {
        return items.get(id);
    }
    public void add(Task1Item item) {
        item.setId(UUID.randomUUID().toString());
        items.put(item.getId(),item);
    }
    public void update(String id,Task1Item item) {
        item.setId(id);
        items.put(id,item);
    }
    public void delete(String id)
    {
        items.remove(id);
    }
}
