package org.task1.resources;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/task1")
@Produces(MediaType.APPLICATION_JSON)
public class Task1Resource {
    private task1List taskList;

    public Task1Resource(task1List taskList) {
        this.taskList=taskList;
    }

    @GET
    public List<Task1Item> getAll() {
        return taskList.getAll();
    }

    @GET
    @Path("/{id}")
    public Task1Item get(@PathParam("id") String id) {
        return taskList.get(id);
    }

    @POST
    public Task1Item add(Task1Item item) {
        taskList.add(item);
        return item;
    }
    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Task1Item item) {
        taskList.update(id, item);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void partialUpdate(@PathParam("id") String id, Map<String,Object> updates) {
        Task1Item item=taskList.get(id);
        if (item ==null) {
            throw new NotFoundException();
        }
        for (Map.Entry<String, Object> entry: updates.entrySet()) {
            String field =entry.getKey();
            Object value=entry.getValue();
            switch(field) {
                case "title":
                    item.setTitle((String) value);
                    break;
                case "completed":
                    item.setCompleted((Boolean) value);
                    break;
                default:
                    throw new BadRequestException("Invalid field: "+ field);
            }
        }
        taskList.update(id,item);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        taskList.delete(id);
    }
}
