package cn.ist.simulation.simulation.domain;

import cn.ist.simulation.simulation.common.SelfValidating;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 16:16
 */
public class Task extends SelfValidating<Task> {
    private Long remainingTime;

    private TaskState taskState;

    public Task() {
        this.remainingTime = -1L;
        this.taskState = TaskState.Waiting;
    }

    public void elapse(Long time) {
        this.remainingTime -= time;
    }

    public void runWith(Long remainingTime) {
        this.remainingTime = remainingTime;
        this.taskState = TaskState.Running;
    }

    public boolean canFinish() {
        return this.taskState == TaskState.Running && this.remainingTime <= 0L;
    }

    public boolean isWaiting() {
        return this.taskState == TaskState.Waiting;
    }
}
