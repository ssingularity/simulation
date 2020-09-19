package cn.ist.simulation.simulation.domain;

import cn.ist.simulation.simulation.common.SelfValidating;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 16:16
 */
public class Task extends SelfValidating<Task> {
    @NotNull
    @Getter
    final DTInput product;

    @Setter
    private Long remainingTime;

    @Getter
    private TaskState taskState;

    public Task(DTInput product) {
        this.product = product;
        this.remainingTime = -1L;
        this.taskState = TaskState.Waiting;
        this.validateSelf();
    }

    void elapse(Long time) {
        this.remainingTime -= time;
    }

    void running() {
        this.taskState = TaskState.Running;
    }

    public boolean canFinish() {
        return this.taskState == TaskState.Running && this.remainingTime <= 0L;
    }
}
