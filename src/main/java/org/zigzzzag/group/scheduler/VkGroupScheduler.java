package org.zigzzzag.group.scheduler;

import org.zigzzzag.group.data.AllGroupData;
import org.zigzzzag.group.task.VkGroupTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zigzag on 09.07.2016.
 */
@WebListener
public class VkGroupScheduler implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new VkGroupTask(), 0, 10, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                AllGroupData.INSTANCE.clearOldData();
            }
        }, 0, 1, TimeUnit.DAYS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdownNow();
    }
}
