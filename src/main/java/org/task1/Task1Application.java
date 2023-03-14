package org.task1;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.task1.resources.task1List;
import org.task1.resources.Task1Resource;

public class Task1Application extends Application<Task1Configuration> {

    public static void main(final String[] args) throws Exception {
        new Task1Application().run(args);
    }

    @Override
    public String getName() {
        return "Task1";
    }



    @Override
    public void run(Task1Configuration configuration, Environment environment) {
        task1List taskList=new task1List();
        Task1Resource taskResource=new Task1Resource(taskList);
        environment.jersey().register(taskResource);
        environment.healthChecks().register("Shriya", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });

    }

}
