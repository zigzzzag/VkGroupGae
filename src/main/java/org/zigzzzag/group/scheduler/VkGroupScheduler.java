package org.zigzzzag.group.scheduler;

import com.google.appengine.api.ThreadManager;
import org.zigzzzag.group.task.ClearOldDataTask;
import org.zigzzzag.group.task.VkGroupTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Zigzag on 09.07.2016.
 */
@WebListener
public class VkGroupScheduler implements ServletContextListener {

    private VkGroupTask vkGroupTask;
    ClearOldDataTask clearOldDataTask;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        vkGroupTask = new VkGroupTask();
        Thread vkGroupThread = ThreadManager.backgroundThreadFactory().newThread(vkGroupTask);
        vkGroupThread.start();

        clearOldDataTask = new ClearOldDataTask();
        Thread clearOldThread = ThreadManager.backgroundThreadFactory().newThread(clearOldDataTask);
        clearOldThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (vkGroupTask != null) {
            vkGroupTask.setWork(false);
        }
        if (clearOldDataTask != null) {
            clearOldDataTask.setWork(false);
        }
    }
}
