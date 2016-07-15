package org.zigzzzag.group.task;

import org.zigzzzag.group.data.AllGroupData;

/**
 * Created by sbt-nikiforov-mo on 15.07.16.
 */
public class ClearOldDataTask implements Runnable {

    private static final long ONE_DAY = 24 * 60 * 60 * 1000;
    private boolean work = true;

    @Override
    public void run() {

        while (work) {
            try {
                AllGroupData.INSTANCE.clearOldData();
                Thread.sleep(ONE_DAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }
}
